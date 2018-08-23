<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Film edit - Cinema</title>
</head>
<body>
<%@ include file="../../header.jsp" %>
<%@ include file="../adminMenu.jsp" %>


<table border="1">
    <tr>
        <td>Del</td>
        <td>Film name</td>
        <td>Description</td>
        <td>Year</td>
        <td>Country</td>
        <td>Directors</td>
        <td>Studio</td>
        <td>Genre</td>
        <td>Length</td>
        <td>Age restr.</td>
    </tr>
    <c:forEach items="${films}" var="film">
        <tr>
            <td><a href="${pageContext.request.contextPath}/admin/pages/FilmsEdit?del=${film.id}">D</a></td>
            <td>${film.name}</td>
            <td>${film.descr}</td>
            <td>${film.year}</td>
            <td>${film.country}</td>
            <td>${film.directors}</td>
            <td>${film.studio}</td>
            <td>${film.genre}</td>
            <td>${film.length}</td>
            <td>${film.ageRestr}</td>
        </tr>
    </c:forEach>

</table>

<a href="AddFilm.jsp">Add new film</a>


</body>
</html>
