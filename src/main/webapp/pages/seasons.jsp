<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gaara
  Date: 3/29/17
  Time: 11:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Seasons - Cinema</title>
</head>
<body>
<%@ include file="../header.jsp" %>
<%@ include file="../mainMenu.jsp" %>


<h2><a href="${pageContext.request.contextPath}/pages/seasons?todayday=${todayday}&todaymonth=${todaymonth}&todayyear=${todayyear}&minusdays=1"><</a> ${todayday}/${todaymonth}/${todayyear} <a href="${pageContext.request.contextPath}/pages/seasons?todayday=${todayday}&todaymonth=${todaymonth}&todayyear=${todayyear}&plusdays=1">></a></h2>

<table border="1">
    <tr>
        <td>Film Name</td>
        <td>Hall</td>
        <td>Seasons</td>
        <td>Price</td>
    </tr>

    <c:forEach items="${pseasons}" var="pseason">
        <tr>
            <td>${pseason.filmName}</td>
            <td>${pseason.hallName}</td>
            <td>
            <c:forEach items="${pseason.times}" var="time">
                <a href="${pageContext.request.contextPath}/pages/BuyTicket?time=${time}&date=${todayday}/${todaymonth}/${todayyear}">${time}</a>
            </c:forEach>
            </td>
            <td>${pseason.price}grn</td>
        </tr>
    </c:forEach>


</table>
</body>
</html>
