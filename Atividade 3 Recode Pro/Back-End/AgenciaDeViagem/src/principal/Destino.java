package principal;

import java.util.Date;

public class Destino {
	private int id;
	private Contato contato;
	private Promocoe promocoe;
	private Date dataViagem;
	
	public Destino(Contato contato, Promocoe promocoe, Date dataViagem) {
		super();
		this.contato = contato;
		this.promocoe = promocoe;
		this.dataViagem = dataViagem;
	}

	public Destino() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Promocoe getPromocoe() {
		return promocoe;
	}

	public void setPromocoe(Promocoe promocoe) {
		this.promocoe = promocoe;
	}

	public Date getDataViagem() {
		return dataViagem;
	}

	public void setDataViagem(Date dataViagem) {
		this.dataViagem = dataViagem;
	}
	
	
	
	
	
	
	
}