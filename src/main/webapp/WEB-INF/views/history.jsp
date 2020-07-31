<%@ page import="java.util.Date" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page session = "true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="admin.css">
    <title>Administrator Page</title>
</head>
<body>
<header>
    <h1>Administrator</h1>
</header>
<section class="banner">
    <img src="images/cat.jpg" alt="Administrator Welcome Banner">
    <div class="welcome">
        <h2>Welcome to <br><span>Administrator Page</span></h2>
    </div>
</section>
<nav class="main-nav">
    <ul>
        <li><a href="/admin/admins" class="admins">Admins</a></li>
        <li><a href="/admin/users">Users</a></li>
        <li><a href="/admin/quizzes">Quizzes</a></li>
        <li><a href="/admin/history">History</a></li>
        <li><a href="/admin/statistics">Statistics</a></li>
    </ul>
</nav>
<div class="wrapper">

    <div class="table">

        <div class="row header yellow">
            <div class="cell">
                User Id
            </div>
            <div class="cell">
                Quiz Id
            </div>
            <div class="cell">
                Score
            </div>
            <%--                <div class="cell">--%>
            <%--                    Time Needed--%>
            <%--                </div>--%>
        </div>

        <c:forEach var="history" items="${histories}">
            <div class="row">
                <div class="cell" data-title="User Id">
                        ${history.userId}
                </div>
                <div class="cell" data-title="Quiz Id">
                        ${history.quizId}
                </div>
                <div class="cell" data-title="Score">
                        ${history.score}
                </div>
                    <%--                    <div class="cell" data-title="Time Needed">--%>
                    <%--                        var time = <%= (new Date().getTime())/1000 %>--%>
                    <%--  DATE-is time-ad gardaqmna   --%>
                    <%--                    </div>--%>
            </div>
        </c:forEach>
        <div class="centered">
            <form action="/admin/history?id=1" method="POST">
                <button class="small red button" type="submit" name="delete_btn">Delete All</button>
            </form>
        </div>

    </div>
</div>
<footer>
    <p class="Copyright">Copyright 2020 QuizWebsite</p>
</footer>
</body>
</html>