package br.com.ordemservico.model.DTO;


import java.math.BigDecimal;

import br.com.ordemservico.infra.Funcionalidades;
import br.com.ordemservico.model.Cliente;
import br.com.ordemservico.model.Equipamento;
import br.com.ordemservico.model.Funcionario;
import br.com.ordemservico.model.Servico;

public record DadosDetalhaServico(Long id, Equipamento equipamento, Cliente cliente, Funcionario funcionario, String dt_inclusao, String dt_conclusao, String valor_servico, String observacao, String avaliacao, Boolean cod_finaliza_servico) {
	public DadosDetalhaServico(Servico servico) {
		this(servico.getId(), servico.getEquipamento(), servico.getCliente(), servico.getFuncionario(), Funcionalidades.retornaDataFormatada(servico.getDt_inclusao()), Funcionalidades.retornaDataFormatada(servico.getDt_conclusao()), Funcionalidades.retornaValorFormatado(servico.getValor_servico().toString()), servico.getObservacao(), servico.getAvaliacao(), servico.getCod_finaliza_servico());
	}
}
