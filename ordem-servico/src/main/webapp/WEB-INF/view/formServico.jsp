<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:url value="/pages?acao=CadServico" var="linkPage" />


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
                <h2>Cadastro de Ordem de <span>Serviço</span></h2>
                <form action="${ linkPage }" method="post">
                    <div class="linha-input">
                        <label>Equipamento: </label>
                        <div class="equipamento-cadastro-container">
                            <div class="linha-input">
                                <label for="modelo">Modelo: </label>
                                <input type="text" name="modelo" id="modelo">
                            </div>
                            <article>
                                <div class="linha-input">
                                    <label for="cor">Cor: </label>
                                    <input type="text" name="cor" id="cor">
                                </div>
                                <div class="linha-input">
                                    <label for="marca">Marca: </label>
                                    <input type="text" name="marca" id="marca">
                                </div>
                            </article>
                        </div>
                        <div class="equipamento-selecionar-container">
                            <select name="id_equipamento" id="id_equipamento">
                                <option value="" selected disabled>Selecione</option>
                                <c:forEach items="${equipamentos}" var="equipamento">
                                    <option value="${equipamento.id}">${equipamento.modelo}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="botoes-container">
                            <button class="btn" onclick="btnCadastrarEquipamento(event)"
                                id="btn_cadastro_equipamento">Cadastrar</button>
                            <button class="btn" onclick="btnSelecionarEquipamento(event)"
                                id="btn_selecionar_equipamento">Selecionar</button>
                        </div>
                    </div>
                    <div class="linha-input">
                        <label>Cliente: </label>
                        <div class="cliente-cadastro-container">
                            <div class="linha-input">
                                <label for="nome">Nome: </label>
                                <input type="text" name="nome" id="nome">
                            </div>
                            <div class="linha-input">
                                <label for="numero">Número: </label>
                                <input type="text" name="numero" id="numero">
                            </div>
                          	<article>
                          		 <div class="linha-input">
	                                <label for="cpf">CPF: </label>
	                                <input type="text" name="cpf" id="cpf">
                           		</div>
	                            <div class="linha-input">
	                                <label for="cep">CEP: </label>
	                                <input type="text" name="cep" id="cep">
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
                        </div>
                        <div class="cliente-selecionar-container">
                            <select name="id_cliente" id="id_cliente">
                                <option value="" selected disabled>Selecione</option>
                                <c:forEach items="${clientes}" var="cliente">
                                    <option value="${cliente.id}">${cliente.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="botoes-container">
                            <button class="btn" onclick="btnCadastrarCliente(event)"
                                id="btn_cadastro_cliente">Cadastrar</button>
                            <button class="btn" onclick="btnSelecionarCliente(event)"
                                id="btn_selecionar_cliente">Selecionar</button>
                        </div>
                    </div>
                    <article>
                    <div class="linha-input">
                        <label>Funcionário: </label>
                        <select name="id_funcionario" id="id_funcionario">

                            <option value="" selected disabled>Selecione</option>
                            <c:forEach items="${funcionarios}" var="funcionario">
                                <option value="${funcionario.id}">${funcionario.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                        <div class="linha-input">
                            <label for="valor">Valor: </label>
                            <input type="number" name="valor">
                        </div>
                    </article>
                        <div class="linha-input">
                            <label for="observacao">Observação: </label>
                            <input type="text" name="observacao">
                        </div>
                    <input type="submit" value="Enviar" class="btn" style="margin-top: 1em;">
                </form>
            </div>
        </div>
        <c:import url="components/footer.jsp" />
    </body>
    <script>

        // variÃ¡veis do fomulario de equipamento
        var btn_cadastro_equipamento = document.getElementById('btn_cadastro_equipamento')
        var btn_selecionar_equipamento = document.getElementById('btn_selecionar_equipamento')
        var selectEquipamento = document.getElementById('id_equipamento')
        var modelo = document.getElementById('modelo')
        var cor = document.getElementById('cor')
        var marca = document.getElementById('marca')

        document.querySelector('.equipamento-selecionar-container').style.display = "block";
        document.querySelector('.equipamento-cadastro-container').style.display = "none";
        btn_selecionar_equipamento.style.display = "none";


        // variÃ¡veis do formulario do cliente
        var btn_cadastro_cliente = document.getElementById('btn_cadastro_cliente')
        var btn_selecionar_cliente = document.getElementById('btn_selecionar_cliente')
        var selectCliente = document.getElementById('id_cliente')
        var nome = document.getElementById('nome')
        var numero = document.getElementById('numero')
        var cpf = document.getElementById('cpf')
        var cep = document.getElementById('cep')

        document.querySelector('.cliente-selecionar-container').style.display = "block";
        document.querySelector('.cliente-cadastro-container').style.display = "none";
        btn_selecionar_cliente.style.display = "none";


        function btnCadastrarEquipamento(e) {
            e.preventDefault();
            document.querySelector('.equipamento-selecionar-container').style.display = "none";
            document.querySelector('.equipamento-cadastro-container').style.display = "block";

            btn_cadastro_equipamento.style.display = "none"
            btn_selecionar_equipamento.style.display = "block"

            selectEquipamento.value = ""
        }
        function btnSelecionarEquipamento(e) {
            e.preventDefault();
            document.querySelector('.equipamento-selecionar-container').style.display = "block";
            document.querySelector('.equipamento-cadastro-container').style.display = "none";

            btn_cadastro_equipamento.style.display = "block"
            btn_selecionar_equipamento.style.display = "none"

            modelo.value = ""
            cor.value = ""
            marca.value = ""
        }
        function btnCadastrarCliente(e) {
            e.preventDefault();
            document.querySelector('.cliente-cadastro-container').style.display = "block";
            document.querySelector('.cliente-selecionar-container').style.display = "none";

            btn_cadastro_cliente.style.display = "none"
            btn_selecionar_cliente.style.display = "block"

            selectCliente.value = ""
        }
        function btnSelecionarCliente(e) {
            e.preventDefault();
            document.querySelector('.cliente-cadastro-container').style.display = "none";
            document.querySelector('.cliente-selecionar-container').style.display = "block";

            btn_cadastro_cliente.style.display = "block"
            btn_selecionar_cliente.style.display = "none"

            nome.value = ""
            numero.value = ""
            cpf.value = ""
            cep.value = ""
        }

    </script>

    </html>