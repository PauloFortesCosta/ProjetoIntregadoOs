package br.com.ordemservico.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ordemservico.model.Equipamento;

public class EquipamentoDAO extends Conexao{
	public Equipamento inserir(Equipamento equipamento) {
		try {
			abrirBanco();
			
			String sql = " INSERT INTO equipamento VALUES (null, ?, ?, ?) ";
			
			pst = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, equipamento.getModelo());
			pst.setString(2, equipamento.getCor());
			pst.setString(3, equipamento.getMarca());			
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			
			if (rs.next()) {		            
				equipamento.alterar(Long.valueOf(rs.getInt(1)), null, null, null);
			} else { 
				System.out.println("Não foi possível obter o ID gerado para o equipamento inserido.");
			}
			
			fecharBanco();
			
		        			
		} catch(Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return equipamento;
	}
	
	 public List<Equipamento> listar() {
	        List<Equipamento> equipamentos = new ArrayList<Equipamento>();
	       
	        try{
	        abrirBanco();  
	        
	        String sql = "SELECT * FROM equipamento ";
	       
	        pst = (PreparedStatement) con.prepareStatement(sql);
	        ResultSet tr = pst.executeQuery();
	        Equipamento equipamento ;
	        while (tr.next()){               
	          equipamento = new Equipamento();          
	          equipamento.alterar(Long.valueOf(tr.getInt("id")), tr.getString("modelo"), tr.getString("cor"), tr.getString("marca"));
	          equipamentos.add(equipamento);
	        } 
	        
	        fecharBanco();
	        
	        }catch (Exception e){
	          System.out.println("Erro " + e.getMessage());
	        } 
	       return equipamentos;
	     }
	 
	 public Equipamento detalhar(int id) {
		 Equipamento equipamento = new Equipamento();
	        try {
	            abrirBanco();
	            
	            String sql = " SELECT * FROM equipamento where id = ? ";
	            
	            pst = (PreparedStatement) con.prepareStatement(sql);
	            
	            pst.setInt(1, id);
	            
	            ResultSet tr = pst.executeQuery();
	            
	            if (tr.next()) {
	                equipamento.alterar(Long.valueOf(tr.getInt("id")), tr.getString("modelo"), tr.getString("cor"), tr.getString("marca"));
	            } else {
	                System.out.println("Nenhum resultado encontrado! ");     
	            }
	            
	            fecharBanco();   
	            
	        } catch (Exception e) {
	            System.out.println("Erro " + e.getMessage());
	        }
	        return equipamento;
	    }    
	  
	    public void atualizar(Equipamento equipamento){
	    	
	    	try {
	    		abrirBanco();
	 	       
		        String sql = " UPDATE equipamento SET modelo = ?, cor = ?, marca = ? WHERE id = ? ";
		        
		        pst = (PreparedStatement) con.prepareStatement(sql);
		        pst.setString(1, equipamento.getModelo());
		        pst.setString(2, equipamento.getCor());
		        pst.setString(3, equipamento.getMarca());
		        pst.setLong(4, equipamento.getId());
		        
		        pst.executeUpdate();
		        
		        fecharBanco();
		        } catch (Exception e) {
		            System.out.println("Erro " + e.getMessage());		            
		        }	        
	    }
	    
	    
	  public void deletar(int id) {
	        try {
	        abrirBanco();  
	        
	        String sql = " DELETE FROM equipamento WHERE id = ? "; 
	        
	        pst=(PreparedStatement) con.prepareStatement(sql);
	        pst.setInt(1, id);   
	        
	        pst.execute();
	        
	        fecharBanco();
	        } catch (Exception e) {
	            System.out.println("Erro " + e.getMessage());
	        }
	    }
	 

}
