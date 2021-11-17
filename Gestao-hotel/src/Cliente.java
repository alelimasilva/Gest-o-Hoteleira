public class Cliente {
	private String nome;
	private int codigo;
	private String telefone; 
	
	public Cliente(String nome, int codigo, String telefone) {
		this.nome = nome;
		this.codigo = codigo;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getTelefone() {
		return telefone;
	}
	
	
}
