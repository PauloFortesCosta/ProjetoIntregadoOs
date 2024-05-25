package br.com.ordemservico.acao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ordemservico.infra.Funcionalidades;
import br.com.ordemservico.model.Peca;
import br.com.ordemservico.model.Servico;
import br.com.ordemservico.model.DAO.PecaDAO;
import br.com.ordemservico.model.DAO.ServicoDAO;

public class PageDetalharServico implements Acao{
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Integer idServico = Integer.valueOf(request.getParameter("idServico"));
		
		Servico servico = new ServicoDAO().detalhar(idServico);
		
		servico.getCliente().setNumero(Funcionalidades.formatarNumeroTelefone(servico.getCliente().getNumero()));		
		
		request.setAttribute("servico", servico);
		
		List<Peca> pecas = new PecaDAO().listar(idServico);
		
		BigDecimal valorTotalPecas = BigDecimal.ZERO;
		
		for(Peca peca : pecas) {
			valorTotalPecas = valorTotalPecas.add(peca.getValor());
		}
		
		request.setAttribute("valorTotalPecas", valorTotalPecas);		
		
		
		request.setAttribute("pecas", pecas);
		
		
		
		return "forward:detalharServico.jsp";
	}

}
