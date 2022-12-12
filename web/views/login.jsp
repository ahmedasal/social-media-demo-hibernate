<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>login</title>
</head>
<body>

    <c:if test="${not empty error}">
        <h3>    <c:out value="${error}" ></c:out></h3>
    </c:if>

<form action="login" method="post">
    <label for="username">Username:</label><br>
    <input type="text" id="username" name="username"><br>
    <label for="password">password:</label><br>
    <input type="text" id="password" name="password"><br>

    <input type="submit" value="login">
</form>
</body>
</html>
