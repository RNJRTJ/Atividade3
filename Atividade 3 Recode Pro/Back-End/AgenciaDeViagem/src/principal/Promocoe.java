package principal;

public class Promocoe {
	private int id;
	private String preco;
	private String viagem;
	
	public Promocoe(String preco, String viagem) {
		super();
		this.preco = preco;
		this.viagem = viagem;
	}

	public Promocoe() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getViagem() {
		return viagem;
	}

	public void setViagem(String viagem) {
		this.viagem = viagem;
	}
	
	
	
	
}