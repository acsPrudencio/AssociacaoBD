package associacaoBD;

public class InfoReunioes {
	
	private int codigoAssociado;
	private int numAssociacao;
	private long dataReuniao;
	
	
	
	public InfoReunioes(int codigoAssociado, int numAssociacao, long dataReuniao) {
		super();
		this.codigoAssociado = codigoAssociado;
		this.numAssociacao = numAssociacao;
		this.dataReuniao = dataReuniao;
	}

	public int getCodigoAssociado() {
		return codigoAssociado;
	}
	public void setCodigoAssociado(int codigoAssociado) {
		this.codigoAssociado = codigoAssociado;
	}
	public int getNumAssociacao() {
		return numAssociacao;
	}
	public void setNumAssociacao(int numAssociacao) {
		this.numAssociacao = numAssociacao;
	}
	public long getDataReuniao() {
		return dataReuniao;
	}
	public void setDataReuniao(long dataReuniao) {
		this.dataReuniao = dataReuniao;
	}

}
