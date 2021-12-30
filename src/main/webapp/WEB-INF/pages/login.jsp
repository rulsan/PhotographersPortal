<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<p class="text-center text-error">${fail}</p>
<div class="span6 offset3">
<form:form method="post" action="j_spring_security_check" class="form-horizontal">
  <div class="control-group">
    <label class="control-label" for="inputEmail">Электронная почта</label>
    <div class="controls">
      <input type="text" name='j_username' id="inputEmail" placeholder="E-mail">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputPassword">Пароль</label>
    <div class="controls">
      <input type="password" name='j_password' id="inputPassword" placeholder="Пароль">
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
      <label class="checkbox">
        <input type="checkbox" name="_spring_security_remember_me">Запомнить
      </label>
      <button type="submit" name="submit" class="btn">Вход</button>
    </div>
  </div>
</form:form>
</div>
