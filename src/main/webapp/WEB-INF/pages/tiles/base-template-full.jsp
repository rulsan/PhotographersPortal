<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="copyright" land="ua" content="SkillsUp"/>
    <meta name="description" content="Фотопортал — социальная сеть для людей увлекающихся искусством фотографии."/>
    <meta name="Keywords" content="фото, фотография, фотоаппарат, сообщество"/>
    <script type="text/javascript" src="<c:url value="/resources/JS/jquery.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/JS/jquery.tmpl.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/style.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/ownstyle.css"/>">
        <title>
            <tiles:getAsString name="title" />
        </title>
    </head>
    <body>
        <tiles:insertAttribute name="header" />
        <div class="row-fluid">
            <div class="span12" id="content"><tiles:insertAttribute name="content" /></div>
        </div>
        <tiles:insertAttribute name="footer" />
    </body>
</html>