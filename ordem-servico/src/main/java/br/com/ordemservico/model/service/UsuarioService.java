package br.com.ordemservico.model.service;

import br.com.ordemservico.infra.Exception.AlertException;
import br.com.ordemservico.model.Usuario;
import br.com.ordemservico.model.DAO.UsuarioDAO;

public abstract class UsuarioService {
	public static Usuario retornaUsuario(String login, String senha) {			
		Usuario usuario = new UsuarioDAO().buscarPorLogin(login);
		if(usuario != null) {
			boolean senhaValida = usuario.validaSenha(login, senha);
			
			if(senhaValida) return usuario;
			else throw new AlertException("Senha inválida. Por favor tente novamente");					
		} else {
			throw new AlertException("Usuário não existe!");
		}
	}
}
