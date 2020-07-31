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

        <div class="row header blue">
            <div class="cell">
                Id
            </div>
            <div class="cell">
                Name
            </div>
            <div class="cell">
                Surname
            </div>
            <div class="cell">
                Username
            </div>
        </div>

        <c:forEach var="admin" items="${admins}">
            <div class="row">
                <div class="cell" data-title="Id">
                        ${admin.userId}
                </div>
                <div class="cell" data-title="Name">
                        ${admin.name}
                </div>
                <div class="cell" data-title="Surname">
                        ${admin.surname}
                </div>
                <div class="cell" data-title="Username">
                        ${admin.userName}
                </div>
            </div>
        </c:forEach>

    </div>
</div>
<footer>
    <p class="Copyright">Copyright 2020 QuizWebsite</p>
</footer>
</body>
</html>