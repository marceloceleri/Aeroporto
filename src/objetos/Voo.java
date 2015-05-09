package objetos;

import java.util.Date;

public class Voo {
	
	private int codigo;
	private String origem;
	private int codigoAviao;
	private int codigoPiloto;
	private Date dataChegada;
	private Date dataSaida;
	private String Destino;
	
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public int getCodigoAviao() {
		return codigoAviao;
	}
	public void setCodigoAviao(int codigoAviao) {
		this.codigoAviao = codigoAviao;
	}
	public int getCodigoPiloto() {
		return codigoPiloto;
	}
	public void setCodigoPiloto(int codigoPiloto) {
		this.codigoPiloto = codigoPiloto;
	}
	public Date getDataChegada() {
		return dataChegada;
	}
	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	public String getDestino() {
		return Destino;
	}
	public void setDestino(String destino) {
		Destino = destino;
	}
	
	
	
	

}
