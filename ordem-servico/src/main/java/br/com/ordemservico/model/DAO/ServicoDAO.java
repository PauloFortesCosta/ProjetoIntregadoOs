package br.com.ordemservico.model.DAO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ordemservico.model.Cliente;
import br.com.ordemservico.model.Equipamento;
import br.com.ordemservico.model.Funcionario;
import br.com.ordemservico.model.Servico;

public class ServicoDAO extends Conexao{
	public void inserir(Servico servico) {
		try {
			abrirBanco();
			
			String sql = " INSERT INTO servico VALUES (null, ?, ?, ?, NOW(), null, ?, ?, null, ?) ";
			
			pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setLong(1, servico.getEquipamento().getId());
			pst.setLong(2, servico.getCliente().getId());
			pst.setLong(3, servico.getFuncionario().getId());
			pst.setBigDecimal(4, servico.getValor_servico());
			pst.setString(5, servico.getObservacao());	
			pst.setBoolean(6, servico.getCod_finaliza_servico());	
			
			pst.execute();
			fecharBanco();
			
		} catch(Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
	}
	
	 public List<Servico> listar() {
	        List<Servico> servicos = new ArrayList<Servico>();
	       
	        try{
	        abrirBanco();  
	        
	        String sql = "SELECT * FROM servico ORDER BY cod_finaliza_servico ASC";
	       
	        pst = (PreparedStatement) con.prepareStatement(sql);
	        ResultSet tr = pst.executeQuery();
	        
	        Servico servico ;
	        while (tr.next()){               
	          servico = new Servico();   
	          
	          Cliente cliente = new ClienteDAO().detalhar(Integer.valueOf(tr.getString("id_cliente")));
	          Funcionario funcionario = new FuncionarioDAO().detalhar(Integer.valueOf(tr.getString("id_funcionario")));
	          Equipamento equipamento = new EquipamentoDAO().detalhar(Integer.valueOf(tr.getString("id_equipamento")));
	          
	          servico.alterar(Long.valueOf(tr.getInt("id")), equipamento, cliente, funcionario, tr.getDate("dt_inclusao"), tr.getDate("dt_conclusao"), tr.getBigDecimal("valor_servico"), tr.getString("observacao"), tr.getString("avaliacao"), tr.getBoolean("cod_finaliza_servico"));
	          servicos.add(servico);
	        } 
	        
	        fecharBanco();
	        
	        }catch (Exception e){
	          System.out.println("Erro " + e.getMessage());
	        } 
	       return servicos;
	     }
	 
	 public List<Servico> pesquisar(String nome) {
	        List<Servico> servicos = new ArrayList<Servico>();
	       
	        try{
	        abrirBanco();  
	        
	        String sql = " SELECT servico.* FROM servico INNER JOIN cliente ON cliente.id = servico.id_cliente WHERE cliente.nome LIKE ? ORDER BY cod_finaliza_servico ASC ";
	       
	        pst = (PreparedStatement) con.prepareStatement(sql);
	        pst.setString(1, "%" + nome + "%");
	        
	        ResultSet tr = pst.executeQuery();
	        Servico servico ;
	        while (tr.next()){               
	        	 servico = new Servico();   
		          
		          Cliente cliente = new ClienteDAO().detalhar(Integer.valueOf(tr.getString("id_cliente")));
		          Funcionario funcionario = new FuncionarioDAO().detalhar(Integer.valueOf(tr.getString("id_funcionario")));
		          Equipamento equipamento = new EquipamentoDAO().detalhar(Integer.valueOf(tr.getString("id_equipamento")));
		          
		          servico.alterar(Long.valueOf(tr.getInt("id")), equipamento, cliente, funcionario, tr.getDate("dt_inclusao"), tr.getDate("dt_conclusao"), tr.getBigDecimal("valor_servico"), tr.getString("observacao"), tr.getString("avaliacao"), tr.getBoolean("cod_finaliza_servico"));
		          servicos.add(servico);
	        } 
	        
	        fecharBanco();
	        
	        }catch (Exception e){
	          System.out.println("Erro " + e.getMessage());
	        } 
	       return servicos;
	     }
	 
	 public List<Servico> pesquisarPorStatus(Boolean codFinalizaServico) {
	        List<Servico> servicos = new ArrayList<Servico>();
	       
	        try{
	        abrirBanco();  
	        
	        String sql = " SELECT * FROM servico WHERE cod_finaliza_servico = ? ";
	       
	        pst = (PreparedStatement) con.prepareStatement(sql);
	        pst.setBoolean(1, codFinalizaServico);
	        
	        ResultSet tr = pst.executeQuery();
	        Servico servico ;
	        while (tr.next()){               
	        	 servico = new Servico();   
		          
		          Cliente cliente = new ClienteDAO().detalhar(Integer.valueOf(tr.getString("id_cliente")));
		          Funcionario funcionario = new FuncionarioDAO().detalhar(Integer.valueOf(tr.getString("id_funcionario")));
		          Equipamento equipamento = new EquipamentoDAO().detalhar(Integer.valueOf(tr.getString("id_equipamento")));
		          
		          servico.alterar(Long.valueOf(tr.getInt("id")), equipamento, cliente, funcionario, tr.getDate("dt_inclusao"), tr.getDate("dt_conclusao"), tr.getBigDecimal("valor_servico"), tr.getString("observacao"), tr.getString("avaliacao"), tr.getBoolean("cod_finaliza_servico"));
		          servicos.add(servico);
	        } 
	        
	        fecharBanco();
	        
	        }catch (Exception e){
	          System.out.println("Erro " + e.getMessage());
	        } 
	       return servicos;
	     }
	 
	 public Servico detalhar(int id) {
		 Servico servico = new Servico();
	        try {
	            abrirBanco();
	            
	            String sql = " SELECT * FROM servico where id = ? ";
	            
	            pst = (PreparedStatement) con.prepareStatement(sql);
	            
	            pst.setInt(1, id);
	            
	            ResultSet tr = pst.executeQuery();
	            
	            if (tr.next()) {
	            	Cliente cliente = new ClienteDAO().detalhar(Integer.valueOf(tr.getString("id_cliente")));
					Funcionario funcionario = new FuncionarioDAO().detalhar(Integer.valueOf(tr.getString("id_funcionario")));
					Equipamento equipamento = new EquipamentoDAO().detalhar(Integer.valueOf(tr.getString("id_equipamento")));
					servico.alterar(Long.valueOf(tr.getInt("id")), equipamento, cliente, funcionario, tr.getDate("dt_inclusao"), tr.getDate("dt_conclusao"), tr.getBigDecimal("valor_servico"), tr.getString("observacao"), tr.getString("avaliacao"), tr.getBoolean("cod_finaliza_servico"));
	            } else {
	                System.out.println("Nenhum resultado encontrado! ");     
	            }
	            
	            fecharBanco();   
	            
	        } catch (Exception e) {
	            System.out.println("Erro " + e.getMessage());
	        }
	        return servico;
	    }    
	  
	    public void atualizar(Servico servico){
	    	
	    	try {
	    		abrirBanco();
	 	       
		        String sql = " UPDATE servico SET id_equipamento = ?, id_cliente = ?, id_funcionario = ?, dt_inclusao = ?, dt_conclusao = ?, valor_servico = ?, observacao = ?, avaliacao = ?, cod_finaliza_servico = ? WHERE id = ? ";
		        
		        pst = (PreparedStatement) con.prepareStatement(sql);		      
		        pst.setLong(1, servico.getEquipamento().getId());
		        pst.setLong(2, servico.getCliente().getId());
		        pst.setLong(3, servico.getFuncionario().getId());
		        pst.setDate(4, servico.getDt_inclusao());
		        pst.setDate(5, servico.getDt_conclusao());
		        pst.setBigDecimal(6, servico.getValor_servico());
		        pst.setString(7, servico.getObservacao());
		        pst.setString(8, servico.getAvaliacao());
		        pst.setBoolean(9, servico.getCod_finaliza_servico());
		        pst.setLong(10, servico.getId());
		        
		        pst.executeUpdate();
		        
		        fecharBanco();
		        } catch (Exception e) {
		            System.out.println("Erro " + e.getMessage());		            
		        }	        
	    }
	    
	    
	  public void deletar(int id) {
	        try {
	        abrirBanco();  
	        
	        String sql = " DELETE FROM servico WHERE id = ? "; 
	        
	        pst=(PreparedStatement) con.prepareStatement(sql);
	        pst.setInt(1, id);   
	        
	        pst.execute();
	        
	        fecharBanco();
	        } catch (Exception e) {
	            System.out.println("Erro " + e.getMessage());
	        }
	    }
	  
	  public void finalizarServico(int id, BigDecimal valor) {			 
		        try {
		            abrirBanco();
		            
		            String sql = " UPDATE servico SET valor_servico = ?, cod_finaliza_servico = ?, dt_conclusao = NOW() WHERE id = ? ";
		            
		            pst = (PreparedStatement) con.prepareStatement(sql);
		            pst.setBigDecimal(1, valor);
		            pst.setBoolean(2, true);
		            pst.setInt(3, id);
		            
		            pst.executeUpdate();		           		 
		            fecharBanco();   
		            
		        } catch (Exception e) {
		            System.out.println("Erro " + e.getMessage());
		        }		  
		    }

	public List<Servico> pesquisarPorIdCliente(Integer idCliente) {
		List<Servico> servicos = new ArrayList<Servico>();
		try {
			abrirBanco();
			
			String sql = "SELECT * FROM servico WHERE id_cliente = ? ";
			
			pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setInt(1, idCliente);
			
			 ResultSet tr = pst.executeQuery();
	            
			 Servico servico = new Servico();
			 while (tr.next()){               
	        	 servico = new Servico();   
		          
		          Cliente cliente = new ClienteDAO().detalhar(Integer.valueOf(tr.getString("id_cliente")));
		          Funcionario funcionario = new FuncionarioDAO().detalhar(Integer.valueOf(tr.getString("id_funcionario")));
		          Equipamento equipamento = new EquipamentoDAO().detalhar(Integer.valueOf(tr.getString("id_equipamento")));
		          
		          servico.alterar(Long.valueOf(tr.getInt("id")), equipamento, cliente, funcionario, tr.getDate("dt_inclusao"), tr.getDate("dt_conclusao"), tr.getBigDecimal("valor_servico"), tr.getString("observacao"), tr.getString("avaliacao"), tr.getBoolean("cod_finaliza_servico"));
		          servicos.add(servico);
	        } 
	            
	            fecharBanco();   				
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return servicos;
	}    		
}
