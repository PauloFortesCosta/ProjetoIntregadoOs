package br.com.ordemservico.acao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.ordemservico.infra.Funcionalidades;
import br.com.ordemservico.model.Funcionario;
import br.com.ordemservico.model.DAO.FuncionarioDAO;


public class DetalharFuncionario implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		JsonObject jsonObject = Funcionalidades.retornaObjetoJson(reader);
	    
	    Integer idFuncionario =  Integer.valueOf(jsonObject.get("idFuncionario").getAsString());
	    
	    Funcionario funcionario = new FuncionarioDAO().detalhar(idFuncionario);
	    
	    String jsonFuncionario = new Gson().toJson(funcionario);
	    
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	
	    PrintWriter out = response.getWriter();
	    out.print(jsonFuncionario);
	    out.flush();
	    
		return null;
	}

}
