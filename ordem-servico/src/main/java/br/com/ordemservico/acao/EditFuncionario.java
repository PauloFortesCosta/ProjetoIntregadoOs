package br.com.ordemservico.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ordemservico.model.Funcionario;
import br.com.ordemservico.model.DAO.FuncionarioDAO;

public class EditFuncionario implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Long id = Long.valueOf(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		Integer idade = Integer.valueOf(request.getParameter("idade"));
		
		Funcionario funcionario = new Funcionario();
		funcionario.alterar(id, nome, email, idade, null);
		
		new FuncionarioDAO().atualizar(funcionario);
		
		return "PageFuncionarios";
	}
	
}
