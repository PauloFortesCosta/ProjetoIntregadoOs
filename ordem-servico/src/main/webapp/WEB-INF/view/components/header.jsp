<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/pages?acao=" var="linkPage" />
<header>
    <a href="/ordem-servico/pages?acao=Home" class="link_logo">
    <img src="static/images/logo_phone.png"/>
        <h2>YourPhone</h2>
    </a>

    <ul class="menu">
        <li>
            Menu
            <ul class="sub-menu">               
                <li><a href="${linkPage}PageLogin">Login</a></li>
                <li><a href="${linkPage}PageFuncionarios">Funcionários</a></li>
                <li><a href="${linkPage}Logout">Sair</a></li>
            </ul>
        </li>
    </ul>
</header>