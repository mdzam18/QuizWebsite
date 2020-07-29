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
    <img src="img/cat.jpg" alt="Administrator Welcome Banner">
    <div class="welcome">
        <h2>Welcome to <br><span>Administrator Page</span></h2>
    </div>
</section>
<nav class="main-nav">
    <div class="wrapper">

        <div class="table">

            <div class="row header">
                <div class="cell">
                    User Id
                </div>
                <div class="cell">
                    Quiz Id
                </div>
                <div class="cell">
                    Score
                </div>
                <div class="cell">
                    Time Needed
                </div>
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
                    <div class="cell" data-title="Time Needed">
<%--                        var time = <%= (new Date().getTime())/1000 %>--%>
                    <%--  DATE-is time-ad gardaqmna   --%>
                        ${(history.startDate - history.endDate)/1000}
                    </div>
                    <div class="centered">
                        <form action="/admin/history?userId=${history.userId}&quizId=${history.quizId}" method="POST">
                            <button class="small red button" type="submit">Delete</button>
                        </form>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</nav>
<footer>
    <p class="Copyright">Copyright 2020 QuizWebsite</p>
</footer>
</body>
</html>