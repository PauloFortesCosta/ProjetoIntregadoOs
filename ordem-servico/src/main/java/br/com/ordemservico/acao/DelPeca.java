package br.com.ordemservico.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ordemservico.model.DAO.PecaDAO;

public class DelPeca implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idPeca = Integer.valueOf(request.getParameter("idPeca"));
		Integer idServico = Integer.valueOf(request.getParameter("idServico"));
		
		if(idPeca != null) {
			new PecaDAO().deletar(idPeca);
		}
		
		return "PageDetalharServico&idServico=" + idServico;
	}

}
