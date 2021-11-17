public class Quartos {
	private int codigo_ap;
	private int categoria;
	private float valor_diaria;
	private int capacidade;
	private boolean ocupado;
	
	public Quartos(int codigo_ap, int categoria) {
		this.codigo_ap = codigo_ap;
		this.categoria = categoria;
		this.ocupado = false;
		if(categoria == 1) {
			this.valor_diaria = 268.00f;
			this.capacidade = 2;
			
		}
		if(categoria == 2) {
			this.valor_diaria = 315.00f;
			this.capacidade = 4;
			
		}
		if(categoria == 3) { 
			this.valor_diaria = 353.00f;
			this.capacidade = 4;
			
		}
		if(categoria == 4) {
			this.valor_diaria = 498.00f;
			this.capacidade = 2;
			
		}	
	}

	public int getCodigo_ap() {
		return codigo_ap;
	}

	public int getCategoria() {
		return categoria;
	}

	public float getValor_diaria() {
		return valor_diaria;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public String retorna_categoria(int categoria) { // retorna em string o tipo de categoria
		if(this.categoria == 1) { 
			return "Standard";
		}
		if(this.categoria == 2) {
			return "Apartamento Vista Bosque";
		}
		if(this.categoria == 3) {
			return "Apartamento Vista Vale";
		}
		if(this.categoria == 4) {
			return "Suite";
		}
		else return "Invalido";
		
	}



	
}
