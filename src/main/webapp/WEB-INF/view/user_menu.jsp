<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>

</head>
<body>

<h2>Hello USER!</h2>

<ul>
    <li>Имя: <c:out value="${user.name}"/></li>

    <li>Возраст: <c:out value="${user.age}"/></li>

    <form method="get" action="<c:url value='/update'/>">
        <input type="number" hidden name="id" value="${user.id}"/>
        <input type="submit" value="Редактированть"/>
    </form>
</ul>
<hr/>

<a href="<c:url value="/logout"/>">Logout</a>
</body>
</html>
