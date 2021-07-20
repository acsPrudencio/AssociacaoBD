package associacaoBD;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

public class TesteDAOAssociacao {
	
	public void limpa() throws SQLException {
		DAOAssociacao dao = new DAOAssociacao();
		dao.removerTodos();
	}

	@Test
	public void testarIncluirRecuperarAssociacao() throws SQLException, ValorInvalido, AssociacaoJaExistente, AssociacaoNaoExistente {
		Associacao a = new Associacao(10, "Casa PASN");
		DAOAssociacao dao = new DAOAssociacao();
		limpa();
		dao.inserir(a);
		Associacao outra = dao.recuperar(10);
		assertEquals(10, outra.getNum());
		assertEquals("Casa PASN", outra.getNome());
	}
	@Test
	public void testarRecuperarAssociacaoNaoExistente() throws SQLException, ValorInvalido, AssociacaoJaExistente, AssociacaoNaoExistente {
		DAOAssociacao dao = new DAOAssociacao();
		Associacao outra;
		//Recuperar associacao nao existente
		try {
			outra = dao.recuperar(11);
			fail("Deveria ter dado erro");
		}catch(AssociacaoNaoExistente e) {
			// Ok, era pra ter dado erro!
		}
	}
	
	@Test
	public void testarRemoverAssociacaoX() throws SQLException, ValorInvalido, AssociacaoJaExistente, AssociacaoNaoExistente {
		DAOAssociacao dao = new DAOAssociacao();
		limpa();
		Associacao a = new Associacao(10, "Casa PASN");
		dao.inserir(a);
		dao.remover(10);
		try {
			Associacao a1 = dao.recuperar(10);
			assertEquals(10, a.getNum());
			assertEquals("Casa PASN", a.getNome());
			fail("Deveria ter dado erro");
		}catch(AssociacaoNaoExistente e) {
			// Ok, era pra ter dado erro!
		}
		
	}
}
