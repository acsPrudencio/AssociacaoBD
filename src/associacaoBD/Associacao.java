package associacaoBD;

public class Associacao {

	private String nome;
	private int num;
	
	public Associacao(int num, String nome){
		this.num = num;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	

}
