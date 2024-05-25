package br.com.ordemservico.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ordemservico.acao.Acao;
import br.com.ordemservico.infra.Exception.AlertException;

@WebServlet("/pages")
public class PagesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		String nomeDaClasse = "br.com.ordemservico.acao." + acao;		
		
		String nome;
		try {
			Class<?> classe = Class.forName(nomeDaClasse); // carrega a classe com o nome
			@SuppressWarnings("deprecation")
			Acao service = (Acao) classe.newInstance();
			nome = service.executa(request, response);
		} catch (AlertException ex) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();			
			String script = "<script>" +
							" alert('" + ex.getMessage() + "'); " +	
							" window.history.back(); " +																
							"</script>";	
			
			out.println(script);
			return;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ServletException
				| IOException e) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			String script = "<script>" +
					"alert('Erro ao carregar página. Redirecionando para a página home.');" +
					"window.location.href = '/ordem-servico/pages?acao=Home';" +
					"</script>";	
			
			out.println(script);			
//			throw new ServletException(e);	
			return;
		}
		
		if(nome == null) {
			return;
		}
		
		String[] tipoEndereco = nome.split(":");
		if(tipoEndereco[0].equals("forward")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEndereco[1]);
			rd.forward(request, response);
			
		} else {
			response.sendRedirect("pages?acao=" + tipoEndereco[0]);
		}				
	}
}
