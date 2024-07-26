<%@ page import="com.model.Match" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Завершенные матчи</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>

    <script>
        function goToNextPage(page_number) {
            window.location.href = '/matches?page='+page_number;
        }
    </script>
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

    <form action="" method="get">
        <input style="visibility: hidden; position: absolute" type="number" value="<%=request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")): 1%>">
        <input placeholder="имя игрока" type="text" name="filter_by_player_name">
        <input type="submit" value="поиск">
    </form>

    <form action="" method="get">
        <input style="visibility: hidden; position: absolute" type="number" value="<%=request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")): 1%>">
        <input type="submit" value="сбросить">
    </form>

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
                for (int i = 1; i <= (int) request.getAttribute("pages"); i++) {
                    int page_number = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")): 1;
        %>
            <button class="<%=(i == page_number) ? "button_page_active" : "button_page"%>" onclick="goToNextPage(<%= i %>)"><%= i %></button>
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
