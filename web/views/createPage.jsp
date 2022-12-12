<%--
  Created by IntelliJ IDEA.
  User: ahmed
  Date: 12/3/22
  Time: 1:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>createPage</title>
</head>
<body>
<h2>Please fill your Page's information</h2>

<form action="createPage" method="post">
  <label for="pageName">page name:</label><br>
  <input type="text" id="pageName" name="pageName"><br>
  <input type="submit" value="Create page">
</form>

</body>
</html>
