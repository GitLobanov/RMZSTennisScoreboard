<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.service.FinishedMatchesPersistenceService" %>
<%@ page import="com.model.Match" %>
<!-- Импортируйте ваш класс завершенного матча -->
<!DOCTYPE html>
<html>
<head>
    <title>Матчи</title>
</head>
<body>
<h1>Добро пожаловать на страницу матчей!</h1>

<ul>
    <li><a href="newMatch">Создать новый матч</a></li>
</ul>

<h2>Завершенные матчи:</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Команда 1</th>
        <th>Команда 2</th>
        <th>Счет</th>
    </tr>
    <%
        // Предположим, что у вас есть метод для получения завершенных матчей
        List<Match> completedMatches = (List<Match>) request.getAttribute("completedMatches");
        if (completedMatches != null && !completedMatches.isEmpty()) {
            for (Match match : completedMatches) {
    %>
    <tr>
        <td><%= match.getId() %></td>
        <td><%= match.getPlayer2() %></td>
        <td><%= match.getPlayer2() %></td>
        <td><%= match.getWinner() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">Нет завершенных матчей.</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>