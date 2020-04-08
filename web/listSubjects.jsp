<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Список предметов</h1>
        <a href="index.jsp">Главная страница</a><br>
        <p>${info}</p>
        <p>Вошедший пользователь: ${user.login}</p>
        <ul>
            <c:forEach var="subject" items="${listSubjects}">
                <li>
                    Предмет: ${subject.name}. Учитель: ${subject.teacher}. 
                    <a href="editSubject?id=${subject.id}">Изменить</a>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
