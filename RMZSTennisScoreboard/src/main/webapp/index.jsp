<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.service.MatchService" %>
<%@ page import="com.model.Match" %>
<!-- Импортируйте ваш класс завершенного матча -->
<!DOCTYPE html>
<html>
<head>
    <title>Матчи</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<div class="main_div">
    <h1>Добро пожаловать на страницу матчей!</h1>

    <ul>
        <a href="match" class="button">Создать новый матч</a>
    </ul>

    <h2>Завершенные матчи:</h2>
    <table class="zigzag">
        <thead>
        <tr>
            <th class="header">Номер</th>
            <th class="header">Игрок №1</th>
            <th class="header">Игрок №2</th>
            <th class="header">Победитель</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Предположим, что у вас есть метод для получения завершенных матчей
            List<Match> completedMatches = (List<Match>) request.getAttribute("matches");
            if (completedMatches != null && !completedMatches.isEmpty()) {
                for (Match match : completedMatches) {
        %>
        <tr>
            <td><%= match.getId() %></td>
            <td><%= match.getPlayer1().getName() %></td>
            <td><%= match.getPlayer2().getName() %></td>
            <td><%= match.getWinner().getName() %></td>
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
        </tbody>
    </table>

</div>

</body>
</html>