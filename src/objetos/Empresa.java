package objetos;

public class Empresa {
	
	private int codigo;
	private String  nome;
	private String sigla;
	
	Empresa empresaSelecionada = null;
	
	
	
	
	public Empresa() {
	
	}
	
	public Empresa (Empresa emp){
		 empresaSelecionada = emp;
		
	}
	
	
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	
	
	

}
