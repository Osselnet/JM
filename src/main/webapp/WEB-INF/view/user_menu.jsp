<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>

</head>
<body>

<h2>Hello <c:out value="${requestScope.user.name}"/>!</h2>

<div>Имя: <c:out value="${requestScope.user.name}"/></div>
<div>Возраст: <c:out value="${requestScope.user.age}"/></div>

<a href="<c:url value="/logout"/>">Logout</a>
</body>
</html>
