package associacaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MinhaAssociacao implements InterfaceAssociacao {

	private ArrayList<Associado> ListaDeAssociados = new ArrayList<Associado>();
	private ArrayList<Reuniao> ListaDeReunioes = new ArrayList<Reuniao>();
	private ArrayList<Taxa> ListaDeTaxas = new ArrayList<Taxa>();
	private ArrayList<TaxasPagas> ListaDeTaxasPagas = new ArrayList<TaxasPagas>();
	private ArrayList<InfoReunioes> ListaDeFrequencias = new ArrayList<InfoReunioes>();

	@Override
	public double calcularFrequencia(int numAssociado, int numAssociacao, long inicio, long fim)
			throws AssociadoNaoExistente, AssociacaoNaoExistente {
		Associado associado;
		Associacao associacao;
		try {
			associado = pesquisarAssociado(numAssociado);
		} catch (SQLException e) {
		} catch (AssociadoNaoExistente e) {
			throw new AssociadoNaoExistente();
		}
		try {
			associacao = pesquisarAssociacao(numAssociacao);
		} catch (SQLException e) {
		} catch (AssociacaoNaoExistente e) {
			throw new AssociacaoNaoExistente();
		}
		try {
			return (double) totalReunioesPresente(inicio, fim, numAssociado, numAssociacao) * 100
					/ totalReunioes(inicio, fim) / 100;
		} catch (SQLException e) {
			return 0;
		}

	}

	public int totalReunioesPresente(long inicio, long fim, int numAssociado, int numAssociacao) throws SQLException {
		int reunioesPresente = 0;

		Connection con = Conexao.getConexao();
		String cmd = "select * from frequencia where associado = " + numAssociado;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		if (rs.next()) {
			long data = rs.getLong("data");
			int associacao = rs.getInt("associacao");
			int associado = rs.getInt("associado");
			if (data >= inicio && data <= fim && associado == numAssociado && associacao == numAssociacao) {
				reunioesPresente++;
			}
		}
		st.close();
		return reunioesPresente;

	}

	@Override
	public void registrarFrequencia(int codigoAssociado, int numAssociacao, long dataReuniao)
			throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente, FrequenciaJaRegistrada,
			FrequenciaIncompativel {
		// Registra a frequencia de um associado em uma reuni�o. n�o deveria registrar
		// participacao em
		// reunioes acontecidas antes da sua filiacao na associa��o.

		Associado associado = null;
		Associacao associacao;
		Reuniao reuniao;
		try {
			associado = pesquisarAssociado(codigoAssociado);
		} catch (SQLException e) {
		} catch (AssociadoNaoExistente e) {
			throw new AssociadoNaoExistente();
		}
		// Verificando se o associado pertence a associacao x
		try {
			associacao = pesquisarAssociacao(numAssociacao);
		} catch (SQLException e) {
		} catch (AssociacaoNaoExistente e) {
			throw new AssociacaoNaoExistente();
		}
		try {
			reuniao = pesquisarReuniao(dataReuniao);
		} catch (SQLException e) {
		} catch (ReuniaoNaoExistente e) {
			throw new ReuniaoNaoExistente();
		}
		try {
			pesquisarFrequencia(codigoAssociado, numAssociacao, dataReuniao);
			throw new FrequenciaJaRegistrada();
		} catch (SQLException e) {
		} catch (FrequenciaIncompativel e) {
			if (associado.getNumero() == codigoAssociado && associado.getAssociacao() == numAssociacao) {
				try {
					Connection con = Conexao.getConexao();
					String cmd = "insert into frequencia (data, associado, associacao) values (" + "'" + dataReuniao
							+ "', " + codigoAssociado + ", " + numAssociacao + ")";
					Statement st = con.createStatement();
					st.executeUpdate(cmd);
					st.close();
				} catch (SQLException j) {
				}
			} else {
				throw new AssociadoNaoExistente();
			}
		}

	}

	@Override
	public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, long data,
			double valor)
			throws AssociacaoNaoExistente, AssociadoNaoExistente, AssociadoJaRemido, TaxaNaoExistente, ValorInvalido {
		Associado associado = null;
		Taxa taxas = null;
		Associacao associacao;
		try {
			associacao = pesquisarAssociacao(numAssociacao);
		} catch (SQLException e) {
		} catch (AssociacaoNaoExistente e) {
			throw new AssociacaoNaoExistente();
		}

		try {
			associado = pesquisarAssociado(numAssociado);
		} catch (SQLException e) {
		} catch (AssociadoNaoExistente e) {
			throw new AssociadoNaoExistente();
		}
		try {
			taxas = pesquisarTaxa(taxa, vigencia);

		} catch (TaxaNaoExistente e) {
			throw new TaxaNaoExistente();
		} catch (SQLException e) {
		}
		// Verifica se o associado ja � remido
		if (associado.isDiscriminador()) {
			// Verifica se a taxa � administrativa,
			// se sim, gera uma exce��o de AssociadoJaRemido,
			// se n�o, registra pagamento da taxa
			if (taxas.isAdministrativa()) {
				throw new AssociadoJaRemido();
			}
			if (valor >= taxas.getValorParcelas() || valor >= taxas.getRestante() || valor == taxas.getRestante()) {
				ListaDeTaxasPagas.add(new TaxasPagas(numAssociacao, taxa, vigencia, numAssociado, data, valor));
				// subitrair valor da taxa do valor restante
				try {
					double pagar = taxas.getValorAno() - valor;
					// Atualiza o valor restante no banco de dados
					Connection con = Conexao.getConexao();
					String cmd = "update taxa SET valor = (" + pagar + ")" + " where nome = (" + "\'" + taxa + "'"
							+ ")";
					Statement st = con.createStatement();
					st.executeUpdate(cmd);
					st.close();
				} catch (Exception SQLException) {
					// TODO: handle exception
				}
			} else {
				throw new ValorInvalido();
			}
		} else {
			if (valor >= taxas.getValorParcelas() || valor >= taxas.getRestante() || valor == taxas.getRestante()) {
				ListaDeTaxasPagas.add(new TaxasPagas(numAssociacao, taxa, vigencia, numAssociado, data, valor));
				try {
					double pagar = taxas.getValorAno() - valor;
					// Atualiza o valor restante no banco de dados
					Connection con = Conexao.getConexao();
					String cmd = "update taxa SET valor = (" + pagar + ")" + " where nome = (" + "\'" + taxa + "'"
							+ ")";
					Statement st = con.createStatement();
					st.executeUpdate(cmd);
					st.close();
				} catch (Exception SQLException) {
					// TODO: handle exception
				}
			} else {
				throw new ValorInvalido();
			}
		}

	}

	@Override
	public double somarPagamentoDeAssociado(int numAssociacao, int numAssociado, String nomeTaxa, int vigencia,
			long inicio, long fim) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente {
		// TODO Auto-generated method stub

		Associado associado;
		Associacao associacao;
		Taxa taxas;
		double total = 0;
		try {
			associado = pesquisarAssociado(numAssociado);
		} catch (SQLException e) {
		} catch (AssociadoNaoExistente e) {
			throw new AssociadoNaoExistente();
		}
		try {
			taxas = pesquisarTaxa(nomeTaxa, vigencia);
		} catch (SQLException e) {
		} catch (TaxaNaoExistente e) {
			throw new TaxaNaoExistente();
		}
		try {
			associacao = pesquisarAssociacao(numAssociacao);
		} catch (SQLException e) {
		} catch (AssociacaoNaoExistente e) {
			throw new AssociacaoNaoExistente();
		}
		// Procura na lista de taxas pagas e soma tudo na variavel total
		for (TaxasPagas pg : ListaDeTaxasPagas) {
			if (nomeTaxa.equals(pg.getTaxa()) && pg.getNumAssociado() == numAssociado
					&& pg.getNumAssociacao() == numAssociacao) {
				total += pg.getValor();
			}
		}
		return total;
	}

	@Override
	public double calcularTotalDeTaxas(int numAssociacao, int vigencia) throws AssociacaoNaoExistente {

		Associacao associacao = null;
		double total = 0;
		try {
			associacao = pesquisarAssociacao(numAssociacao);
		} catch (SQLException e) {
		} catch (AssociacaoNaoExistente e) {
			throw new AssociacaoNaoExistente();
		}

		try {
			Connection con = Conexao.getConexao();
			String cmd = "select * from taxa where vigencia = " + vigencia;
			Taxa a = null;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(cmd);

			while (rs.next()) {
				String nome1 = rs.getString("nome");
				int vigencia1 = rs.getInt("vigencia");
				double valor = rs.getDouble("valor");
				int parcelas = rs.getInt("parcelas");
				boolean administrativa = rs.getBoolean("administrativa");
				int associacao1 = rs.getInt("associacao");
				ListaDeTaxas.add(new Taxa(nome1, vigencia1, valor, parcelas, administrativa));
			}
		} catch (Exception SQLException) {

		}

		// Calcula a soma das taxas
		for (Taxa taxa : ListaDeTaxas) {
			if (associacao.getNum() == numAssociacao && taxa.getVigencia() == vigencia) {
				total += taxa.getValorAno();
			}
		}
		return total;
	}

	@Override
	public void adicionar(associacaoBD.Associacao a) throws AssociacaoJaExistente, ValorInvalido {

		if (a.getNome() == null || a.getNum() <= 0 || (a.getNome().isEmpty() == true)) {
			throw new ValorInvalido();
		}
		try {
			pesquisarAssociacao(a.getNum());
			throw new AssociacaoJaExistente();
		} catch (SQLException e) {
		} catch (AssociacaoNaoExistente e) {
			try {
				Connection con = Conexao.getConexao();
				String cmd = "insert into associacao (numero, nome) values (" + a.getNum() + ", \'" + a.getNome()
						+ "\')";
				Statement st = con.createStatement();
				st.executeUpdate(cmd);
				st.close();
			} catch (Exception j) {
				// TODO: handle exception
			}
		}
	}

	public Associacao pesquisarAssociacao(int numAssociacao) throws AssociacaoNaoExistente, SQLException {
		Associacao a = null;
		try {
			Connection con = Conexao.getConexao();
			String cmd = "select * from associacao where numero = " + numAssociacao;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(cmd);
			if (rs.next()) {
				int num = rs.getInt("numero");
				String nome = rs.getString("nome");
				a = new Associacao(num, nome);
			}
			st.close();
		} catch (Exception SQLException) {

		}
		if (a == null) {
			throw new AssociacaoNaoExistente();
		}

		return a;
	}

	@Override
	public void adicionar(int associacao, associacaoBD.Associado a)
			throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido {
		// TODO Auto-generated method stub

		if (a.getNumero() <= 0 || a.getTelefone() == null || a.getDataAssociacao() <= 0 || a.getNascimento() <= 0
				|| (a.getTelefone().isEmpty() == true) || a.getNome() == null || (a.getNome().isEmpty() == true)) {
			throw new ValorInvalido();
		}

		try {
			pesquisarAssociacao(associacao);
		} catch (AssociacaoNaoExistente e) {
			throw new AssociacaoNaoExistente();
		} catch (SQLException e) {
		}
		try {
			pesquisarAssociado(a.getNumero());
			throw new AssociadoJaExistente();
		} catch (SQLException e) {
		} catch (AssociadoNaoExistente e) {
			if (a instanceof AssociadoRemido) {
				try {

					Connection con = Conexao.getConexao();
					String cmd = "insert into associado (numero, nome, telefone, data_associacao, nascimento, associacao, discriminador, data_remissao) values ("
							+ a.getNumero() + ", \'" + a.getNome() + "', \'" + a.getTelefone() + "', "
							+ a.getDataAssociacao() + ", " + a.getNascimento() + ", " + associacao + ", " + true + ", "
							+ ((AssociadoRemido) a).getDataRemissao() + ")";
					Statement st = con.createStatement();
					st.executeUpdate(cmd);
					st.close();
				} catch (Exception i) {
					// TODO: handle exception
				}
			} else {
				try {
					Connection con = Conexao.getConexao();
					String cmd = "insert into associado (numero, nome, telefone, data_associacao, nascimento, associacao, discriminador, data_remissao) values ("
							+ a.getNumero() + ", \'" + a.getNome() + "', \'" + a.getTelefone() + "', "
							+ a.getDataAssociacao() + ", " + a.getNascimento() + ", " + associacao + ", "
							+ a.isDiscriminador() + ", " + false + ")";
					Statement st = con.createStatement();
					st.executeUpdate(cmd);
					st.close();
				} catch (Exception i) {
					// TODO: handle exception
				}
			}
		}

	}

	public Associado pesquisarAssociado(int numAssociado) throws AssociadoNaoExistente, SQLException {

		// Pesquisa na lista de associados, se o associado estiver cadastrado
		// retorna o cadastro, caso contrario da uma exce��o
		Connection con = Conexao.getConexao();
		String cmd = "select * from associado where numero = " + numAssociado;
		Associado a = null;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		if (rs.next()) {
			int num = rs.getInt("numero");
			String nome = rs.getString("nome");
			String telefone = rs.getString("telefone");
			long dataAssociacao = rs.getLong("data_associacao");
			long nascimento = rs.getLong("nascimento");
			int associacao = rs.getInt("associacao");
			boolean discriminador = rs.getBoolean("discriminador");
			long dataRemissao = rs.getLong("data_remissao");
			a = new Associado(num, nome, telefone, dataAssociacao, nascimento, associacao);
			a.setDiscriminador(discriminador);
			return a;
		}
		st.close();
		if (a == null) {
			throw new AssociadoNaoExistente();
		}
		return a;
	}

	@Override
	public void adicionar(int associacao, associacaoBD.Reuniao r)
			throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido {

		if (r.getAta() == null || r.getData() <= 0 || (r.getAta().isEmpty() == true)) {
			throw new ValorInvalido();
		}
		// TODO Auto-generated method stub
		try {
			pesquisarAssociacao(associacao);
		} catch (SQLException e) {
		} catch (AssociacaoNaoExistente e) {
			throw new AssociacaoNaoExistente();
		}
		try {
			pesquisarReuniao(r.getData());
			throw new ReuniaoJaExistente();
		} catch (SQLException e) {
		} catch (ReuniaoNaoExistente e) {
			try {
				Connection con = Conexao.getConexao();
				String cmd = "insert into reuniao (pauta, data, associacao) values (" + "'" + r.getAta() + "', "
						+ r.getData() + ", " + associacao + ")";
				Statement st = con.createStatement();
				st.executeUpdate(cmd);
				st.close();
			} catch (SQLException p) {
			}
		}

	}

	public Reuniao pesquisarReuniao(long data) throws ReuniaoNaoExistente, SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "select * from reuniao where data = " + data;
		Reuniao r = null;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		if (rs.next()) {
			long data1 = rs.getLong("data");
			String pauta = rs.getString("pauta");
			int associacao = rs.getInt("associacao");
			if (data == data1) {
				r = new Reuniao(data, pauta);
				r.setAssociacao(associacao);
			}
		}
		if (r == null) {
			throw new ReuniaoNaoExistente();
		}
		st.close();
		return r;
	}

	// *Criar uma classe para otimizar isso
	public int totalReunioes(long inicio, long fim) throws SQLException {
		int reunioes = 0;

		ArrayList<Reuniao> ListaDeReunioes = new ArrayList<Reuniao>();

		Connection con = Conexao.getConexao();
		Statement st = con.createStatement();
		String cmd = "select * from reuniao";
		ResultSet rs = st.executeQuery(cmd);
		while (rs.next()) {
			String pauta = rs.getString("pauta");
			long data = rs.getLong("data");
			int associacao = rs.getInt("associacao");
			ListaDeReunioes.add(new Reuniao(data, pauta, associacao));
		}

		for (Reuniao reuniao : ListaDeReunioes) {
			if (reuniao.getData() >= inicio && reuniao.getData() <= fim) {
				reunioes++;
			}
		}
		return reunioes;

	}

	public InfoReunioes pesquisarFrequencia(int codigoAssociado, int numAssociacao, long dataReuniao)
			throws FrequenciaIncompativel, SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "select * from frequencia where data = " + dataReuniao;
		InfoReunioes r = null;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		if (rs.next()) {
			long data = rs.getLong("data");
			int associado = rs.getInt("associado");
			int associacao = rs.getInt("associacao");
			if (data == dataReuniao && numAssociacao == associacao && codigoAssociado == associado) {
				r = new InfoReunioes(codigoAssociado, numAssociacao, dataReuniao);
			}
		}
		if (r == null) {
			throw new FrequenciaIncompativel();
		}
		st.close();
		return r;
	}

	@Override
	public void adicionar(int associacao, associacaoBD.Taxa t)
			throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido {
		// TODO Auto-generated method stub
		if (t.getVigencia() <= 0 || t.getValorAno() <= 0 || t.getNome() == null || t.getParcelas() <= 0
				|| (t.getNome().isEmpty() == true)) {
			throw new ValorInvalido();
		}
		try {
			pesquisarAssociacao(associacao);
		} catch (SQLException e) {
		} catch (AssociacaoNaoExistente e) {
			throw new AssociacaoNaoExistente();
		}
		try {
			pesquisarTaxa(t.getNome(), t.getVigencia());
			throw new TaxaJaExistente();
		} catch (SQLException e) {
		} catch (TaxaNaoExistente e) {
			try {
				Connection con = Conexao.getConexao();
				String cmd = "insert into taxa (nome, vigencia, valor, parcelas, administrativa, associacao) values ("
						+ "\'" + t.getNome() + "\', " + t.getVigencia() + ", " + t.getValorAno() + ", "
						+ t.getParcelas() + ", " + t.isAdministrativa() + ", " + associacao + ")";
				Statement st = con.createStatement();
				st.executeUpdate(cmd);
				st.close();
			} catch (Exception SQLException) {
			}
		}

	}

	public Taxa pesquisarTaxa(String nome1, int vigencia1) throws TaxaNaoExistente, SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "select * from taxa where vigencia = " + vigencia1;
		Taxa a = null;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		while (rs.next()) {
			String nome = rs.getString("nome");
			int vigencia = rs.getInt("vigencia");
			long valor = rs.getLong("valor");
			int parcelas = rs.getInt("parcelas");
			boolean administrativa = rs.getBoolean("administrativa");
			int associacao = rs.getInt("associacao");
			if (nome1.equals(nome) && vigencia1 == vigencia) {

				a = new Taxa(nome, vigencia, valor, parcelas, administrativa);
			}
		}
		st.close();
		if (a == null) {
			throw new TaxaNaoExistente();
		}
		return a;
	}

	@Override
	public void limparBanco() {
		try {
			Connection con = Conexao.getConexao();
			String cmd = "delete from reuniao";
			Statement st = con.createStatement();
			st.executeUpdate(cmd);
			cmd = "delete from frequencia";
			st.executeUpdate(cmd);
			cmd = "delete from associacao";
			st.executeUpdate(cmd);
			cmd = "delete from associado";
			st.executeUpdate(cmd);
			cmd = "delete from taxa";
			st.executeUpdate(cmd);
			st.close();
		} catch (Exception e) {
		}
	}

	public void remover(int n) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao where numero = " + n;
		Associacao a = null;
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

}
