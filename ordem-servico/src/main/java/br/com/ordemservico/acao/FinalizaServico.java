package br.com.ordemservico.acao;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ordemservico.model.DAO.ServicoDAO;

public class FinalizaServico implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer atualizarValor = Integer.valueOf(request.getParameter("atualizar_servico"));
		Integer idServico = Integer.valueOf(request.getParameter("id_servico"));
		BigDecimal valor_servico = new BigDecimal(request.getParameter("valor_servico"));
		
		if(atualizarValor != null && atualizarValor == 1) {
			valor_servico = new BigDecimal(request.getParameter("valor_total_servico"));
		}
		
		new ServicoDAO().finalizarServico(idServico, valor_servico);
		return "PageServicos";
	}

}
