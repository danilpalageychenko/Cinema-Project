<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gaara
  Date: 4/5/17
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Buy ticket - Cinema</title>
</head>
<body>

<%@ include file="../header.jsp" %>
<%@ include file="../mainMenu.jsp" %>

    <h3><font color="red"> <c:out value="${sessionScope.buyticketmessage}"/></font></h3>

Chosed film: ${filmName}
Season time: ${date} at ${time}

    Chose place:
    <table border="1">
        <c:set var="y" value="${0}"/>
        <c:forTokens delims="[]" items="${chosedseason.places}" var="placesrow" >
            <tr>
                <c:forTokens delims=",\"" items="${placesrow}" var="place" varStatus="inerloop">
                    <td>
                        <c:if test="${place == 'Empty'}">
                            <a href="/pages/BuyTicket?x=${inerloop.index}&y=${y}&time=${time}&date=${date}">
                            <img src="${pageContext.request.contextPath}/images/hall/empty.png" width="30" height="30" />
                            </a>
                        </c:if>
                        <c:if test="${place == 'Busy'}">
                            <img src="${pageContext.request.contextPath}/images/hall/busy.png" width="30" height="30" />
                        </c:if>
                    </td>
                    <c:if test="${inerloop.last}">
                        <c:set var="y" value="${y+1}"/>
                    </c:if>
                </c:forTokens>
            </tr>


        </c:forTokens>
    </table>
</body>
</html>
