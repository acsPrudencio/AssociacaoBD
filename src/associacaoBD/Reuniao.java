package associacaoBD;

public class Reuniao {
	
	private long data;
	private String ata;
	private int associacao;

	public Reuniao(long data, String ata) {
		this.data = data;
		this.ata = ata;
	}
	
	public Reuniao(long data, String ata, int associacao) {
		this.data = data;
		this.ata = ata;
		this.associacao = associacao;
	}

	
	public int getAssociacao() {
		return associacao;
	}

	//Bug do eclipse
	public void setAssociacao(int associacao) {
		this.associacao = associacao;
	}

	public void setAssociacao1(int associacao2) {
		// TODO Auto-generated method stub
		this.associacao = associacao;
	}
	// So funciona se deixar os dois, nao entendi o motivo
	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}

	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}


	
	
}
