<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:url value="/pages?acao=CadFuncionario" var="linkPage" />


    <!DOCTYPE html>
    <html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Ordem Serviço</title>
        <link rel="stylesheet" href="static/css/style.css">
    </head>

    <body>
        <c:import url="components/header.jsp" />
        <div class="container">
            <div class="card-branco">
            	<h2>Cadastrar <span>Funcionário</span></h2>
                <form action="${ linkPage }" method="post">
                    <div class="linha-input">
                        <label for="nome">Nome: <span>*</span></label>
                        <input type="text" name="nome">
                    </div>
                  	<article>
                  		 <div class="linha-input">
	                        <label for="email">E-mail: <span>*</span></label>
	                        <input type="text" name="email">
	                    </div>
	                    <div class="linha-input">
	                        <label for="idade">Idade: <span>*</span></label>
	                        <input type="number" name="idade">
	                    </div>
                  	</article>
                 	<article>
                 		  <div class="linha-input">
	                        <label for="login">Login: <span>*</span></label>
	                        <input type="text" name="login">
                    	</div>
                    	<div class="linha-input">
	                        <label for="senha">Senha: <span>*</span></label>
	                        <input type="password" name="senha">
                    	</div>
                 	</article>
                    <input type="submit" value="Enviar" class="btn">
                </form>
            </div>
        </div>
        <c:import url="components/footer.jsp" />
    </body>

    </html>