package br.com.ordemservico.model;

public class Equipamento {
	private Long id;
	private String modelo;
	private String cor;
	private String marca;
	
	public Equipamento() {}
	
	public Equipamento(String modelo, String cor, String marca) {
		this.marca = marca;
		this.cor = cor;
		this.modelo = modelo;
	}
	
	public void alterar(Long id, String modelo, String cor, String marca) {
		this.id = id;
		
		if(modelo != null && modelo != "") {			
			this.modelo = modelo;
		}
		if(cor != null && cor != "") {			
			this.cor = cor;
		}
		if(marca != null && marca != "") {			
			this.marca = marca;
		}
	}

	public Long getId() {
		return id;
	}

	public String getModelo() {
		return modelo;
	}

	public String getCor() {
		return cor;
	}

	public String getMarca() {
		return marca;
	}
	
	

}
