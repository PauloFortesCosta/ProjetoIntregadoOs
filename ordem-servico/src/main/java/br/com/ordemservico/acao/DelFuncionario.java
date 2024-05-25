package br.com.ordemservico.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ordemservico.model.Funcionario;
import br.com.ordemservico.model.DAO.FuncionarioDAO;
import br.com.ordemservico.model.DAO.UsuarioDAO;

public class DelFuncionario implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("id_funcionario") != null && request.getParameter("id_funcionario") != "") {	
			Integer idFuncionario = Integer.valueOf(request.getParameter("id_funcionario"));
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			Funcionario funcionario = funcionarioDAO.detalhar(idFuncionario);
			funcionarioDAO.deletar(idFuncionario);
			
			new UsuarioDAO().deletar(Integer.valueOf(Integer.valueOf(funcionario.getUsuario().getId().toString())));
		}
		
		return "PageFuncionarios";
	}

}
