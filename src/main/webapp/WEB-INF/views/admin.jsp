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
    </ul>
</nav>
<footer>
    <p class="Copyright">Copyright 2020 QuizWebsite</p>
</footer>
</body>
</html>