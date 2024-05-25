package br.com.ordemservico.model.DAO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ordemservico.model.Peca;

public class PecaDAO extends Conexao {
	public Peca inserir(Peca peca) {
		try {
			abrirBanco();
			
			String sql = " INSERT INTO peca VALUES (null, ?, ?, ?) ";
			
			pst = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, peca.getId_servico());
			pst.setString(2, peca.getNome());
			pst.setBigDecimal(3, peca.getValor());			
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			
			if (rs.next()) {		            
				peca.alterar(Long.valueOf(rs.getInt(1)), null, null, BigDecimal.ZERO);
			} else { 
				System.out.println("Não foi possível obter o ID gerado para a Peça inserido.");
			}
			
			fecharBanco();
			
		} catch(Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return peca;
	}
	
	 public List<Peca> listar(int id) {
	        List<Peca> pecas = new ArrayList<Peca>();
	       
	        try{
	        abrirBanco();  
	        
	        String sql = "SELECT * FROM peca where id_servico = ? ";
	       
	        pst = (PreparedStatement) con.prepareStatement(sql);
	        pst.setInt(1, id);
	        ResultSet tr = pst.executeQuery();
	        
	        Peca peca ;
	        while (tr.next()){               
	        	peca = new Peca();          
	        	peca.alterar(Long.valueOf(tr.getLong("id")), tr.getString("nome"), tr.getInt("id_servico"), tr.getBigDecimal("valor"));
	        	pecas.add(peca);
	        } 
	        
	        fecharBanco();
	        
	        }catch (Exception e){
	          System.out.println("Erro " + e.getMessage());
	        } 
	       return pecas;
	     }
	 
	 public Peca detalhar(int id) {
		 Peca peca = new Peca();
	        try {
	            abrirBanco();
	            
	            String sql = " SELECT * FROM peca where id = ? ";
	            
	            pst = (PreparedStatement) con.prepareStatement(sql);
	            
	            pst.setInt(1, id);
	            
	            ResultSet tr = pst.executeQuery();
	            
	            if (tr.next()) {
	                peca.alterar(Long.valueOf(tr.getLong("id")), tr.getString("nome"), tr.getInt("id_servico"), tr.getBigDecimal("valor"));
	            } else {
	                System.out.println("Nenhum resultado encontrado! ");     
	            }
	            
	            fecharBanco();   
	            
	        } catch (Exception e) {
	            System.out.println("Erro " + e.getMessage());
	        }
	        return peca;
	    }    
	  
	    public void atualizar(Peca peca){
	    	
	    	try {
	    		abrirBanco();
	 	       
		        String sql = " UPDATE peca SET id_servico = ?, nome = ?, valor = ? WHERE id = ? ";
		        
		        pst = (PreparedStatement) con.prepareStatement(sql);
		        pst.setInt(1, peca.getId_servico());
		        pst.setString(2, peca.getNome());		        
		        pst.setBigDecimal(4, peca.getValor());
		        pst.setLong(5, peca.getId());
		        
		        pst.executeUpdate();
		        
		        fecharBanco();
		        } catch (Exception e) {
		            System.out.println("Erro " + e.getMessage());		            
		        }	        
	    }
	    
	    
	  public void deletar(int id) {
	        try {
	        abrirBanco();  
	        
	        String sql = " DELETE FROM peca WHERE id = ? "; 
	        
	        pst=(PreparedStatement) con.prepareStatement(sql);
	        pst.setInt(1, id);   
	        
	        pst.execute();
	        
	        fecharBanco();
	        } catch (Exception e) {
	            System.out.println("Erro " + e.getMessage());
	        }
	    }
	 
	 

}
