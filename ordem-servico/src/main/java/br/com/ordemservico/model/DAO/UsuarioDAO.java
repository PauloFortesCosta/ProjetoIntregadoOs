package br.com.ordemservico.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import br.com.ordemservico.infra.Exception.AlertException;
import br.com.ordemservico.model.TipoUsuario;
import br.com.ordemservico.model.Usuario;

public class UsuarioDAO extends Conexao{
	public Usuario inserir(Usuario usuario) {
		try {
			abrirBanco();
			
			String sql = " INSERT INTO usuario VALUES (null, ?, ?, ?) ";
			pst = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, usuario.getLogin());
			pst.setString(2, usuario.getSenha());
			pst.setString(3, usuario.getTipo_usuario().name());
			
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			
			if (rs.next()) {			
				usuario.alterar(Long.valueOf(rs.getString(1)), null);
			} else { 
				System.out.println("Não foi possível obter o ID gerado para o Usuário inserido.");
			}
			
			fecharBanco();
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new AlertException("Login já está sendo usado");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			throw new AlertException("Erro ao inserir usuário");
		}
		return usuario;
	}
	
	 public Usuario buscarPorLogin(String login) {
	        Usuario usuario;
	        try {
	            abrirBanco();
	            
	            String sql = " SELECT * FROM usuario where login = ? ";
	            
	            pst = (PreparedStatement) con.prepareStatement(sql);
	            
	            pst.setString(1, login);
	            
	            ResultSet tr = pst.executeQuery();
	            
	            if (tr.next()) {	                
	                usuario = new Usuario(Long.valueOf(tr.getInt("id")), tr.getString("login"), tr.getString("senha"), TipoUsuario.valueOf(tr.getString("tipo_usuario")));	               	              	               
	                return usuario;
	            } else {
	                System.out.println("Nenhum resultado encontrado! ");     
	            }
	            
	            fecharBanco();   	            
	        } catch (Exception e) {
	            System.out.println("Erro " + e.getMessage());
	        }
	        return null;
	    }    
	  
	    public void atualizarSenha(Usuario usuario){
	    	
	    	try {
	    		abrirBanco();
	 	       
		        String sql = " UPDATE usuario SET senha = ? WHERE id = ? ";
		        
		        pst = (PreparedStatement) con.prepareStatement(sql);
		        pst.setString(1, usuario.getLogin());		        		       
		        pst.setLong(2, usuario.getId());
		        
		        pst.executeUpdate();
		        
		        fecharBanco();
		        } catch (Exception e) {
		            System.out.println("Erro " + e.getMessage());		            
		        }	        
	    }
	    
	    
	  public void deletar(int id) {
	        try {
	        abrirBanco();  
	        
	        String sql = " DELETE FROM usuario WHERE id = ? "; 
	        
	        pst=(PreparedStatement) con.prepareStatement(sql);
	        pst.setInt(1, id);   
	        
	        pst.execute();
	        
	        fecharBanco();
	        } catch (Exception e) {
	            System.out.println("Erro " + e.getMessage());
	        }
	    }
	  
	  public Usuario detalhar(int id) {
	        Usuario usuario = new Usuario();
	        try {
	            abrirBanco();
	            
	            String sql = " SELECT * FROM usuario where id = ? ";
	            
	            pst = (PreparedStatement) con.prepareStatement(sql);
	            
	            pst.setInt(1, id);
	            	          	           
            	ResultSet tr = pst.executeQuery();
	            
	            if (tr.next()) {	                
	                usuario = new Usuario(Long.valueOf(tr.getInt("id")), tr.getString("login"), tr.getString("senha"), TipoUsuario.valueOf(tr.getString("tipo_usuario")));	               	              	               
	                return usuario;
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
