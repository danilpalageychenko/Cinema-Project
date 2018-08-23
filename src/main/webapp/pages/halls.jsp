<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Halls - Cinema</title>
</head>
<body>
<%@ include file="../header.jsp" %>
<%@ include file="../mainMenu.jsp" %>


<form action="${pageContext.servletContext.contextPath}/pages/halls" method="post">
    <p><select size="3" name="hallcolor">
        <c:if test="${halls != '[]'}">
            <c:forEach items="${halls}" var="halls">
                <option value="${halls.hallName}">${halls.hallName}</option>
            </c:forEach>
        </c:if>

        <c:if test="${halls == '[]'}">
            <option disabled>Halls are empty</option>
        </c:if>
    </select></p>
    <p><input type="submit" value="Выбрать"></p>
</form>

<c:if test="${selectedhall != null}">
    <table border="1">
        <c:forTokens delims="[]" items="${selectedhall.places}" var="placesrow">
            <tr>
                <c:forTokens delims=",\"" items="${placesrow}" var="place">
                    <td>
                        <c:if test="${place == 'Empty'}">
                            <img src="${pageContext.request.contextPath}/images/hall/empty.png" width="30" height="30" />
                        </c:if>
                    </td>
                </c:forTokens>
            </tr>
        </c:forTokens>
    </table>
</c:if>

</body>
</html>
