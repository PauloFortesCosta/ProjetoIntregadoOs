package br.com.ordemservico.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ordemservico.model.TipoUsuario;
import br.com.ordemservico.model.Usuario;

@WebFilter("/pages")
public class AutorizacaoFilter extends HttpFilter implements Filter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String acao = request.getParameter("acao");
		
		HttpSession sessao = req.getSession();
		
		boolean usuarioNaoLogado = (sessao.getAttribute("usuarioLogado") == null);
		boolean pageProtegida = !(acao.equals("PageLogin") || acao.equals("LogUsuario")|| acao.equals("Logout"));		
		boolean pageAutorizadaCliente = (acao.equals("PageCliente") || acao.equals("PageDetalharServico"));
		
		if(pageProtegida && usuarioNaoLogado) {
			res.sendRedirect("pages?acao=PageLogin");
			return;
		}
		
		if(pageProtegida) {
			Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
			
			if(usuario.getTipo_usuario() == TipoUsuario.CLIENTE && !pageAutorizadaCliente) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();			
				String script = "<script>" +
								" alert('Essa página não é autorizada pelo Cliente'); " +	
								" window.history.back(); " +																
								"</script>";	
				
				out.println(script);
				return;		
			}
		}
		
		chain.doFilter(request, response);
	}
}
