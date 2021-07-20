package associacaoBD;

public class TaxasPagas {

	private int numAssociacao;
	private String taxa;
	private int vigencia;
	private int numAssociado;
	private long data;
	private double valor;
	
	public TaxasPagas(int numAssociacao, String taxa, int vigencia, int numAssociado, long data, double valor) {
		super();
		this.numAssociacao = numAssociacao;
		this.taxa = taxa;
		this.vigencia = vigencia;
		this.numAssociado = numAssociado;
		this.data = data;
		this.valor = valor;
	}

	public int getNumAssociacao() {
		return numAssociacao;
	}

	public void setNumAssociacao(int numAssociacao) {
		this.numAssociacao = numAssociacao;
	}

	public String getTaxa() {
		return taxa;
	}

	public void setTaxa(String taxa) {
		this.taxa = taxa;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

	public int getNumAssociado() {
		return numAssociado;
	}

	public void setNumAssociado(int numAssociado) {
		this.numAssociado = numAssociado;
	}

	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
