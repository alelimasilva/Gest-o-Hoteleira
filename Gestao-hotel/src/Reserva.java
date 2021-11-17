import excecoes.CapacidadeException;
import excecoes.DataException;
import excecoes.OcupadoException;

public class Reserva {
	private boolean saida;
	private Cliente cliente;
	private Quartos quarto;
	private int data_entrada;
	private int data_saida;
	private int num_hospede;
	private float diaria;
	
	public Reserva(Cliente cliente, Quartos quarto, int data_entrada, int data_saida, int num_hospede, float diaria)
			throws DataException, CapacidadeException, OcupadoException {
		super();
		boolean validador  = true;
		if(quarto.isOcupado()) {
			throw new OcupadoException();
		}
		else {
			this.quarto = quarto;
		}
		if(num_hospede > (this.quarto.getCapacidade()+1)) {
			throw new CapacidadeException();// terminar essa exceçao 
		}
		if(this.quarto.isOcupado()) {
			validador = false; // e fazer essa
		}
		if(validador) {

			if(data_entrada < data_saida) {
				
				if(data_entrada >=1 && data_entrada <= 30) {
					this.data_entrada = data_entrada;
				}
				else {
					throw new DataException();
				}
				
				if(data_saida >=1 && data_saida <= 30) {
					this.data_saida = data_saida;
				}
				else {
					throw new DataException();
				}
			}
			else {
				throw new DataException();
			}
			this.saida = false;
			this.cliente = cliente;
			this.num_hospede = num_hospede;
			this.diaria = diaria;
		}
	}

	public boolean isSaida() {
		return saida;
	}

	public void setSaida(boolean saida) {
		this.saida = saida;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Quartos getQuarto() {
		return quarto;
	}


	public int getData_entrada() {
		return data_entrada;
	}


	public int getData_saida() {
		return data_saida;
	}



	public int getNum_hospede() {
		return num_hospede;
	}

	
	public float getDiaria() {
		return diaria;
	}
	
	public void print_reserva() {
		
		System.out.println("Quarto: " + this.quarto.retorna_categoria(this.quarto.getCategoria()) + 
							" -Data de entrada: " + this.getData_entrada() + " -Data de saida: " + this.getData_saida() +
							" -Hospedes: " + this.getNum_hospede());
	}
	
	
	
}
