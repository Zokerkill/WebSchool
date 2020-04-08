<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Новая оценка</title>
    </head>
    <body>
        <h1>Добавление новой оценки</h1>
        <a href="index.jsp">Главная страница</a><br>
        <p>${info}</p>
        <p>Вошедший пользователь: ${user.login}</p>
        <form action="addGrade" method="POST">
            Балл: <input type="text" name="name"><br>
            Описание: <input type="text" name="text"><br>
            <input type="submit" value="Добавить оценку">
        </form>
    </body>
</html>
