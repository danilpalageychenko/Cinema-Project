<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><c:out value="${filmDTO.name}" default="err"/> - Cinema</title>
</head>
<body>
<%@ include file="../header.jsp" %>
<%@ include file="../mainMenu.jsp" %>

<img src="data:image/png;base64, ${films.image}" width="200" height="300">
<c:out value="${filmDTO.name}" default="err"/>
<c:out value="${filmDTO.descr}" default="err"/>
<c:out value="${filmDTO.year}" default="err"/>
<c:out value="${filmDTO.country}" default="err"/>
<c:out value="${filmDTO.directors}" default="err"/>
<c:out value="${filmDTO.studio}" default="err"/>
<c:out value="${filmDTO.genre}" default="err"/>
<c:out value="${filmDTO.length}" default="err"/>
<c:out value="${filmDTO.ageRestr}" default="err"/>

</body>
</html>
