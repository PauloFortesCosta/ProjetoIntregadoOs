<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/pages?acao=" var="linkPage" />
    
      
<!DOCTYPE html>
    <html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Ordem Servi�o</title>
        <link rel="stylesheet" href="static/css/style.css">
    </head>

    <body>
        <c:import url="components/header.jsp" />
        <div class="container">
            <div class="card-branco">
            	<h2>Cadastrar novo <span>usuário</span></h2>
                <form action="${ linkPage }CadLogin" method="post">
                    <div class="linha-input">
                        <label for="login">Login: <span>*</span></label>
                        <input type="text" name="login">
                    </div>
                    <div class="linha-input">
                        <label for="senha">Senha: <span>*</span></label>
                        <input type="password" name="senha">
                    </div> 
                    <div class="linha-input">
                        <label for="senha">Tipo de usuário: <span>*</span></label>
                        <select name="tipo_usuario">
                            <option value="" disabled selected>Selecione</option>
                            <option value="1">Funcionário</option>
                            <option value="2">Cliente</option>
                        </select>
                    </div>                                                         
                    <input type="submit" value="Enviar" class="btn">
                </form>
            </div>
        </div>
        <c:import url="components/footer.jsp" />
    </body>

    </html>