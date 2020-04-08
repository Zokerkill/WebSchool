<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My School</title>
    </head>
    <body>
        <h1>Редактирование ролей у пользователей!</h1>
        <p>${info}</p>
        <a href="index">Главная страница</a><br>
        <form action="changeRole" method="POST">
            Список пользователей: 
            <select name="userId">
                <option value="#" hidden></option>
                <c:forEach var="entry" items="${mapUsers}">
                    <option value="${entry.key.id}">${entry.key.login} с ролью ${entry.value}</option>
                </c:forEach>
            </select>
            <span>  </span>
            Список ролей: 
            <select name="roleId">
                <option value="#" hidden></option>
                <c:forEach var="role" items="${listRoles}">
                    <option value="${role.id}">${role.role}</option>
                </c:forEach>
            </select>
            <br>
            <input type="submit" value="Изменить">
        </form>
    </body>
</html>
