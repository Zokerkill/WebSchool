<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Редактирование атрибутов оценки</h1>
        <a href="index.jsp">Главная страница</a><br>
        <p>${info}</p>
        <p>Вошедший пользователь: ${user.login}</p>
        <form action="changeGrade" method="POST">
            <input type="hidden" name="id" value="${grade.id}">
            Балл: <input type="text" name="name" value="${grade.name}"><br>
            Описание: <input type="text" name="text" value="${grade.text}"><br>
            <input type="submit" value="Сохранить изменения">
        </form>
    </body>
</html>
