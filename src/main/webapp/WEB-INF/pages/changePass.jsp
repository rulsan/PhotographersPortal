<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="span6 offset3">
<form:form method="post" action="/portal/profile/changePass" class="form-horizontal" modelAttribute="changePassUserDTO" name="changePassUserDTO">
  <div class="control-group">
    <div class="controls">
      <input type="password" name="currentPass" id="inputCurrentPassword" placeholder="Текущий пароль"><br>
      <span class="text-error"><em><form:errors path="currentPass"/></em></span>
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
      <input type="password" name="newPass" id="inputNewPassword" placeholder="Новый пароль"><br>
      <span class="text-error"><em><form:errors path="newPass"/></em></span>
    </div>
  </div>
  <div class="control-group">
      <div class="controls">
      <input type="password" name="confirmNewPass" id="inputConfirmNewPassword" placeholder="Подтвердите новый пароль"><br>
      <span class="text-error"><em><form:errors path="confirmNewPass"/></em></span>
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
      <button type="submit" class="btn">Изменить пароль</button>
    </div>
  </div>
  <div class="control-group controls">${successChangePass}</div>
  <div class="control-group controls"><a class="black" href="/portal/profile/update-user">${successChangePassRedirect}</a></div>
</form:form>
</div>

