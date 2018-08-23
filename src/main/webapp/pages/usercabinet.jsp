<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gaara
  Date: 4/7/17
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User cabinet - Cinema</title>
</head>
<br>
<%@ include file="../header.jsp" %>
<%@ include file="../mainMenu.jsp" %>


<b>Username:</b> ${user.login} </br>
<b>Name:</b> ${user.name} </br>
<b>Sur-name:</b> ${user.surName} </br>
<b>Birth date:</b> ${user.bday} </br>
<b>Role:</b> ${user.role} </br>
<b>Money:</b> ${user.money} </br>
<b>Tickets:</b> ${user.tickets} </br>

</body>
</html>
