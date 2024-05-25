package br.com.ordemservico.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ordemservico.model.Cliente;
import br.com.ordemservico.model.Servico;
import br.com.ordemservico.model.Usuario;
import br.com.ordemservico.model.DAO.ClienteDAO;
import br.com.ordemservico.model.DAO.ServicoDAO;
import br.com.ordemservico.model.DTO.DadosDetalhaServico;

public class PageCliente implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		Cliente cliente = new ClienteDAO().pesquisarPorIdUsuario(usuario.getId());
		Integer idCliente = Integer.valueOf(cliente.getId().toString());
	
		
		List<Servico> servicos = new ServicoDAO().pesquisarPorIdCliente(idCliente);
		List<DadosDetalhaServico> listServicos= servicos.stream().map(servico -> new DadosDetalhaServico(servico)).toList();
		request.setAttribute("servicos", listServicos);
		
		return "forward:pageCliente.jsp";
	}

}
