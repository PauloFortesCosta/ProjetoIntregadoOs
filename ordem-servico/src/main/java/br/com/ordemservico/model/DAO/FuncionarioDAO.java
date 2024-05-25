package br.com.ordemservico.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ordemservico.model.Funcionario;

public class FuncionarioDAO extends Conexao{
	public void inserir(Funcionario funcionario) {
		try {
			abrirBanco();
			
			String sql = " INSERT INTO funcionario VALUES (null, ?, ?, ?, ?) ";
			
			pst = (PreparedStatement) con.prepareStatement(sql);			
			pst.setString(1, funcionario.getNome());
			pst.setString(2, funcionario.getEmail());
			pst.setInt(3, funcionario.getIdade());
			pst.setLong(4, funcionario.getUsuario().getId());
			
			pst.execute();
			fecharBanco();
			
		} catch(Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
	}
	
	 public List<Funcionario> listar() {
	        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	       
	        try{
	        abrirBanco();  
	        
	        String sql = "SELECT * FROM funcionario ";
	       
	        pst = (PreparedStatement) con.prepareStatement(sql);
	        ResultSet tr = pst.executeQuery();
	        Funcionario funcionario ;
	        while (tr.next()){             
	        	var usuario = new UsuarioDAO().detalhar(tr.getInt("id_usuario"));
	        	
	        	
	          funcionario = new Funcionario(); 	          
	          funcionario.alterar(Long.valueOf(tr.getInt("id")), tr.getString("nome"), tr.getString("email"), Integer.valueOf(tr.getInt("idade")), usuario);
	          funcionarios.add(funcionario);
	        } 
	        
	        fecharBanco();
	        
	        }catch (Exception e){
	          System.out.println("Erro " + e.getMessage());
	        } 
	       return funcionarios;
	     }
	 
	 public List<Funcionario> pesquisar(String nome) {
	        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	       
	        try{
	        abrirBanco();  
	        
	        String sql = "SELECT * FROM funcionario WHERE nome LIKE ?";
	       
	        pst = (PreparedStatement) con.prepareStatement(sql);
	        pst.setString(1, "%" + nome + "%");
	        
	        ResultSet tr = pst.executeQuery();
	        Funcionario funcionario ;
	        while (tr.next()){   
	        	var usuario = new UsuarioDAO().detalhar(tr.getInt("id_usuario"));
	        	
	          funcionario = new Funcionario();          
	          funcionario.alterar(Long.valueOf(tr.getInt("id")), tr.getString("nome"), tr.getString("email"), Integer.valueOf(tr.getInt("idade")), usuario);
	          funcionarios.add(funcionario);
	        } 
	        
	        fecharBanco();
	        
	        }catch (Exception e){
	          System.out.println("Erro " + e.getMessage());
	        } 
	       return funcionarios;
	     }
	 
	 public Funcionario detalhar(int id) {
	        Funcionario funcionario = new Funcionario();
	        try {
	            abrirBanco();
	            
	            String sql = " SELECT * FROM funcionario where id = ? ";
	            
	            pst = (PreparedStatement) con.prepareStatement(sql);
	            
	            pst.setInt(1, id);
	            
	            ResultSet tr = pst.executeQuery();
	            
	            if (tr.next()) {
	            	var usuario = new UsuarioDAO().detalhar(tr.getInt("id_usuario"));
	                funcionario.alterar(Long.valueOf(tr.getInt("id")), tr.getString("nome"), tr.getString("email"), Integer.valueOf(tr.getInt("idade")), usuario);
	            } else {
	                System.out.println("Nenhum resultado encontrado! ");     
	            }
	            
	            fecharBanco();   
	            
	        } catch (Exception e) {
	            System.out.println("Erro " + e.getMessage());
	        }
	        return funcionario;
	    }    
	  
	    public void atualizar(Funcionario funcionario){
	    	
	    	try {
	    		abrirBanco();
	 	       
		        String sql = " UPDATE funcionario SET nome = ?, email = ?, idade = ? WHERE id = ? ";
		        
		        pst = (PreparedStatement) con.prepareStatement(sql);
		        pst.setString(1, funcionario.getNome());
		        pst.setString(2, funcionario.getEmail());
		        pst.setInt(3, funcionario.getIdade());
		        pst.setLong(4, funcionario.getId());
		        
		        pst.executeUpdate();
		        
		        fecharBanco();
		        } catch (Exception e) {
		            System.out.println("Erro " + e.getMessage());		            
		        }	        
	    }
	    
	    
	  public void deletar(int id) {
	        try {
	        abrirBanco();  
	        
	        String sql = " DELETE FROM funcionario WHERE id = ? "; 
	        
	        pst=(PreparedStatement) con.prepareStatement(sql);
	        pst.setInt(1, id);   
	        
	        pst.execute();
	        
	        fecharBanco();
	        } catch (Exception e) {
	            System.out.println("Erro " + e.getMessage());
	        }
	    }
	 
	 
}
