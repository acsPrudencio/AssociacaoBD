package associacaoBD;

public class Taxa {
	
	private String nome;
	private int vigencia;
	private double valorAno;
	private double restante;
	private int parcelas;
	private boolean administrativa;

	public  Taxa(String nome, int vigencia, double valorAno, int parcelas, boolean administrativa) {
		this.nome = nome;
		this.vigencia = vigencia;
		this.valorAno = valorAno;
		this.parcelas = parcelas;
		this.administrativa = administrativa;
		restante = valorAno;
		
	}
	
	public void setPagar(double valor) {
		restante = restante - valor;
	}
	
	public double getValorParcelas() {
		return valorAno/parcelas;
	}

	public double getRestante() {
		return restante;
	}

	public void setRestante(double restante) {
		this.restante = restante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

	public double getValorAno() {
		return valorAno;
	}

	public void setValorAno(double valorAno) {
		this.valorAno = valorAno;
	}

	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

	public boolean isAdministrativa() {
		return administrativa;
	}

	public void setAdministrativa(boolean administrativa) {
		this.administrativa = administrativa;
	}


	
	
}
