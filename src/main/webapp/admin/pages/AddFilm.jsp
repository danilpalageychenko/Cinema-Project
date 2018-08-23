<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add film - Cinema</title>
</head>
<body>
<c:out value="${sessionScope.addfilmmessage}"/>

<form action="${pageContext.request.contextPath}/admin/pages/AddFilm" method="post" enctype="multipart/form-data">
    <p> Name: <input type="text" name="name"> </p>
    <p> Description: <input type="text" size="3" name="descr"></p>
    <p> Year: <input type="number" name="year"></p>
    <p> Country: <input type="text" name="country"></p>
    <p>Directors: <input type="text" name="directors"></p>
    <p>Studio: <input type="text" name="studio"></p>
    <p>Genre:
    <select size="1" name="genre">
        <option value="Action">Action</option>
        <option value="Romantic">Romantic</option>
        <option value="Horror">Horror</option>
    </select>
    </p>
    <p>Length: <input type="number" name="length"></p>
    <p>Age Restrictions: <input type="number" name="ageRestr"></p>
    <p>Image:  <input type="file" name="photo" size="50"/></p>

    <br/>
    <input type="submit" value="Add" />




</form>
</body>
</html>
