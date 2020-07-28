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
                    Name
                </div>
                <div class="cell">
                    Surname
                </div>
                <div class="cell">
                    Username
                </div>
            </div>

            <c:forEach var="user" items="${users}">
                <div class="row">
                    <div class="cell" data-title="Id">
                        ${user.userId}
                    </div>
                    <div class="cell" data-title="Name">
                        ${user.name}
                    </div>
                    <div class="cell" data-title="Surname">
                        ${user.surname}
                    </div>
                    <div class="cell" data-title="Username">
                        #{user.userName}
                    </div>
                    <form action="/admin/users?id=${user.id}" method="POST">
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