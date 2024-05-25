package br.com.ordemservico.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ordemservico.infra.Exception.AlertException;

public class CadLogin implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("login") == null || request.getParameter("login") == "") {
			throw new AlertException("Campo Login não pode ser vazio");
		}	
		if(request.getParameter("senha") == null || request.getParameter("senha") == "") {
			throw new AlertException("Campo Senha não pode ser vazio");
		}	
		if(request.getParameter("tipo_usuario") == null || request.getParameter("tipo_usuario") == "") {
			throw new AlertException("Campo Tipo de Usuário não pode ser vazio");
		}	
		
		return "PageLogin";
	}

}
