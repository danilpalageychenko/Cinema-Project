<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users edit - Cinema</title>
</head>
<body>
<%@ include file="../../header.jsp" %>
<%@ include file="../adminMenu.jsp" %>


<table border="2">
    <tr>
        <td>Del</td>
        <td>id</td>
        <td>Login</td>
        <td>Name</td>
        <td>SurName</td>
        <td>Rights</td>
        <td>Bday</td>
        <td>Money</td>
        <td>Tickets</td>
    </tr>
<c:forEach items="${usersDTOList}" var="users">
    <tr>
        <td><a href="${pageContext.request.contextPath}/admin/pages/UsersEdit?del=${users.id}">D</a></td>
        <td>${users.id}</td>
        <td>${users.login}</td>
        <td>${users.name}</td>
        <td>${users.surName}</td>
        <td>${users.role}</td>
        <td>${users.bday}</td>
        <td>${users.money}</td>
        <td>${users.tickets}</td>
    </tr>
</c:forEach>
</table>

</body>
</html>
