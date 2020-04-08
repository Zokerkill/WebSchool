<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Новый пользователь</title>
    </head>
    <body>
        <h1>Добавление нового пользователя</h1>
        <a href="index.jsp">Главная страница</a><br>
        <p>${info}</p>
        <p>Вошедший пользователь: ${user.login}</p>
        <form action="addPerson" method="POST">
            Имя и фамилия: <input type="text" name="name"><br>
            Логин: <input type="text" name="login"><br>
            Пароль: <input type="password" name="password1"><br>
            Повторить пароль: <input type="password" name="password2"><br>
            <input type="submit" value="Добавить пользователя">
        </form>
    </body>
</html>
