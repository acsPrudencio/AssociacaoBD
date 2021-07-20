package associacaoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOAssociacao extends MinhaAssociacao{

	public void inserir(Associacao a) throws SQLException, ValorInvalido, AssociacaoJaExistente {
		Connection con = Conexao.getConexao();
		//insert into associacao (numero, nome) values (1, 'ADUFPI')
		if (a.getNome() == null || a.getNum() <= 0 || (a.getNome().isEmpty() == true)) {
			throw new ValorInvalido();
		}
		try {
			recuperar(a.getNum());
			throw new AssociacaoJaExistente();
		} catch (AssociacaoNaoExistente e) {
			String cmd = "insert into associacao (numero, nome) values (" 
				     + a.getNum() + ", \'" + a.getNome() + "\')";
				Statement st = con.createStatement();
			    st.executeUpdate(cmd);
			    st.close();
		}
		
	}

	public void alterar(Associacao a) {
		
	}

	public void remover(int n) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao where numero = " + n;
		Associacao a = null;
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}
	
	public void removerTodos() throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao";
		System.out.println(cmd);
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
	}

	public Associacao recuperar(int n) throws SQLException, AssociacaoNaoExistente {
		Connection con = Conexao.getConexao();
		//select * from associacao where numero = 2
		String cmd = "select * from associacao where numero = " + n;
		System.out.println(cmd);
		Associacao a = null;
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(cmd);
	    if (rs.next()) {
	    	int num  = rs.getInt("numero");
	    	String nome = rs.getString("nome");
	    	a = new Associacao(num, nome);
	    }
	    if(a == null) {
	    	throw new AssociacaoNaoExistente();
	    }
	    st.close();
	    return a;
	}
	
	public ArrayList<Associacao> recuperarTodos() throws SQLException {
		ArrayList<Associacao> ListaDeAssociacoes = new ArrayList<Associacao>();
		
		try {
			Connection con = Conexao.getConexao();
			Statement st = con.createStatement();
			String cmd = "select * from associacao";
			System.out.println(cmd);
			ResultSet rs = st.executeQuery(cmd);
			System.out.println(rs.next());
			while (rs.next()) {
				int num  = rs.getInt("numero");
		    	String nome = rs.getString("nome");
				ListaDeAssociacoes.add(new Associacao(num, nome));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ListaDeAssociacoes;
	}	

}
