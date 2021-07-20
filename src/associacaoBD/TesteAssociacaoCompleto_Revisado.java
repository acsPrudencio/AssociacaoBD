package associacaoBD;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class TesteAssociacaoCompleto_Revisado {

	@Test
	public void testarCadastroDeAssociacao() throws AssociacaoJaExistente, ValorInvalido {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "");
		a1.setNome("Cruzeiro do Sul V");
		controle.adicionar(a1);

		try {
			controle.adicionar(a1);
			fail("Deveria ter dado erro! Cadastro de mesma associacao");
		} catch (AssociacaoJaExistente e) {
			// Ok, era pra dar erro mesmo!
		}
	}

	@Test
	public void testarCadastroDeAssociacaoComNomeVazio() throws AssociacaoJaExistente, ValorInvalido {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();

		Associacao a1 = new Associacao(1306, "");

		// Tentativa de cadastrar com sem nome
		try {
			controle.adicionar(a1);
			fail("Deveria ter dado erro! Associacao sem nome");
		} catch (ValorInvalido e) {
			// Ok. Era pra dar erro mesmo!
		}
	}

	@Test
	public void testarCadastroDeAssociacaoComNomeNulo() throws AssociacaoJaExistente, ValorInvalido {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, null);

		// Tentativa de cadastrar com sem nome
		try {
			controle.adicionar(a1);
			fail("Deveria ter dado erro! Associacao nome nulo");
		} catch (ValorInvalido e) {
			// Ok. Era pra dar erro mesmo!
		}
	}

	@Test
	public void testarCadastroDeAssociacaoComNumeroMenorQueZero() throws AssociacaoJaExistente, ValorInvalido {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(-10, "Cruzeiro do Sul V");

		// Tentativa de cadastrar com numero 0
		try {
			controle.adicionar(a1);
			fail("Deveria ter dado erro! Associacao numero -10");
		} catch (ValorInvalido e) {
			// Ok. Era pra dar erro mesmo!
		}
	}

	@Test
	public void testarCadastroDeAssociado()
			throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		Date hoje = new Date();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", hoje.getTime(), nasc);
		controle.adicionar(1306, associado1);

		try {
			controle.adicionar(1306, associado1);
			fail("Era pra ter dado erro! Cadastro de mesmo associado");
		} catch (AssociadoJaExistente e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeAssociadoSemNome()
			throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		Date hoje = new Date();
		Associado associado1 = new Associado(1, "", "3232-3232", hoje.getTime(), nasc);

		try {
			controle.adicionar(1306, associado1);
			fail("Era pra ter dado erro! Cadastro de associado sem nome");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeAssociadoComNomeNulo()
			throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		Date hoje = new Date();
		Associado associado1 = new Associado(1, null, "3232-3232", hoje.getTime(), nasc);

		try {
			controle.adicionar(1306, associado1);
			fail("Era pra ter dado erro! Cadastro de associado com nome nulo");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeAssociadoSemTelefone()
			throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		Date hoje = new Date();
		Associado associado1 = new Associado(1, "Pedro", "", hoje.getTime(), nasc);

		try {
			controle.adicionar(1306, associado1);
			fail("Era pra ter dado erro! Cadastro de associado sem telefone");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeAssociadoComTelefoneNulo()
			throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		Date hoje = new Date();
		Associado associado1 = new Associado(1, "Pedro", null, hoje.getTime(), nasc);

		try {
			controle.adicionar(1306, associado1);
			fail("Era pra ter dado erro! Cadastro de associado com telefone nulo");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeAssociadoComNumeroMenorQueZero()
			throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		Date hoje = new Date();
		Associado associado1 = new Associado(-10, "Pedro", "3232-3232", hoje.getTime(), nasc);

		try {
			controle.adicionar(1306, associado1);
			fail("Era pra ter dado erro! Cadastro de associado com numero -10");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeAssociadoComDataNascMenorQueZero()
			throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		Date hoje = new Date();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", hoje.getTime(), -10);

		try {
			controle.adicionar(1306, associado1);
			fail("Era pra ter dado erro! Cadastro de associado com data nasc igual -10");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeAssociadoComDataAssociacaoMenorQueZero()
			throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", -10, nasc);

		try {
			controle.adicionar(1306, associado1);
			fail("Era pra ter dado erro! Cadastro de associado com data associacao igual -10");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeAssociadoComAssociacaoInexistente() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		Date hoje = new Date();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", hoje.getTime(), nasc);

		try {
			controle.adicionar(1307, associado1);
			fail("Deveria ter dado erro associado em associacao que nao existe!");
		} catch (AssociacaoNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok erro cadastro de associado em associacao inexistente!");
		}

	}

	@Test
	public void testarCadastroDeReuniao()
			throws AssociacaoNaoExistente, ValorInvalido, ReuniaoJaExistente, AssociacaoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(2021, 01, 01);
		long dataReuni = gc.getTimeInMillis();

		Reuniao reuniao1 = new Reuniao(dataReuni, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);
		try {
			controle.adicionar(1306, reuniao1);
			fail("Era pra ter dado erro! Cadastro de mesma reuniao");
		} catch (ReuniaoJaExistente e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeReuniaoComDataMenorQueZero()
			throws AssociacaoNaoExistente, ValorInvalido, ReuniaoJaExistente, AssociacaoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(2021, 01, 01);

		Reuniao reuniao1 = new Reuniao(-10, "Aumento de taxas");

		try {
			controle.adicionar(1306, reuniao1);
			fail("Era pra ter dado erro! Cadastro de reuniao com data = -10");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeReuniaoSemPauta()
			throws AssociacaoNaoExistente, ValorInvalido, ReuniaoJaExistente, AssociacaoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(2021, 01, 01);
		long dataReuni = gc.getTimeInMillis();

		Reuniao reuniao1 = new Reuniao(dataReuni, "");

		try {
			controle.adicionar(1306, reuniao1);
			fail("Era pra ter dado erro! Cadastro de reuniao sem pauta");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeReuniaoComPautaNulo()
			throws AssociacaoNaoExistente, ValorInvalido, ReuniaoJaExistente, AssociacaoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(2021, 01, 01);
		long dataReuni = gc.getTimeInMillis();
		Reuniao reuniao1 = new Reuniao(dataReuni, null);

		try {
			controle.adicionar(1306, reuniao1);
			fail("Era pra ter dado erro! Cadastro de reuniao com pauta nulo");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void cadastroDeReuniaoEmAssociacaoNaoExistente() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(2021, 01, 01);
		long dataReuni = gc.getTimeInMillis();

		Reuniao reuniao1 = new Reuniao(dataReuni, "Aumento de taxas");

		try {
			controle.adicionar(1000, reuniao1);
			fail("Tinha que dar erro cadastro de reuniao sem associacao");
		} catch (AssociacaoNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok erro reuniao sem associacao!");
		}

	}

	@Test
	public void testarCadastroDeTaxa()
			throws AssociacaoNaoExistente, AssociacaoJaExistente, ValorInvalido, TaxaJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		Taxa taxa1 = new Taxa("ManutenâˆšÃŸâˆšÂ£o", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);
		try {
			controle.adicionar(1306, taxa1);
			fail("Era pra ter dado erro! Cadastro de mesma taxa");
		} catch (TaxaJaExistente e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeTaxaComValorZero()
			throws AssociacaoNaoExistente, AssociacaoJaExistente, ValorInvalido, TaxaJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		Taxa taxa1 = new Taxa("ManutenâˆšÃŸâˆšÂ£o", 2021, 0, 12, true);

		try {
			controle.adicionar(1306, taxa1);
			fail("Era pra ter dado erro! Cadastro de taxa com valor 0");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeTaxaComVigenciaZero()
			throws AssociacaoNaoExistente, AssociacaoJaExistente, ValorInvalido, TaxaJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		Taxa taxa1 = new Taxa("ManutenâˆšÃŸâˆšÂ£o", -10, 600, 12, true);

		try {
			controle.adicionar(1306, taxa1);
			fail("Era pra ter dado erro! Cadastro de taxa com vigencia -10");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeTaxaComParcelasZero()
			throws AssociacaoNaoExistente, AssociacaoJaExistente, ValorInvalido, TaxaJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		Taxa taxa1 = new Taxa("ManutenâˆšÃŸâˆšÂ£o", 2021, 600, 0, true);

		try {
			controle.adicionar(1306, taxa1);
			fail("Era pra ter dado erro! Cadastro de taxa com parcelas 0");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeTaxaSemNome()
			throws AssociacaoNaoExistente, AssociacaoJaExistente, ValorInvalido, TaxaJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		Taxa taxa1 = new Taxa("", 2021, 600, 12, true);

		try {
			controle.adicionar(1306, taxa1);
			fail("Era pra ter dado erro! Cadastro de taxa sem nome");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeTaxaComNomeNulo()
			throws AssociacaoNaoExistente, AssociacaoJaExistente, ValorInvalido, TaxaJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		Taxa taxa1 = new Taxa(null, 2021, 600, 12, true);

		try {
			controle.adicionar(1306, taxa1);
			fail("Era pra ter dado erro! Cadastro de taxa com nome nulo");
		} catch (ValorInvalido e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void cadastroDeTaxaSemAssociacao() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Taxa taxa1 = new Taxa("ManutenâˆšÃŸâˆšÂ£o", 2021, 600, 12, true);

		try {
			controle.adicionar(1, taxa1);
			fail("erro cadastro taxa associacao nao existente");
		} catch (AssociacaoNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok erro taxa sem associacao");
		}

	}

	@Test
	public void testarCalculoDeFrequencia() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		Reuniao reuniao1 = new Reuniao(data1, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);
		controle.registrarFrequencia(1, 1306, data1);

		Reuniao reuniao2 = new Reuniao(data1 + 1000000, "ConfirmaâˆšÃŸâˆšÂ£o de aumento");
		controle.adicionar(1306, reuniao2);

		double freq = controle.calcularFrequencia(1, 1306, data1, data1 + 1000000);
		assertEquals(0.5, freq, 0.01);
	}

	@Test
	public void frequenciaDeAssociadoNaoExistente() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();

		Reuniao reuniao1 = new Reuniao(data1, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);

		try {
			controle.registrarFrequencia(3, 1306, data1);
			fail("erro frequencia de associado nao existente");
		} catch (AssociadoNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok erro frequencia de associado que nao existe!");
		}

	}

	// Obs.: O mÃˆtodo anterior testa com um associado que nâ€žo existe de maneira
	// alguma, nem nesta nem em nenhuma outra associaÃ�â€žo.
	// JÂ· este testa com um associado que nâ€žo existe nesta associaÃ�â€žo na qual
	// tentarÂ· registrar a frequÃ�ncia, mas existe em outra, o que deverÂ· gerar
	// que o associado nâ€žo existe da mesma forma do teste anterior.
	@Test
	public void frequenciaDeAssociadoNaoExistente2() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		Associacao a2 = new Associacao(1307, "Cruzeiro do Sul V2");

		controle.adicionar(a1);
		controle.adicionar(a2);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado a = new Associado(1, "Fulano", "1111-1111", data1, nasc);
		controle.adicionar(1307, a);
		Reuniao reuniao1 = new Reuniao(data1, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);

		try {
			controle.registrarFrequencia(1, 1306, data1);
			fail("erro frequencia de associado nao existente nesta associacao");
		} catch (AssociadoNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok erro frequencia de associado que nao existe nesta associacao!");
		}

	}

	@Test
	public void testeFrequenciaDeAssociacaoQueNaoExiste() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		Reuniao reuniao1 = new Reuniao(data1, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);

		try {
			controle.registrarFrequencia(1, 1000, data1);
			fail("Frequencia de associacao que nao existe!");
		} catch (AssociacaoNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok erro frequencia de associacao que nao existe!");
		}

	}

	@Test
	public void frequenciaDeReuniaoQueNaoExiste() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		Reuniao reuniao1 = new Reuniao(data1, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);

		try {
			controle.registrarFrequencia(1, 1306, data1 + 100000);
			fail("Frequencia em reuniao nao existente!");
		} catch (ReuniaoNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok erro frequencia de reuniao nao existente!");
		}

	}

	@Test
	public void testeFrequenciaIncompativel() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		Reuniao reuniao1 = new Reuniao(data1, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);

		try {
			controle.registrarFrequencia(1, 1306, data1 - 10000);
			fail("Frequemcia incompativel");
		} catch (ReuniaoNaoExistente | FrequenciaIncompativel e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok frequencia incompativel!");
		}

	}

	@Test
	public void testeFrequenciaJaRegistrada() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		Reuniao reuniao1 = new Reuniao(data1, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);

		controle.registrarFrequencia(1, 1306, data1);

		try {
			controle.registrarFrequencia(1, 1306, data1);
			fail("Frequencia ja registrada!");
		} catch (FrequenciaJaRegistrada e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok frequencia ja registrada!");
		}

	}

	@Test
	public void testarPagamento() throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente,
			AssociacaoJaExistente, ValorInvalido, AssociadoJaExistente, TaxaJaExistente, AssociadoJaRemido {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// RemissâˆšÂ£o em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("ManutenâˆšÃŸâˆšÂ£o", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("Reforma", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);

		Date hoje = new Date();

		try {
			controle.registrarPagamento(1306, "ManutenâˆšÃŸâˆšÂ£o", 2021, 1, hoje.getTime(), 49);
			fail("NâˆšÂ£o deveria deixar pagar esse valor, pois 49 estÂ· abaixo do valor da parcela, que deve ser o valor mÃŒnimo a ser pago");
		} catch (ValorInvalido e) {
			// Ok. Barrou pagamento abaixo da parcela!
		}
		controle.registrarPagamento(1306, "ManutenâˆšÃŸâˆšÂ£o", 2021, 1, hoje.getTime(), 60);
		controle.registrarPagamento(1306, "Reforma", 2021, 1, hoje.getTime(), 100);

		double valor = controle.somarPagamentoDeAssociado(1306, 1, "ManutenâˆšÃŸâˆšÂ£o", 2021, hoje.getTime(),
				hoje.getTime());
		assertEquals(60, valor, 0.01);
		double valor2 = controle.somarPagamentoDeAssociado(1306, 1, "Reforma", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(100, valor2, 0.01);

		try {
			controle.registrarPagamento(1306, "ManutenâˆšÃŸâˆšÂ£o", 2021, 2, hoje.getTime(), 60);
			fail("NâˆšÂ£o deveria deixar pagar esse valor, pois este associado Ãˆ remido");
		} catch (AssociadoJaRemido e) {
			// Ok. Barrou pagamento de taxa adminsitrativa!
		}

		controle.registrarPagamento(1306, "Reforma", 2021, 2, hoje.getTime(), 100);
		valor = controle.somarPagamentoDeAssociado(1306, 2, "ManutenâˆšÃŸâˆšÂ£o", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(0, valor, 0.01);
		valor2 = controle.somarPagamentoDeAssociado(1306, 2, "Reforma", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(100, valor2, 0.01);
		controle.registrarPagamento(1306, "Reforma", 2021, 2, hoje.getTime(), 1080);
		valor2 = controle.somarPagamentoDeAssociado(1306, 2, "Reforma", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(1180, valor2, 0.01);
		controle.registrarPagamento(1306, "Reforma", 2021, 2, hoje.getTime(), 20);
		valor2 = controle.somarPagamentoDeAssociado(1306, 2, "Reforma", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(1200, valor2, 0.01);
	}

	@Test
	public void testaPagamentoEmAssociacaoNaoExistente() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);
		// RemissâˆšÂ£o em 01/03/2021
		gc.set(2021, 02, 01);
		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		try {
			controle.registrarPagamento(1307, "A", 2021, 1, data1, 70);
			fail("Pagamento de associacao nao existente");
		} catch (AssociacaoNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok pagamento de associacao nao existente!");
		}

	}

	@Test
	public void testaPagamentoDeAssociadoNaoExistente() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);
		// RemissâˆšÂ£o em 01/03/2021
		gc.set(2021, 02, 01);
		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		try {
			controle.registrarPagamento(1306, "A", 2021, 2, data1, 70);
			fail("Pagamento de associado nao existente");
		} catch (AssociadoNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok pagamento de associado nao existente!");
		}

	}

	@Test
	public void testaPagamentoDeTaxaNaoExistente() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);
		// RemissâˆšÂ£o em 01/03/2021
		gc.set(2021, 02, 01);
		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		try {
			controle.registrarPagamento(1306, "B", 2021, 1, data1, 70);
			fail("Pagamento de taxa nao existente");
		} catch (TaxaNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok pagamento de taxa nao existente!");
		}

	}

	@Test
	public void testeCalcularFrequenciaDeAssociadoNaoExistente() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);
		// RemissâˆšÂ£o em 01/03/2021
		gc.set(2021, 02, 01);
		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		try {
			controle.calcularFrequencia(10, 1306, data1, data1 + 10000);
			fail("Calculo de associado nao existente");
		} catch (AssociadoNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok calculofrequencia  de associado nao existente!");
		}

	}

	@Test
	public void testeCalcularFrequenciaDeAssociacaoNaoExistente() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);
		// RemissâˆšÂ£o em 01/03/2021
		gc.set(2021, 02, 01);
		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		try {
			controle.calcularFrequencia(1, 1307, data1, data1 + 10000);
			fail("Calculo de associacao nao existente");
		} catch (AssociacaoNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok calculo frequencia  de associacao nao existente!");
		}

	}

	@Test
	public void testeCalcularTotalDeTaxa() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		Taxa taxa2 = new Taxa("B", 2021, 100, 12, true);
		controle.adicionar(1306, taxa1);
		controle.adicionar(1306, taxa2);
		assertEquals(700, controle.calcularTotalDeTaxas(1306, 2021), 0.001);
	}

	@Test
	public void testeCalcularTotalDeTaxa2() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		Taxa taxa2 = new Taxa("B", 2021, 100, 12, true);
		Taxa taxa3 = new Taxa("C", 2017, 100, 12, true);

		controle.adicionar(1306, taxa1);
		controle.adicionar(1306, taxa2);
		controle.adicionar(1306, taxa3);
		assertEquals(700, controle.calcularTotalDeTaxas(1306, 2021), 0.001);
	}

	@Test
	public void testeCalcularTotalDeTaxaDeAssociacaoNaoExistente() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		Taxa taxa2 = new Taxa("B", 2021, 100, 12, true);
		Taxa taxa3 = new Taxa("C", 2017, 100, 12, true);

		controle.adicionar(1306, taxa1);
		controle.adicionar(1306, taxa2);
		controle.adicionar(1306, taxa3);

		try {
			controle.calcularTotalDeTaxas(1, 2021);
			fail("Calculo de taxa de associacao nao existente!");
		} catch (AssociacaoNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok erro calculo total de taxa de associacao nao existente!");
		}

	}

	@Test
	public void testarPagamentoAbaixoDaParcela() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissâ€žo em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("ManutenÃ�â€žo", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("Reforma", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		Date hoje = new Date();

		try {
			controle.registrarPagamento(1306, "ManutenÃ�â€žo", 2021, 1, hoje.getTime(), 49);
			fail("Nâ€žo deveria deixar pagar esse valor, pois 49 estÂ· abaixo do valor da parcela, que deve ser o valor mÃŒnimo a ser pago");
		} catch (ValorInvalido e) {
			// Ok. Barrou pagamento abaixo da parcela!
		}

	}

	@Test
	public void testeSomarPagamentosDeAssociado() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissâ€žo em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("B", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		Date hoje = new Date();

		controle.registrarPagamento(1306, "A", 2021, 1, hoje.getTime(), 60);
		controle.registrarPagamento(1306, "B", 2021, 1, hoje.getTime(), 100);

		double valor = controle.somarPagamentoDeAssociado(1306, 1, "A", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(60, valor, 0.01);

	}

	@Test
	public void testeSomarPagamentosDeAssociado2() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissâ€žo em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("B", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		Date hoje = new Date();

		controle.registrarPagamento(1306, "A", 2021, 1, hoje.getTime(), 60);
		controle.registrarPagamento(1306, "B", 2021, 1, hoje.getTime(), 100);

		double valor = controle.somarPagamentoDeAssociado(1306, 1, "B", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(100, valor, 0.01);

	}

	@Test
	public void testePagamentoDeAssociadoRemido() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissâ€žo em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("B", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		Date hoje = new Date();

		try {
			controle.registrarPagamento(1306, "A", 2021, 2, hoje.getTime(), 60);
			fail("Nâ€žo deveria deixar pagar esse valor, pois este associado Ãˆ remido");
		} catch (AssociadoJaRemido e) {
			// Ok. Barrou pagamento de taxa adminsitrativa!
		}

	}

	@Test
	public void testePagamentoDeAssociadoRemidoSemSerAdministrativa() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissâ€žo em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("B", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		Date hoje = new Date();

		try {
			controle.registrarPagamento(1306, "B", 2021, 2, hoje.getTime(), 100);
		} catch (AssociadoJaRemido e) {
			fail("Nâ€žo deveria ter barrado, pois nâ€žo Ãˆ administrativa!");
		}

	}

	@Test
	public void testeTotalDePagamentoDeAssociadoRemido() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissâ€žo em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("B", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		Date hoje = new Date();

		controle.registrarPagamento(1306, "B", 2021, 2, hoje.getTime(), 100);
		double valor = controle.somarPagamentoDeAssociado(1306, 2, "A", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(0, valor, 0.01);

	}

	@Test
	public void testeTotalDePagamentoDeAssociadoRemido2() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissâ€žo em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("B", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		Date hoje = new Date();

		controle.registrarPagamento(1306, "B", 2021, 2, hoje.getTime(), 100);
		double valor = controle.somarPagamentoDeAssociado(1306, 2, "B", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(100, valor, 0.01);
	}

	@Test
	public void testePagarOQueFaltaMenorQueOValorDaParcela() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissâ€žo em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("B", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		Date hoje = new Date();

		controle.registrarPagamento(1306, "B", 2021, 2, hoje.getTime(), 100);
		controle.registrarPagamento(1306, "B", 2021, 2, hoje.getTime(), 1080);

		try {
			controle.registrarPagamento(1306, "B", 2021, 2, data1, 20);
		} catch (ValorInvalido e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			fail("O valor Ãˆ menor do que a parcela, mas deveria aceitar, uma vez que Ãˆ sÃ› o que falta para quitar o ano!");
		}

	}

	@Test
	public void testeTotalDePagamentosTudo() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		controle.limparBanco();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissâ€žo em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("B", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		Date hoje = new Date();

		controle.registrarPagamento(1306, "B", 2021, 2, hoje.getTime(), 100);
		controle.registrarPagamento(1306, "B", 2021, 2, hoje.getTime(), 1080);
		controle.registrarPagamento(1306, "B", 2021, 2, hoje.getTime(), 20);
		double valor = controle.somarPagamentoDeAssociado(1306, 2, "B", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(1200, valor, 0.01);
	}

}