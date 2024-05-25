<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/pages?acao=" var="linkPage" />

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ordem Serviço</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


    <link rel="stylesheet" href="static/css/style.css">
    <link rel="stylesheet" href="static/css/home.css">
    <link rel="stylesheet" href="static/css/detalharServico.css">
    <link rel="stylesheet" href="static/css/modal.css">
</head>
<body>
    <c:import url="components/header.jsp" />

    <div class="modal_fundo modal_inserir_peca">
        <div class="modal_container">
        <button class="btn_fechar" onclick="fecharModal('inserir_peca')">X</button>
            <div class="modal_conteudo">                    
                <h2>Adicionar <span>Peça</span></h2>                       
                <form action="${ linkPage }CadPeca" method="post">                                                         
                    <input type="hidden" name="id_servico">                    
                    <div class="linha-input">
                        <label for="nome_peca">Nome: </label>
                        <input type="text" name="nome_peca">
                    </div>
                    <div class="linha-input">
                        <label for="valor_peca">Valor: </label>
                        <input type="number" name="valor_peca" step="0.01" min="0">
                    </div>
                    <input type="submit" value="Enviar" class="btn">
                </form>                    
            </div>
            <div class="btn_container">                
            </div>
        </div>
    </div>

    <div class="modal_fundo modal_finalizar_servico">
        <div class="modal_container">
            <button class="btn_fechar" onclick="fecharModal('finalizar_servico')">X</button>
            <div class="modal_conteudo">                    
               <p>A soma dos materiais usados foi de <span id="soma_valor_peca">${valorTotalPecas}</span>, com a diferença no orçamento de <span id="valor_diferenca"></span> <span id="status_valor_servico"></span>. Deseja atualizar valor total do serviço? O valor total do serviço vai ficar <span id="valor_total_servico"></span>.</p>     

               <form action="${ linkPage }FinalizaServico" method="post">
                   <input type="hidden" name="id_servico">
                   <input type="hidden" name="soma_valor_peca">
                   <input type="hidden" name="valor_diferenca">
                   <input type="hidden" name="valor_total_servico">
                   <input type="hidden" name="valor_servico">
    
                   <div class="linha-input radio">
                      	<div>  
	                      	<label for="sim">Sim:</label>
	                       	<input type="radio" name="atualizar_servico" value="1" checked>
						</div>
                       <div>
                       		<label for="nao">Não</label>
                      		<input type="radio" name="atualizar_servico" value="0">
                       </div>
                  </div>
                   <div class="btn_container">        
                       <button class="btn">Finalizar</button>                        
                   </div>
               </form>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="card-branco">
            <article>
             <c:if test="${not servico.cod_finaliza_servico}">
				<button class="btn_adicionar" onclick="abrirModal(event, 'inserir_peca');"><span>+</span> Adicionar</button>							    
			 </c:if>             
             <div class="peca_container scroll">
                 
				<c:forEach items="${pecas}" var="peca">
					<div class="card_peca">
					    <p>${peca.nome}</p>
					    <p class="valor_peca">${peca.valor}</p>
					    <c:if test="${not servico.cod_finaliza_servico}">
					     	<a href="${linkPage}DelPeca&idPeca=${peca.id}&idServico=${peca.id_servico}"><button><span>-</span></button></a>								    
					    </c:if>
					   
					</div>
				</c:forEach>
				                
             </div>
            </article>
             <section>
                 <h3>Cliente</h3>
                 <div class="card_detalhamento">
                     <p>${servico.cliente.nome}</p>
                     <p style="text-align: center;">-</p>
                     <p style="text-align: right;">${servico.cliente.numero}</p>                     
                 </div>
                 <h3>Funcionário</h3>
                 <div class="card_detalhamento">
                     <p>${servico.funcionario.nome}</p>
                     <p style="text-align: center;">-</p>
                     <p style="text-align: right;">${servico.funcionario.email}</p>                    
                 </div>
                 <h3>Aparelho</h3>
                 <div class="card_detalhamento aparelho">
                     <p>Modelo - <span>${servico.equipamento.modelo}</span></p>
                     <p>Cor - <span>${servico.equipamento.cor}</span></p>
                     <p>Marca - <span>${servico.equipamento.marca}</span></p>
                     <p>
                        Orçamento - <span id="valor_servico">${servico.valor_servico}</span>
                    </p>
                </div>
            </section>
            <div class="btn_container">
             	<c:if test="${not servico.cod_finaliza_servico}">
				 	<button class="btn" onclick="abrirModal(event, 'finalizar_servico');">Finalizar Serviço</button>								    
		    	</c:if>                
            </div>
        </div>
    </div>
    <c:import url="components/footer.jsp" />
    <script src="static/js/modal.js"></script>
    <script src="static/js/funcionalidades.js"></script>
    <script>    	
    
    var cardPeca = document.querySelectorAll('.card_peca')
    
    if(cardPeca.length > 0) {
        cardPeca.forEach((card) => {
            const p_valor = card.querySelector('.valor_peca');
            
            if(p_valor) {
                const valor = formataMoedaReal(p_valor.textContent);
                p_valor.innerHTML = valor;
            }
        })
    }
    
    document.querySelectorAll('[name="id_servico"]').forEach((input) => {
        input.value = new URL(window.location.href).searchParams.get('idServico');
    });

    var soma_valor_peca = document.getElementById('soma_valor_peca');
    var valor_diferenca = document.getElementById('valor_diferenca');
    var valor_total_servico = document.getElementById('valor_total_servico');
    var valor_servico = document.getElementById('valor_servico');
        var status_valor_servico = document.getElementById('status_valor_servico');
        
        var input_soma_valor_peca = document.querySelector('[name="soma_valor_peca"]');
        var input_valor_diferenca = document.querySelector('[name="valor_diferenca"]');
        var input_valor_total_servico = document.querySelector('[name="valor_total_servico"]');
        var input_valor_servico = document.querySelector('[name="valor_servico"]');

        

        // Atribuindo valores aos inputs
        input_valor_servico.value = valor_servico.textContent;
        input_soma_valor_peca.value = soma_valor_peca.textContent;                
        valor_diferenca.innerHTML = Number(input_valor_servico.value) - Number(input_soma_valor_peca.value);
        input_valor_diferenca.value = valor_diferenca.textContent;
        
        if(Number(input_valor_diferenca.value) >= 0) {
            status_valor_servico.innerHTML = "positivo(s)"
            valor_total_servico.innerHTML = Number(input_valor_servico.value) - Number(input_valor_diferenca.value); 
        } else {
            status_valor_servico.innerHTML = "negativos(s)"
            valor_total_servico.innerHTML = Number(input_valor_servico.value) + Math.abs(Number(input_valor_diferenca.value)); 
        }

        input_valor_total_servico.value = valor_total_servico.textContent;  
        
        
        //formatantdo números
        valor_servico.innerHTML = formataMoedaReal(Number(input_valor_servico.value));
        soma_valor_peca.innerHTML = formataMoedaReal(Number(input_soma_valor_peca.value));
        valor_diferenca.innerHTML = formataMoedaReal(Math.abs(Number(input_valor_diferenca.value)));       
        valor_total_servico.innerHTML = formataMoedaReal(Number(input_valor_total_servico.value));

    </script>
</body>
</html>