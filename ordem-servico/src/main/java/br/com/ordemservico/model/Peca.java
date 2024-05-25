package br.com.ordemservico.model;

import java.math.BigDecimal;

public class Peca {
	private Long id;
	private Integer id_servico;
	private String nome;
	private BigDecimal valor;
	
	
public Peca() {}
	
	public Peca(String nome, Integer idServico, BigDecimal valor) {
		this.nome = nome;
		this.id_servico = idServico;
		this.valor = valor;
	}
	
	public void alterar(Long id,String nome, Integer idServico, BigDecimal valor) {
		this.id = id;
		
		if(nome != null && nome != "") {			
			this.nome = nome;
		}
		if(idServico != null) {			
			this.id_servico = idServico;
		}
		if(valor.compareTo(BigDecimal.ZERO) != 0) {			
			this.valor = valor;
		}
	}

	public Long getId() {
		return id;
	}

	public Integer getId_servico() {
		return id_servico;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
	
}
