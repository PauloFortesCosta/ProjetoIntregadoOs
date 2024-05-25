package br.com.ordemservico.acao;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ordemservico.infra.Exception.AlertException;
import br.com.ordemservico.model.Cliente;
import br.com.ordemservico.model.Equipamento;
import br.com.ordemservico.model.Funcionario;
import br.com.ordemservico.model.Servico;
import br.com.ordemservico.model.TipoUsuario;
import br.com.ordemservico.model.Usuario;
import br.com.ordemservico.model.DAO.ClienteDAO;
import br.com.ordemservico.model.DAO.EquipamentoDAO;
import br.com.ordemservico.model.DAO.FuncionarioDAO;
import br.com.ordemservico.model.DAO.ServicoDAO;
import br.com.ordemservico.model.DAO.UsuarioDAO;

public class CadServico implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		validaCampos(request);
		
		Servico servico = new Servico();
		
		String id_funcionario = request.getParameter("id_funcionario");		
		Funcionario funcionario = new FuncionarioDAO().detalhar(Integer.valueOf(id_funcionario));
		BigDecimal valor_servico = new BigDecimal(request.getParameter("valor"));
		String observacao = request.getParameter("observacao");			
		Equipamento equipamento = null;
		Cliente cliente = null;
		
		if(request.getParameter("id_equipamento") != null && request.getParameter("id_equipamento") != "") {
			String id_equipamento = request.getParameter("id_equipamento");
			equipamento = new EquipamentoDAO().detalhar(Integer.valueOf(id_equipamento));			
		} else {			
			String modelo = request.getParameter("modelo");
			String cor = request.getParameter("cor");
			String marca = request.getParameter("marca");
			
			equipamento = new Equipamento(modelo, cor, marca);			
			equipamento = new EquipamentoDAO().inserir(equipamento);
		}
				
		if(request.getParameter("id_cliente") != null && request.getParameter("id_cliente") != "") {
			String id_cliente = request.getParameter("id_cliente");
			cliente = new ClienteDAO().detalhar(Integer.valueOf(id_cliente));
		} else {
			String numero_cliente = request.getParameter("numero");
			String nome_cliente = request.getParameter("nome");
			String cpf_cliente = request.getParameter("cpf");
			String cep_cliente = request.getParameter("cep");
			String login_cliente = request.getParameter("login");
			String senha_cliente = request.getParameter("senha");
			
			Usuario usuario = new UsuarioDAO().inserir(new Usuario(null, login_cliente, senha_cliente, TipoUsuario.CLIENTE));			
			cliente = new ClienteDAO().inserir(new Cliente(nome_cliente, numero_cliente, cpf_cliente, cep_cliente, usuario));			
		}
		
		servico.alterar(null, equipamento, cliente, funcionario, null, null, valor_servico, observacao, null, false);
		
		new ServicoDAO().inserir(servico);
		
		return "Home";
	}
	
private void validaCampos(HttpServletRequest request) {
		
		if(request.getParameter("id_funcionario") == null || request.getParameter("id_funcionario") == "") {
			throw new AlertException("Campo Funcionário não pode ser vazio");
		}	
		
		if(request.getParameter("valor") == null || request.getParameter("valor") == "") {
			throw new AlertException("Campo Valor não pode ser vazio");
		}			
		
		if(request.getParameter("id_equipamento") == null || request.getParameter("id_equipamento") == "") {
			if(request.getParameter("modelo") == null || request.getParameter("modelo") == "") {
				throw new AlertException("Campo Modelo não pode ser vazio");
			}
			
			if(request.getParameter("marca") == null || request.getParameter("marca") == "") {
				throw new AlertException("Campo Marca não pode ser vazio");
			}
			
			if(request.getParameter("cor") == null || request.getParameter("cor") == "") {
				throw new AlertException("Campo Cor não pode ser vazio");
			}	
		}
		
		if(request.getParameter("id_cliente") == null || request.getParameter("id_cliente") == "") {
			if(request.getParameter("nome") == null || request.getParameter("nome") == "") {
				throw new AlertException("Campo Nome não pode ser vazio");
			}
			
			if(request.getParameter("numero") == null || request.getParameter("numero") == "") {
				throw new AlertException("Campo Número não pode ser vazio");
			}
			
			if(request.getParameter("cpf") == null || request.getParameter("cpf") == "") {
				throw new AlertException("Campo CPF não pode ser vazio");
			}
			
			if(request.getParameter("cep") == null || request.getParameter("cep") == "") {
				throw new AlertException("Campo CEP não pode ser vazio");
			}
			
			if(request.getParameter("login") == null || request.getParameter("login") == "") {
				throw new AlertException("Campo login não pode ser vazio");
			}
			
			if(request.getParameter("senha") == null || request.getParameter("senha") == "") {				
				throw new AlertException("Campo Senha não pode ser vazio");
			}
		}
	}

}
