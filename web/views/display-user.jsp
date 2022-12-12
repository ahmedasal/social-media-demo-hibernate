<%--
  Created by IntelliJ IDEA.
  User: ahmed
  Date: 11/19/22
  Time: 1:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>User </title>
</head>
<body>

<c:if test="${user != null}">
    <h1> Hello, <c:out value="${user.firstname}"></c:out> </h1>
</c:if>
<c:if test="${user == null}">
    <h1>Welcome social</h1>
</c:if>

</body>
</html>
