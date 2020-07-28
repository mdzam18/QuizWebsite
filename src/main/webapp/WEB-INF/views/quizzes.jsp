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
                    Id
                </div>
                <div class="cell">
                    Category
                </div>
                <div class="cell">
                    Creator Id
                </div>
                <div class="cell">
                    Creator Username
                </div>
                <div class="cell">
                    Description
                </div>
            </div>

            <c:forEach var="quiz" items="${quizzes}">
                <div class="row">
                    <div class="cell" data-title="Id">
                        ${quiz.quizId}
                    </div>
                    <div class="cell" data-title="Category">
                        ${quiz.category}
                    </div>
                    <div class="cell" data-title="Creator Id">
                        ${quiz.creatorId}
                    </div>
                    <div class="cell" data-title="Username">
                        ${quiz.surname}
                    </div>
                    <div class="cell" data-title="Description">
                        ${quiz.description}
                    </div>
                    <form action="/admin/quizzes?id=${quiz.id}" method="POST">
                        <button type="submit">Delete</button>
                    </form>
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