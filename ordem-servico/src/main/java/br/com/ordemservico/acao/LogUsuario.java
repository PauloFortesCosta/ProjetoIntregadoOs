package br.com.ordemservico.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ordemservico.infra.Exception.AlertException;
import br.com.ordemservico.model.TipoUsuario;
import br.com.ordemservico.model.Usuario;
import br.com.ordemservico.model.service.UsuarioService;

public class LogUsuario implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		validaCampos(request);
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Usuario usuario = UsuarioService.retornaUsuario(login, senha);
		
		HttpSession sessao = request.getSession();
		sessao.setAttribute("usuarioLogado", usuario);
		
		if(usuario.getTipo_usuario() == TipoUsuario.ADMIN) {
			return "Home";
		} else {
			return "PageCliente";
		}
	}

	private void validaCampos(HttpServletRequest request) {	
		if(request.getParameter("login") == null || request.getParameter("login") == "") {
			throw new AlertException("Campo Login não pode ser vazio");
		}	
		
		if(request.getParameter("senha") == null || request.getParameter("senha") == "") {
			throw new AlertException("Campo Senha não pode ser vazio");
		}	
		
	}
}
