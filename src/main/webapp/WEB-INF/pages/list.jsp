<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="<c:url value="/resources/JS/showmoreusers.js" />"></script>

<script id="listTemplate" type="text/x-jquery-tmpl">
   <dl class="listuser">
      <dt class="userId">
          <a href="\${user.id}">\${user.login}</a>
      </dt>
      <dd class="email">
       \${user.displayedEmail}
      </dd>
   </dl>
</script>

<div class="span8">
   <ul id="listofusers" class="nav nav-pills nav-stacked">
   <h5><p id = "noSearchedUsers" class="text-info"></p></h5>
   <c:forEach items="${profileList}" var="user">
      <dl class="listuser">
         <dt class="userId">
            <a href="${user.id}">${user.login}</a>
         </dt>
         <dd class="email">
            ${user.displayedEmail}
         </dd>
      </dl>
   </c:forEach>
   </ul>
</div>
<div class="span1">
  <div class="input-append searchfield">
    <input id="sfield" size=15 type="text" placeholder="Введите имя">
    <span class="add-on"><i class="icon-search"></i></span>
  </div>
</div>

