<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="<c:url value="/resources/JS/functions/imagesvalid.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/JS/avataruploader.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/JS/pickavatar.js" />"></script>

<div class="span3">
<div class="avatar">
  <img src="<c:url value="${avatarURL}"/>">
  <a id="change" href="">Сменить аватар</a>
</div>
<div class="pop">
  <form id="sendavatar" method="post" enctype="multipart/form-data" action="<c:url value="/profile/saveAvatar"/>">
    <a id="close" href="">Отмена</a><br>
    <input required="required" accept="image/x-png, image/gif, image/jpeg, image/jpg" type="file" name="newAvatar" id="file-picker" class="btn file-input-button" value="Загрузить изображение"/>
    <input type="submit" id="submit" class="btn" value="Ok">
    <div id="info"> </div><!-- сюда будет выводится информация о заливке -->
    <p class="text-error">${uploadError}</p>
    <p id="noavatars"></p>
  </form>
</div>
</div>
<div class="span9">
<dl class="dl-horizontal">
    <dt><h4>${username}</h4></dt>
    <dd></dd>
</dl>
<dl class="dl-horizontal">
    <dt>Зарегистрирован:</dt>
    <dd><fmt:formatDate value="${memberSince}" pattern="dd-MM-yyyy" /></dd>
</dl>
<dl class="dl-horizontal">
    <dt>Электронная почта:</dt>
    <dd>${email}</dd>
</dl>

<form:form method="post" action="/portal/profile/action/update-user" modelAttribute="profileUserDTO" name="profileUserDTO">
<dl class="dl-horizontal">
    <dt>Показать свой E-mail:</dt>
    <dd>
    <form:checkbox path="visibleEmail" />
    </dd>
</dl>
<dl class="dl-horizontal">
    <dt>Оборудование:</dt>
    <dd>
    <form:textarea class="edit_area" id="edit_area1" path="camera" />
    </dd>
</dl>
<dl class="dl-horizontal">
    <dt>Контактные данные:</dt>
    <dd>
    <form:textarea class="edit_area" id="edit_area2" path="contacts" />
    </dd>
</dl>
<dl class="dl-horizontal">
    <dt><a class="black" href="/portal/profile/changePass">Сменить пароль</a></dt>
</dl>
<div class="control-group center_button">
    <div class="controls">
        <button type="submit" class="btn">Сохранить</button>
    </div>
</div>
</form:form>
</div>