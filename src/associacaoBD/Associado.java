package associacaoBD;

public class Associado {
	
	private int numero;
	private String nome;
	private String telefone;
	private long dataAssociacao;
	private long nascimento;
	private int associacao;
	private boolean discriminador;

	public Associado(int numero, String nome, String telefone, long dataAssociacao, long nascimento) {
		this.numero = numero;
		this.nome = nome;
		this.telefone = telefone;
		this.dataAssociacao = dataAssociacao;
		this.nascimento = nascimento;
		
	}
	
	public Associado(int numero, String nome, String telefone, long dataAssociacao, long nascimento, int associacao) {
		this.numero = numero;
		this.nome = nome;
		this.telefone = telefone;
		this.dataAssociacao = dataAssociacao;
		this.nascimento = nascimento;
		this.associacao = associacao;
	}

	
	
	public boolean isDiscriminador() {
		return discriminador;
	}



	public void setDiscriminador(boolean discriminador) {
		this.discriminador = discriminador;
	}



	public int getAssociacao() {
		return associacao;
	}
	
	public void setAssociacao(int associacao) {
		this.associacao = associacao;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public long getDataAssociacao() {
		return dataAssociacao;
	}

	public void setDataAssociacao(long dataAssociacao) {
		this.dataAssociacao = dataAssociacao;
	}

	public long getNascimento() {
		return nascimento;
	}

	public void setNascimento(long nascimento) {
		this.nascimento = nascimento;
	}
	
	
}
