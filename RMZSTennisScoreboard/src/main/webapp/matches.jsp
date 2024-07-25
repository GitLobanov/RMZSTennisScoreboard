<%@ page import="com.model.Match" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ranob
  Date: 7/25/2024
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Завершенные матчи</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>

<header>
    <h2>Завершенные матчи</h2>
    <ul>
        <a href="/" class="button_2">Главная страница</a>
    </ul>

    <ul>
        <a href="match" class="button_2">Новый матч</a>
    </ul>
</header>


<div class="scroll-container">
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
