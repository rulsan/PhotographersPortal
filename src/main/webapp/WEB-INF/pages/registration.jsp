<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="span6 offset3">
<form:form method="post" action="registration" class="form-horizontal" modelAttribute="registerUserDTO" name="registerUserDTO">
  <div class="control-group">
    <label class="control-label" for="inputLogin">Имя</label>
    <div class="controls">
      <form:input path="login" placeholder="Имя"/><br>
      <span class="text-error"><em><form:errors path="login"/></em></span>
    </div>
  </div>
  <div class="control-group">
   <label class="control-label" for="inputEmail">Электронная почта</label>
   <div class="controls">
       <form:input path="email" placeholder="E-mail"/><br>
       <span class="text-error"><em><form:errors path="email"/></em></span>
   </div>
 </div>
 <div class="control-group">
   <label class="control-label" for="inputPassword">Пароль</label>
   <div class="controls">
     <input type="password" name="pass" id="inputPassword" placeholder="Пароль"><br>
     <span class="text-error"><em><form:errors path="pass"/></em></span>
   </div>
 </div>
 <div class="control-group">
   <label class="control-label" for="inputConfirmPassword">Подтвердите пароль</label>
   <div class="controls">
   <input type="password" name="confirmPass" id="inputConfirmPassword" placeholder="Подтвердите пароль"><br>
   <span class="text-error"><em><form:errors path="confirmPass"/></em></span>
 </div>
 </div>
 <div class="control-group">
   <div class="controls">
     <button type="submit" class="btn">Зарегистрироваться</button>
   </div>
 </div>
</form:form>
</div>
