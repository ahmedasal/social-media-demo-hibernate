<%--
  Created by IntelliJ IDEA.
  User: ahmed
  Date: 11/18/22
  Time: 10:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Please fill your information</h2>

<form action="register" method="post">
    <label for="username">Username:</label><br>
    <input type="text" id="username" name="username"><br>
    <label for="firstname">First name:</label><br>
    <input type="text" id="firstname" name="firstname"><br>
    <label for="lastname">Last name:</label><br>
    <input type="text" id="lastname" name="lastname"><br>
    <label for="password">Password:</label><br>
    <input type="text" id="password" name="password"><br>
    <label for="birthday">Birthday:</label><br>
    <input type="text" id="birthday" name="birthday"><br>
    <label for="email">Email:</label><br>
    <input type="text" id="email" name="email"><br>

    <input type="submit" value="Register">
</form>

</form>
</body>
</html>
