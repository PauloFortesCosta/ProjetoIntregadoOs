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

        <div class="modal_fundo modal_editar">
            <div class="modal_container">
                <div class="modal_conteudo">                    
                    <h2>Editar <span>Funcionário</span></h2>                       
                    <form action="${ linkPage }EditFuncionario" method="post">
                        <input type="hidden" name="id">
                        <div class="linha-input">
                            <label for="nome">Nome: </label>
                            <input type="text" name="nome">
                        </div>
                        <div class="linha-input">
                            <label for="email">E-mail: </label>
                            <input type="text" name="email">
                        </div>
                        <div class="linha-input">
                            <label for="idade">Idade: </label>
                            <input type="number" name="idade">
                        </div>
                        <input type="submit" value="Enviar" class="btn">
                    </form>                    
                </div>
                <div class="btn_container">
                    <button class="btn" onclick="fecharModal('editar')">Fechar</button>
                </div>
            </div>
        </div>

        <div class="modal_fundo modal_excluir">
            <div class="modal_container">
                <div class="modal_conteudo">                    
                    <p>Tem certeza que desejar excluir este funcionário?</p>   
                    <p id="id_funcionario_excluir" style="display: none;"></p>           
                </div>
                <div class="btn_container">
                    <button class="btn" onclick="fecharModal('excluir')">Cancelar</button>
                    <button class="btn" onclick="excluirFuncionario()">Confirmar</button>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="pesquisar_container">
                <form action="${ linkPage }PageFuncionarios" method="post">
                    <input type="text" name="search">
                    <button class="btn">Pesquisar</button>
                </form>
            </div>
            <div class="table-container scroll">

                <table class="table_padrao">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>E-mail</th>
                            <th>Idade</th>
                            <th colspan="2" class="t_acao">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${funcionarios}" var="funcionario">
                            <tr>
                                <td>${funcionario.nome}</td>
                                <td>${funcionario.email}</td>
                                <td>${funcionario.idade}</td>
                                <td class="t_acao">
                                    <Button class="btn"
                                        onclick="ativarFormEditarFuncionario(event, '${funcionario.id}')">Editar</Button>
                                    <button class="btn"
                                        onclick="ativarConfirmExcluirFuncionario(event, '${funcionario.id}')">Excluir</button>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
        <c:import url="components/footer.jsp" />
        <script src="static/js/modal.js"></script>
        <script>
            function ativarFormEditarFuncionario(e, idFuncionario) {
                e.preventDefault();
                
                var dados = {
                    idFuncionario: idFuncionario,
                };

                $.ajax({
                    url: '/ordem-servico/pages?acao=DetalharFuncionario',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(dados),
                    success: function (response) {                
                        document.querySelector('[name="id"]').value = response.id
                        document.querySelector('[name="nome"]').value = response.nome
                        document.querySelector('[name="email"]').value = response.email
                        document.querySelector('[name="idade"]').value = response.idade
                        abrirModal(e, 'editar');
                    },
                    error: function (xhr, status, error) {
                        // Lidar com erros
                        console.error(xhr.responseText);
                    }
                });
            }

            function ativarConfirmExcluirFuncionario(e, idFuncionario) {
                e.preventDefault();
                document.getElementById('id_funcionario_excluir').innerHTML = idFuncionario;
                abrirModal(e, 'excluir');
            }

            function excluirFuncionario() {
                const idFuncionario = document.getElementById('id_funcionario_excluir').textContent;
                window.location.href = "/ordem-servico/pages?acao=DelFuncionario&id_funcionario=" + idFuncionario;                
            }
        </script>
    </body>

    </html>