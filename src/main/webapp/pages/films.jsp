<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Films - Cinema</title>
</head>
<body>
<%@ include file="../header.jsp" %>
<%@ include file="../mainMenu.jsp" %>

 <c:forEach items="${filmDTOList}" var="films">
     <img src="data:image/png;base64, ${films.image}" width="200" height="300">
        <a href="${pageContext.servletContext.contextPath}/film?id=${films.id}">${films.name}</a>
     ${films.descr}
     ${films.year}
     ${films.country}
     ${films.directors}
     ${films.studio}
     ${films.genre}
     ${films.length}
     ${films.ageRestr}
        <br/>
 </c:forEach>

</body>
</html>
