<%--
  Created by IntelliJ IDEA.
  User: yulmkao
  Date: 23.10.2026
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Save</title>
</head>
<body>
<h1>сохранить пользователей</h1>
<div>
  <form action="/save" method="post">
    <input type="hidden" name="count" value="${numbersForJsp.size()}">
    <c:forEach items="${numbersForJsp}" var="num">
      <div>
        <input type="text" name="name_${num}" placeholder="name">
        <input type="text" name="surname_${num}" placeholder="surname">
        <input type="text" name="age_${num}" placeholder="age">
        <input type="text" name="email_${num}" placeholder="email">
        <input type="text" name="city_${num}" placeholder="city">
        <input type="text" name="phone_${num}" placeholder="phone">
      </div>
    </c:forEach>
    <input type="submit" value="save">
  </form>
</div>
</body>
</html>