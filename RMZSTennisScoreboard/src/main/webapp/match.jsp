<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Новый матч</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>

<body>


<%

    String matchStatus = "wait";
    if (request.getAttribute("matchStatus") != null)
        matchStatus = request.getAttribute("matchStatus").toString();

    if (matchStatus.equals("game")) {
%>

<h2>Счет (сеты): <p>${resultSets}</p></h2>
<h2>Счет (геймы): <p>${resultGames}</p></h2>
<h2>Счет гейма: <p>${resultGame}</p></h2>

<label type="text" name="player1" class="input">Первый игрок:</label>
<form method="post">
    <button class="button" name="player" value="1"  type="submit">+</button>
</form>
<br><br>
<label type="text" name="player2" class="input">Второй игрок:</label>
<form action="" method="post">
    <button class="button" name="player" value="2" type="submit">+</button>
</form>
<br><br>

<%
} else if (matchStatus.equals("wait")){
%>

<h2>НОВЫЙ</h2>
<h1>МАТЧ</h1>

<form action="" method="post">
    Первый игрок: <input type="text" name="player1" class="input">
    <br><br>
    Второй игрок: <input type="text" name="player2" class="input">
    <br><br>
    <input class="button" type="submit" value="начать">
</form>

<%
    } else if (matchStatus.equals("end")) {
%>

<div class="main_div">
    <h2>МАТЧ</h2>
    <h1>ЗАВЕРШЕН</h1>

    <h2>Сетов сыграно: <p>${resultSets}</p></h2>

    <form action="/" method="get">
        <button class="button" type="submit">К спискам матчей</button>
    </form>

    <form action="" method="post">
        <button class="button" type="submit">Новый матч</button>
    </form>

</div>


<%
    }
%>


</body>
</html>
