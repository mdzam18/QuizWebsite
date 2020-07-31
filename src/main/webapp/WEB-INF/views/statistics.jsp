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
        <li><a href="/admin/Statistics">Statistics</a></li>
    </ul>
</nav>
<div>

    <form action="/admin/statistics" method="POST">
        <input type="number" name="field1" placeholder="Type id number" required>
        <input type = "submit" value = "Submit"/>
    </form>

    <div class="wrapper">

        <div class="table">

            <div class="row header purple">
                <div class="cell">
                    Id
                </div>
                <div class="cell">
                    Description
                </div>
                <div class="cell">
                    Category
                </div>
                <div class="cell">
                    Creator Id
                </div>
            </div>

            <c:forEach var="quiz" items="${quizzes}">
                <div class="row">
                    <div class="cell" data-title="Id">
                            ${quiz.quizId}
                    </div>
                    <div class="cell" data-title="Description">
                            ${quiz.description}
                    </div>
                    <div class="cell" data-title="Category">
                            ${quiz.category}
                    </div>
                    <div class="cell" data-title="Creator Id">
                            ${quiz.creatorId}
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <h4>Quiz score history for a user:</h4><br>
    <form>
        <input class="id_field" type="number" name="field2" placeholder="Enter User id" required>
        <input class="id_field" type="number" name="field3" placeholder="Enter Quiz id" required>
        <input class = "submit_btn" type = "submit" value = "Submit"/>
    </form>

    <ul>
        <c:forEach var="score" items="${scores}">
            <li>
                    ${score}
            </li>
        </c:forEach>
    </ul>

    <h4>Find out who got the highest score in a quiz:</h4><br>
    <form>
        <input class="id_field" type="number" name="field4" placeholder="Enter Quiz id" required>
        <input class = "submit_btn" type = "submit" value = "Submit"/>
    </form>
    <c:if test="${user != null}">
        <p>And the best player is .. ${user.userName}</p>
    </c:if>

    <h4>Calculate the average score in a particular quiz:</h4><br>
    <form>
        <input class="id_field" type="number" name="field5" placeholder="Enter Quiz id" required>
        <input class = "submit_btn" type = "submit" value = "Submit"/>
    </form>
    <c:if test="${avg != null}">
        <p>The average score is ${avg}</p>
    </c:if>


</div>

<!-- </nav> -->
<footer>
    <p class="Copyright">Copyright 2020 QuizWebsite</p>
</footer>
</body>
</html>