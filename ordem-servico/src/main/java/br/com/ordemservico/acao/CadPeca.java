package br.com.ordemservico.acao;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ordemservico.model.Peca;
import br.com.ordemservico.model.DAO.PecaDAO;

public class CadPeca implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idServico = null;
        String nome = null;
        BigDecimal valor = null;

        String idServicoStr = request.getParameter("id_servico");
        String nomePeca = request.getParameter("nome_peca");
        String valorPeca = request.getParameter("valor_peca");
       
        if (idServicoStr != null && nomePeca != null && valorPeca != null) {
            try {
                idServico = Integer.valueOf(idServicoStr);
                nome = nomePeca;
                valor = new BigDecimal(valorPeca);
                
                Peca peca = new Peca(nome, idServico, valor);
                new PecaDAO().inserir(peca);
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter parâmetros: " + e.getMessage());             
            }
        } else { 
            System.err.println("Um ou mais parâmetros da solicitação são nulos.");            
        }
    
		
		return "PageDetalharServico&idServico=" + idServico;
	}

}
