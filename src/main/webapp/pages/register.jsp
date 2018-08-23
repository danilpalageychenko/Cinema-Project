<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register - Cinema</title>
    <link rel="stylesheet" type="text/css" href="../styles/register.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );
    </script>
</head>
<body>
<%@ include file="../header.jsp" %>
<%@ include file="../mainMenu.jsp" %>

<c:out value="${sessionScope.registermessage}"/>
<form name="registerForm" method="post" action="${pageContext.servletContext.contextPath}/register">
    Username: <input type="text" name="login"/> <br/>
    Password: <input type="password" name="password"/> <br/>
    Name: <input type="text" name="name"/> <br/>
    Sur-name: <input type="text" name="surname"/> <br/>
    Birth date: <input type="text" name="bday" id="datepicker">



    <input type="submit" value="Register" />
</form>
</body>
</html>
