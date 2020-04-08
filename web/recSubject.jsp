<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Оценки по предмету</h1>
        <a href="index.jsp">Главная страница</a><br>
        <p>${info}</p>
        <p>Вошедший пользователь: ${user.login}</p>
        <form action="recListSubject" method="POST">
            Список предметов:
            <select name="subjectId">
                <c:forEach var="subject" items="${listRecords}">
                    <option value="${subject.id}">${subject.name}</option>
                </c:forEach>
            </select>
            <br>
            <input type="submit" value="Показать оценки по предмету">
        </form>
    </body>
</html>
