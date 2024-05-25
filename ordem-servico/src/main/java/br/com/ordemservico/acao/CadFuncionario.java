package br.com.ordemservico.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ordemservico.infra.Exception.AlertException;
import br.com.ordemservico.model.Funcionario;
import br.com.ordemservico.model.TipoUsuario;
import br.com.ordemservico.model.Usuario;
import br.com.ordemservico.model.DAO.FuncionarioDAO;
import br.com.ordemservico.model.DAO.UsuarioDAO;

public class CadFuncionario implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		validaCampos(request);
				
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		Integer idade = Integer.valueOf(request.getParameter("idade"));
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario(null, login, senha, TipoUsuario.ADMIN);
		usuario = new UsuarioDAO().inserir(usuario);
		
		Funcionario funcionario = new Funcionario(nome, email, idade, usuario);
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.inserir(funcionario);		
		
		return "Home";
	}
	
	private void validaCampos(HttpServletRequest request) {
		
		if(request.getParameter("nome") == null || request.getParameter("nome") == "") {
			throw new AlertException("Campo Nome não pode ser vazio");
		}	
		
		if(request.getParameter("email") == null || request.getParameter("email") == "") {
			throw new AlertException("Campo E-mail não pode ser vazio");
		}	
		
		if(request.getParameter("idade") == null || request.getParameter("idade") == "") {
			throw new AlertException("Campo Idade não pode ser vazio");
		}
		
		if(request.getParameter("login") == null || request.getParameter("login") == "") {
			throw new AlertException("Campo Login não pode ser vazio");
		}	
		
		if(request.getParameter("senha") == null || request.getParameter("senha") == "") {
			throw new AlertException("Campo Senha não pode ser vazio");
		}	
		
	}

}

