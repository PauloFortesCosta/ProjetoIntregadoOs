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
    <link rel="stylesheet" href="static/css/table.css">
    <link rel="stylesheet" href="static/css/modal.css">
</head>
<body>
    <c:import url="components/header.jsp" />
    <div class="container">
        <div class="pesquisar_container">
            <form id="statusForm" action="${linkPage}PageServicos" method="post">            
                <input type="text" name="search">
                <button class="btn">Pesquisar</button>            
                <select name="status_servico" onchange="submitForm()">
                    <option value="" selected disabled>Selecione</option>
                    <option value="">Todos</option>
                    <option value="0">Em Andamento</option>
                    <option value="1">Finalizado</option>
                </select>
            </form>
        </div>
        <div class="table-container scroll">

            <table class="table_padrao">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Cliente</th>
                        <th>Funcionário</th>
                        <th>Aparelho</th>
                        <th>Valor</th>
                        <th>Status</th>
                        <th class="t_acao">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${servicos}" var="servico">
                        <tr>
                            <td>${servico.id}</td>
                            <td>${servico.cliente.nome}</td>
                            <td>${servico.funcionario.nome}</td>
                            <td>${servico.equipamento.marca}/${servico.equipamento.modelo}</td>
                            <td>${servico.valor_servico}</td>
                            
                            <td>
                            	<c:choose>
								    <c:when test="${servico.cod_finaliza_servico}">
								        Finalizado
								    </c:when>
								    <c:otherwise>
								        Em Andamento
								    </c:otherwise>
								</c:choose>
                            </td>
                            <td class="t_acao">
                                <a href="${linkPage}PageDetalharServico&idServico=${servico.id}"><Button class="btn">Detalhar</Button> </a>                               
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
    <c:import url="components/footer.jsp" />
    <script>
        function submitForm() {
            document.getElementById("statusForm").submit();
        }
    </script>
</body>
</html>