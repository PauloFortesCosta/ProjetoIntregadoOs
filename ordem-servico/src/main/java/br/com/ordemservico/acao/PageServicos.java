package br.com.ordemservico.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ordemservico.model.Servico;
import br.com.ordemservico.model.DAO.ServicoDAO;

public class PageServicos implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<Servico> servicos;
		if(request.getParameter("search") != null && request.getParameter("search") != "") {	
			String nome = request.getParameter("search");
			servicos = new ServicoDAO().pesquisar(nome);
		} else if(request.getParameter("status_servico") != null && request.getParameter("status_servico") != "") {			
			Integer statusServico = Integer.valueOf(request.getParameter("status_servico"));
			
			if(statusServico != null && statusServico == 1) {			
				servicos = new ServicoDAO().pesquisarPorStatus(true);
			} else {
				servicos = new ServicoDAO().pesquisarPorStatus(false);
			}
		} else {
			servicos = new ServicoDAO().listar();
		}
		
		
		
		
		request.setAttribute("servicos", servicos);
		
		
		return "forward:pageServicos.jsp";
	}

}
