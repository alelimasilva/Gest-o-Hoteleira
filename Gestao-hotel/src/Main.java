import Util.Console;
import excecoes.CapacidadeException;
import excecoes.DataException;
import excecoes.OcupadoException;
public class Main {
	
	public static Hotel hotel = new Hotel();
	private static int codigo_cliente = 100, num_quartos = 0;
	
	public static void main(String[] args) {
		
		int opcao = -1;
		
	    do {
	    	System.out.println();
	        System.out.println(" 1 - Cadastrar cliente");
	        System.out.println(" 2 - Cadastrar quarto");
	        System.out.println(" 3 - Check-In");
	        System.out.println(" 4 - Check-out");	
	        System.out.println(" 5 - Consulta cliente");
	        System.out.println(" 6 - Consulta quarto");	
	        System.out.println(" 7 - Relatorio do Hotel");
	        System.out.println(" 0 - Sair");

	        System.out.println("Escolha uma opcao: ");
	        opcao = Integer.parseInt(Console.readLine());

	        switch ( opcao ) {
	            case 1 :
	            	System.out.println("Cadastar cliente.");
	            	cadastrar_cliente();
	                break;
	            case 2 :
	            	System.out.println("Cadastrar quarto.");
	            	cadastrar_quarto();
	                break;
	            case 3 :
	            	System.out.println("Check-In.");
	            	checkIn();
	                break;
	            case 4 :
	            	System.out.println("Check-out.");
	            	checkOut();
	                break;	          
	            case 5 :
	            	System.out.println("Consulta cliente.");
	            	consulta_cliente();
	                break;
	            case 6 :
	            	System.out.println("Consulta quarto.");
	            	consulta_quarto();
	                break;	          
	            case 7 :
	            	System.out.println("Relatorio do hotel.");   
	            	relatorio();
	        }
	    } while( opcao != 0 );	
	}

	public static void cadastrar_cliente() {
		System.out.println("Nome do cliente: ");
		String nome = Console.readLine();
		System.out.println("Telefone: ");
		String telefone = Console.readLine();
		Cliente cliente = new Cliente(nome, ++codigo_cliente, telefone);
		hotel.getClientes().add(cliente);
		System.out.println("Cliente cadastrado!");
		
		}
	
	public static void cadastrar_quarto() {
		System.out.println("Qual tipo de quarto deseja cadastrar?");
	    System.out.println(" 1 - Standard");
	    System.out.println(" 2 - Apartamento Vista Bosque");	
	    System.out.println(" 3 - Apartamento Vista Vale");
	    System.out.println(" 4 - Suite");
	    System.out.println("Escolha uma opcao: ");
	    int opcao = Integer.parseInt(Console.readLine());
	    Quartos quarto = new Quartos(++num_quartos, opcao);
	    hotel.getQuartos().add(quarto);
	    System.out.println("Quarto cadastrado!");
	}
	
	public static void checkIn() {
		
		if(hotel.getClientes().isEmpty()) {
			System.out.println("Não há clientes cadastrados.");
		}
		else {	
			hotel.listar_clientes();
			System.out.println("Digite o codigo do cliente: ");
			String cod = Console.readLine();
			if(hotel.getQuartos().isEmpty()) {
				System.out.println("Não há quartos cadastrados.");
			}
			else {		
				hotel.listar_quartos();
				System.out.println("Digite o codigo do quarto");
				String cod_quarto = Console.readLine();
				System.out.println("Digite a data de entrada: ");
				String data_entrada = Console.readLine();
				System.out.println("Digite a data de saida: ");
				String data_saida = Console.readLine();
				System.out.println("Quantos hospedes ocuparao o quarto?");
				String num_hospedes = Console.readLine();
			
				try {
					Reserva reserva = new Reserva(hotel.getClientes().get(Integer.parseInt(cod)-101), hotel.getQuartos().get(Integer.parseInt(cod_quarto)-1),
							Integer.parseInt(data_entrada), Integer.parseInt(data_saida), Integer.parseInt(num_hospedes), 
							hotel.getQuartos().get(Integer.parseInt(cod_quarto)-1).getValor_diaria());
					hotel.getReservas().add(reserva);
					hotel.getQuartos().get(Integer.parseInt(cod_quarto)-1).setOcupado(true);
					System.out.println("Check-In realizado com sucesso!");			
				}catch (OcupadoException e) {
					System.out.println("O quarto está ocupado!");
				}catch (DataException e) {
					System.out.println("\nCheckIn não altorizado!\nAs datas devem ser dias válidos entre 1 e 30.\nCom pelo menos 1 dia de estadia, com data de entrada menor que a data de saída.");
				}catch (CapacidadeException e) {
					System.out.println("\nCheckIn não altorizado!\nA capacidade não deve ultrapassar o limite do quarto com a cama extra.");
				}
			}
		}
	
	}
	
	public static void checkOut() {
		
		if(hotel.getQuartos().isEmpty()) {
			System.out.println("Não há quartos cadastrados");
		}
		else {
			int ocupados = 0;
			System.out.println("Quartos ocupados: ");
			for(Quartos quarto: hotel.getQuartos()) {
				if(quarto.isOcupado()) {
					ocupados++;
					System.out.println("Código " + quarto.getCodigo_ap() + " " + quarto.retorna_categoria(quarto.getCategoria()) + 
							" -Valor da diaria: R$" + quarto.getValor_diaria());
				}
			}
			if(ocupados == 0) {
				System.out.println("Não há nenhum quarto ocupado!");
			}
			else {
				System.out.println("Digite o codigo do quarto");
				int cod_quarto = Integer.parseInt(Console.readLine());
				for(Reserva reserva : hotel.getReservas()) { // pesquisa o quarto nas reservas
					if( reserva.getQuarto().getCodigo_ap() == cod_quarto  
						&& !reserva.isSaida()) { // se achar o codigo do quarto nas reservas e ele estiver ocupado faz checkout
						
						reserva.setSaida(true); // muda a saida para true
						hotel.getQuartos().get(cod_quarto-1).setOcupado(false); // e ocupado vira false
						int dias_totais = reserva.getData_saida() - reserva.getData_entrada();
						float valor_total = reserva.getDiaria() * dias_totais;
						if(reserva.getNum_hospede() == (reserva.getQuarto().getCapacidade()+1)) { // se tiver cama extra adiciona 30% no valor
							valor_total = valor_total + (valor_total * 0.3f);
						}
						hotel.setRenda_total(valor_total + hotel.getRenda_total()); // adiciona o valor na renda do hotel
						System.out.println("Nome: " + reserva.getCliente().getNome() + 
								" -Codigo: " + reserva.getCliente().getCodigo());
						System.out.println("Numero do quarto: " + reserva.getQuarto().getCodigo_ap() + 
								" -Tipo: " + reserva.getQuarto().retorna_categoria(reserva.getQuarto().getCategoria()) + 
								" -Valor da diaria: " + reserva.getQuarto().getValor_diaria());
						System.out.println("Data de entrada: " + reserva.getData_entrada() + " -Data de saida: " + reserva.getData_saida());
						System.out.println("Numero de hospedes: " + reserva.getNum_hospede());
						System.out.println("Numero de diarias: " + dias_totais + " -Valor total: R$" + valor_total);
					}
	
				}
			}
		}
	}
	
	public static void consulta_cliente() {
		for(Cliente cliente : hotel.getClientes()) {
			System.out.println();
			System.out.println("Nome: " + cliente.getNome() + " -Codigo: " + 
					cliente.getCodigo() + " -Telefone:" + cliente.getTelefone());
			System.out.println("Reservas ja feitas: ");
			for(Reserva reserva : hotel.getReservas()) { // pesquisa nas reservas, quais sao do cliente 
				if(reserva.getCliente().getCodigo() == cliente.getCodigo()) {
					reserva.print_reserva();
				}
			}
		}
	}
	
	public static void consulta_quarto() {
		for(Quartos quarto : hotel.getQuartos()) {
			System.out.println();
			System.out.println("Quarto " + quarto.getCodigo_ap() + " " + quarto.retorna_categoria(quarto.getCategoria()) + 
					" -Valor da diaria: R$" + quarto.getValor_diaria() + " -Ocupado: " + quarto.isOcupado());
			System.out.println("Historico de reservas: ");
			for(Reserva reserva : hotel.getReservas()) { // pesquisa nas reservas quais sao do quarto 
				if(quarto.getCodigo_ap() == reserva.getQuarto().getCodigo_ap()) {
					System.out.println("Hospede: " + reserva.getCliente().getNome());
					reserva.print_reserva();
				}
			}
		}
	}

	public static void relatorio() {
		System.out.println("Numero total de quartos: " + num_quartos);
		int ocupados = 0;
		for(Quartos quarto : hotel.getQuartos()) { // percorre os quartos para ver qual esta ocupado
			if(quarto.isOcupado()) ocupados++;			
		}
		System.out.println("Numero de quartos ocupados: " + ocupados);
		System.out.println("Renda total: R$" + ((Hotel) hotel).getRenda_total());
		
	}
}



