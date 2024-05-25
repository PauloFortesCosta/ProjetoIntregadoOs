package br.com.ordemservico.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ordemservico.model.Cliente;
import br.com.ordemservico.model.Equipamento;
import br.com.ordemservico.model.Funcionario;
import br.com.ordemservico.model.DAO.ClienteDAO;
import br.com.ordemservico.model.DAO.EquipamentoDAO;
import br.com.ordemservico.model.DAO.FuncionarioDAO;

public class FormServico implements Acao{	
	
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Funcionario> funcionarios = new FuncionarioDAO().listar();
		request.setAttribute("funcionarios", funcionarios);
		
		List<Cliente> clientes = new ClienteDAO().listar();
		request.setAttribute("clientes", clientes);
		
		List<Equipamento> equipamentos = new EquipamentoDAO().listar();
		request.setAttribute("equipamentos", equipamentos);
			
		return "forward:formServico.jsp";
	}

}
