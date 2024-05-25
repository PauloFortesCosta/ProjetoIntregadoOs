package br.com.ordemservico.infra;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class Funcionalidades {

	public static JsonObject retornaObjetoJson(BufferedReader reader) {	
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        
        try {
			while ((line = reader.readLine()) != null) {
			    stringBuilder.append(line);
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
        
        String dadosJson = stringBuilder.toString();
        
       
        return JsonParser.parseString(dadosJson).getAsJsonObject();
	}
	
	 public static String formatarNumeroTelefone(String numero) {	      
        return String.format("(%s) %s %s-%s", numero.substring(0, 2), numero.substring(2, 3), numero.substring(3, 7), numero.substring(7));
    }
	 
	 public static String retornaDataFormatada(Date data) {
		 if(data != null) {			 
			 String dataString = data.toString();
			 return String.format("%s/%s/%s", dataString.substring(8, 10), dataString.substring(5, 7), dataString.substring(0, 4));
		 }
		 return null;
	 }
	 
	 public static String retornaValorFormatado(String valor) {
		    if (valor != null) {
		        String[] partes = valor.split("\\.");
		        if (partes.length == 2) {
		            return String.format("R$%s,%s", partes[0], partes[1].length() >= 2 ? partes[1].substring(0, 2) : partes[1] + "0");		           
		        }
		    }
		    return null;
		}

}
