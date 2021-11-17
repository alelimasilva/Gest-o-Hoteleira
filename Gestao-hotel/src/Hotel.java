import java.util.List;
import java.util.ArrayList;

public class Hotel {
	private float renda_total;
	private List<Quartos> quartos;
	private List<Cliente> clientes;
	private List<Reserva> reservas;

	public Hotel() {
		this.renda_total = 0;
		this.quartos = new ArrayList<Quartos>();
		this.clientes = new ArrayList<Cliente>();
		this.reservas = new ArrayList<Reserva>();
	}
	
	
	public float getRenda_total() {
		return renda_total;
	}


	public void setRenda_total(float renda_total) {
		this.renda_total = renda_total;
	}
	


	public List<Quartos> getQuartos() {
		return this.quartos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}
	
	public void listar_clientes() { // imprime informações de todos os clientes cadstrados
		System.out.println();
		for(Cliente cliente : clientes) {
			System.out.println("Nome: " + cliente.getNome() + " -Telefone: " + cliente.getTelefone()
					+ " - Codigo: " + cliente.getCodigo());
		}
		System.out.println();
	}
	
	public void listar_quartos() { // imprime informações de todos os quartos
		
		System.out.println();
		for(Quartos quarto: quartos) {
			System.out.println("Código " + quarto.getCodigo_ap() + " " + quarto.retorna_categoria(quarto.getCategoria()) + 
					" -Valor da diaria: R$" + quarto.getValor_diaria() + " -Ocupado: " + quarto.isOcupado());
		}
		System.out.println();
	}
}
