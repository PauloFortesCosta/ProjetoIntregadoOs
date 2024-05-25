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
    <link rel="stylesheet" href="static/css/style.css">
    <link rel="stylesheet" href="static/css/home.css">
    
    <style>
   	.btn {
		min-height: 50px;
	}
    </style>
</head>


<body>
    <c:import url="components/header.jsp" />
    <div class="container">
        <div class="card-branco">
            <h2>Ordem de <span>Serviço</span></h2>
            <div class="btn_container">
                <a href="${linkPage}FormFuncionario"><button class="btn">Cadastrar Funcionario</button></a>
                <a href="${linkPage}FormServico"><button class="btn">Cadastrar Serviço</button></a>
                <a href="${linkPage}PageServicos"><button class="btn">Serviços em Atendimento</button></a>
            </div>
        </div>
    </div>
    <c:import url="components/footer.jsp" />
</body>

</html>