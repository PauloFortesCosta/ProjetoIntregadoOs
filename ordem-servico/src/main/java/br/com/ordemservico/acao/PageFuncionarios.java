package br.com.ordemservico.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ordemservico.model.Funcionario;
import br.com.ordemservico.model.DAO.FuncionarioDAO;

public class PageFuncionarios implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Funcionario> funcionarios;
		

		if(request.getParameter("search") != null && request.getParameter("search") != "") {	
			String nome = request.getParameter("search");
			funcionarios = new FuncionarioDAO().pesquisar(nome);
		} else {
			funcionarios = new FuncionarioDAO().listar();
		}
		
		request.setAttribute("funcionarios", funcionarios);
		
		return "forward:pageFuncionarios.jsp";
	}

}
