<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="principal"/>
<c:choose>
    <c:when test="${emailCheck eq principal}">
        <script>
        $(document).ready(function() {
            $("#profile").addClass("active");
        });
        </script>
    </c:when>
    <c:otherwise>
        <script>
        $(document).ready(function() {
           $("#otherprofiles").addClass("active");
        });
        </script>
    </c:otherwise>
</c:choose>
</sec:authorize>

<div class="row-fluid">
<div class="span3">
    <div class="avatar">
        <img src="<c:url value="${avatarURL}"/>">
    </div>
</div>
<div class="span9">
    <p id="info">
        <h4>
        ${username}<br>
        <small>Последняя активность: <fmt:formatDate value="${lastDate}" pattern="dd-MM-yyyy" /></small>
    </h4>
    </p>
</div>
</div>
<div class="row-fluid">
<div class="span12">
<dl class="dl-horizontal">
  <dt>Имя:</dt>
  <dd>${username}</dd>
</dl>
<dl class="dl-horizontal">
  <dt>Зарегистрирован:</dt>
  <dd><fmt:formatDate value="${memberSince}" pattern="dd-MM-yyyy" /></dd>
</dl>
<dl class="dl-horizontal">
  <dt>Электронная почта:</dt>
  <dd>${email}</dd>
</dl>
<dl class="dl-horizontal">
  <dt>Оборудование:</dt>
  <dd>${devices}</dd>
</dl>
<dl class="dl-horizontal">
  <dt>Контактные данные:</dt>
  <dd>${contacts}</dd>
</dl>
</div>
</div>
