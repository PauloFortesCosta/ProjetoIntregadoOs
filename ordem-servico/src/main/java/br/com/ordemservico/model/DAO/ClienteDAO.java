package br.com.ordemservico.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ordemservico.model.Cliente;

public class ClienteDAO extends Conexao{
	public Cliente inserir(Cliente cliente) {
		try {
			abrirBanco();
			
			String sql = " INSERT INTO cliente VALUES (null, ?, ?, ?, ?, ?) ";
			
			pst = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);			
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getNumero());
			pst.setString(3, cliente.getCpf());
			pst.setString(4, cliente.getCep());
			pst.setLong(5, cliente.getUsuario().getId());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			
			if (rs.next()) {		            
				cliente.alterar(Long.valueOf(rs.getInt(1)), null, null, null, null, null);
			} else { 
				System.out.println("Não foi possível obter o ID gerado para o cliente inserido.");
			}
			
			fecharBanco();
			
		} catch(Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return cliente;
	}
	
	 public List<Cliente> listar() {
	        List<Cliente> clientes = new ArrayList<Cliente>();
	       
	        try{
	        abrirBanco();  
	        
	        String sql = "SELECT * FROM cliente ";
	       
	        pst = (PreparedStatement) con.prepareStatement(sql);
	        ResultSet tr = pst.executeQuery();
	        Cliente cliente ;
	        while (tr.next()){           
	        	var usuario = new UsuarioDAO().detalhar(tr.getInt("id_usuario"));
	        	cliente = new Cliente();          
	        	cliente.alterar(Long.valueOf(tr.getInt("id")), tr.getString("nome"), tr.getString("numero"), tr.getString("cpf"), tr.getString("cep"), usuario);
	        	clientes.add(cliente);
	        } 
	        
	        fecharBanco();
	        
	        }catch (Exception e){
	          System.out.println("Erro " + e.getMessage());
	        } 
	       return clientes;
	     }
	 
	 public Cliente detalhar(int id) {
		 Cliente cliente = new Cliente();
	        try {
	            abrirBanco();
	            
	            String sql = " SELECT * FROM cliente where id = ? ";
	            
	            pst = (PreparedStatement) con.prepareStatement(sql);
	            
	            pst.setInt(1, id);
	            
	            ResultSet tr = pst.executeQuery();
	            
	            if (tr.next()) {
	            	var usuario = new UsuarioDAO().detalhar(tr.getInt("id_usuario"));
	                cliente.alterar(Long.valueOf(tr.getInt("id")), tr.getString("nome"), tr.getString("numero"), tr.getString("cpf"), tr.getString("cep"), usuario);
	            } else {
	                System.out.println("Nenhum resultado encontrado! ");     
	            }
	            
	            fecharBanco();   
	            
	        } catch (Exception e) {
	            System.out.println("Erro " + e.getMessage());
	        }
	        return cliente;
	    }    
	  
	    public void atualizar(Cliente cliente){
	    	
	    	try {
	    		abrirBanco();
	 	       
		        String sql = " UPDATE cliente SET nome = ?, numero = ?, cpf = ?, cep = ? WHERE id = ? ";
		        
		        pst = (PreparedStatement) con.prepareStatement(sql);
		        pst.setString(1, cliente.getNome());
		        pst.setString(2, cliente.getNumero());
		        pst.setString(3, cliente.getCpf());
		        pst.setString(4, cliente.getCep());
		        pst.setLong(5, cliente.getId());
		        
		        pst.executeUpdate();
		        
		        fecharBanco();
		        } catch (Exception e) {
		            System.out.println("Erro " + e.getMessage());		            
		        }	        
	    }
	    
	    
	  public void deletar(int id) {
	        try {
	        abrirBanco();  
	        
	        String sql = " DELETE FROM cliente WHERE id = ? "; 
	        
	        pst=(PreparedStatement) con.prepareStatement(sql);
	        pst.setInt(1, id);   
	        
	        pst.execute();
	        
	        fecharBanco();
	        } catch (Exception e) {
	            System.out.println("Erro " + e.getMessage());
	        }
	    }

	public Cliente pesquisarPorIdUsuario(Long id) {
		Cliente cliente = new Cliente();
        try {
            abrirBanco();
            
            String sql = " SELECT * FROM cliente where id_usuario = ? ";
            
            pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setLong(1, id);
            
            ResultSet tr = pst.executeQuery();
            
            if (tr.next()) {
            	var usuario = new UsuarioDAO().detalhar(tr.getInt("id_usuario"));
                cliente.alterar(Long.valueOf(tr.getInt("id")), tr.getString("nome"), tr.getString("numero"), tr.getString("cpf"), tr.getString("cep"), usuario);
                return cliente;
            } else {
                System.out.println("Nenhum resultado encontrado! ");     
            }
            
            fecharBanco();   
            
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
	}
	 
	 
}
