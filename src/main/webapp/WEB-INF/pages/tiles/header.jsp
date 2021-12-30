<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="header">

<header>
    <div class="navbar navbar-static navbar-inverse">
        <div class="navbar-inner">
            <a href="/portal/index" class="brand">Фото Портал</a>
            <ul class="nav pull-right">
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                <li><a href="/portal/registration">Регистрация</a></li>
                <li><a href="/portal/login">Войти</a></li>
            </sec:authorize>
             <sec:authorize access="isFullyAuthenticated()">
                <li><a href="/portal/profile">Мой профиль</a></li>
                <li><a href="/portal/logout">Выйти[<sec:authentication property="principal.username" />]</a></li>
             </sec:authorize>
            </ul>
        </div>
    </div>
</header>

</div>