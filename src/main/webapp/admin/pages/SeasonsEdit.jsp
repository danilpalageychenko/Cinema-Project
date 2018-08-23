<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gaara
  Date: 3/27/17
  Time: 10:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Seasons edit - Cinema</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.timeentry.css">

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.plugin.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.timeentry.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );
        $(document).ready(function(){
            $(".time_element").timeEntry({show24Hours: true, spinnerImage: ''});
        });
    </script>
</head>
<body>
<%@ include file="../../header.jsp" %>
<%@ include file="../adminMenu.jsp" %>


<table border="1">
    <tr>
        <td>Del</td>
        <td>Film Name</td>
        <td>Hall</td>
        <td>When</td>
        <td>Price</td>
    </tr>
<c:forEach items="${seasons}" var="season" varStatus="loop">
    <tr>
        <td><a href="${pageContext.request.contextPath}/admin/pages/SeasonsEdit?del=${season.id}">D</a></td>
        <td >
            ${filmNames[loop.index]}
        </td>
        <td>${season.hallName}</td>
        <td>${season.time}</td>
        <td>${season.price}</td>
    </tr>
</c:forEach>
</table>
<form action="/admin/pages/SeasonsEdit">
    <input type="text" name="filmname">
    <select size="1" name="hall">
        <c:if test="${halls != '[]'}">
            <c:forEach items="${halls}" var="halls">
                <option value="${halls.hallName}">${halls.hallName}</option>
            </c:forEach>
        </c:if>
    </select>
    <input type="text" name="date" id="datepicker">
    <input type="text" name="time" class="time_element"/>
    <input type="number" name="price">

    <input type="submit" value="Add" />


</form>
<c:out value="${sessionScope.seasonseditmessage}"/>

</body>
</html>
