<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/pages?acao=" var="linkPage" />

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=dice-width, initial-scale=1.0">
    <title>Ordem Serviço</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


    <link rel="stylesheet" href="static/css/style.css">
    <link rel="stylesheet" href="static/css/table.css">
    <link rel="stylesheet" href="static/css/modal.css">
</head>
<body>
    <c:import url="components/header.jsp" />
    <div class="container">
        <h2>Seus Serviços</h2>
        <div class="table-container scroll">

            <table class="table_padrao">
                <thead>
                    <tr>                                                                        
                        <th>Aparelho</th>
                        <th>Valor Total</th>
                        <th>Início do Atendimento</th>
                        <th>Finalizado</th>
                        <th class="t_acao">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${servicos}" var="servico">
                        <tr>
                            <td>${servico.equipamento().marca}/${servico.equipamento().modelo}</td>                            
                            <td>${servico.valor_servico()}</td>
                            <td>${servico.dt_inclusao()}</td>                                                        
                            <td>
                            	<c:choose>
								    <c:when test="${servico.cod_finaliza_servico()}">
								        <p style="color: #00e41b;">SIM - (${servico.dt_conclusao()})</p>
								    </c:when>
								    <c:otherwise>
								        <p style="color: #ff0000;">NÃO</p>
								    </c:otherwise>
								</c:choose>
                            </td>
                            <td class="t_acao">
                                <c:if test="${servico.cod_finaliza_servico()}">
                                    <a href="${linkPage}PageDetalharServico&idServico=${servico.id()}"><Button class="btn">Detalhar</Button> </a>                               
                                </c:if>
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