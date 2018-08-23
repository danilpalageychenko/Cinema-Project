<%--
  Created by IntelliJ IDEA.
  User: gaara
  Date: 3/27/17
  Time: 11:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Hall - Cinema</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/js/HallsCheckBoxs.js"></script>
</head>
<body>
<%@ include file="../../header.jsp" %>
<%@ include file="../adminMenu.jsp" %>

<c:out value="${sessionScope.message}"/>
<table id="table1" border="2">
</table>

<form id="form1" method="post" style="margin:0px;" onsubmit="makeJson()" action="${pageContext.request.contextPath}/admin/pages/AddHall">
    Hall Name:
    <input type="text" name="hallName">
    <input type="hidden" name="jsonString" id="jsonString"/>
    <input type="submit" value="Submit">
</form>
<input type="button" value="+" onclick="CreateRow()" style="height:30px;width:30px;margin-left:6px;float:left;">
<input type="button" value="-" onclick="RemoveRow()" style="height:30px;width:30px;margin:0;">
</body>
</html>
