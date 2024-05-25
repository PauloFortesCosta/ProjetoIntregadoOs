package br.com.ordemservico.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Servico {
	private Long id;
	private Equipamento equipamento;
	private Cliente cliente;
	private Funcionario funcionario;
	private Date dt_inclusao;
	private Date dt_conclusao;
	private BigDecimal valor_servico;
	private String observacao;
	private String avaliacao;
	private Boolean cod_finaliza_servico;
	
	public Servico() {}	
	
	public Servico(Integer idEquipamento, Integer idCliente, Integer idFuncionario, BigDecimal valorServico, String observacao) {
		this.valor_servico = valorServico;
		if(observacao != null && observacao != "") {			
			this.observacao = observacao;
		}	
		this.cod_finaliza_servico = false;
	}
	
	public void alterar(Long id, Equipamento equipamento, Cliente cliente, Funcionario funcionario, Date dt_inclusao, Date dt_conclusao, BigDecimal valorServico, String observacao, String avaliacao, Boolean cod_finaliza_servico) {
		this.id = id;
		
		if(equipamento != null) {
			this.equipamento = equipamento;
		}
		
		if(cliente != null) {
			this.cliente = cliente;
		}
		
		if(funcionario != null) {
			this.funcionario = funcionario;
		}
		
		if(observacao != null && observacao != "") {			
			this.observacao = observacao;
		}	
		
		if(dt_inclusao != null) {			
			this.dt_inclusao = dt_inclusao;
		}
		
		if(dt_conclusao != null) {			
			this.dt_conclusao = dt_conclusao;
		}
		
		if(avaliacao != null && avaliacao != "") {			
			this.avaliacao = avaliacao;
		}
		
		if(valorServico.compareTo(BigDecimal.ZERO) != 0) {
			this.valor_servico = valorServico;
		}
		
		if(cod_finaliza_servico != null) {
			this.cod_finaliza_servico = cod_finaliza_servico;
		}	
	}

	public Boolean getCod_finaliza_servico() {
		return cod_finaliza_servico;
	}

	public Long getId() {
		return id;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public Date getDt_inclusao() {
		return dt_inclusao;
	}

	public Date getDt_conclusao() {
		return dt_conclusao;
	}

	public BigDecimal getValor_servico() {
		return valor_servico;
	}

	public String getObservacao() {
		return observacao;
	}

	public String getAvaliacao() {
		return avaliacao;
	}
	
	
}
