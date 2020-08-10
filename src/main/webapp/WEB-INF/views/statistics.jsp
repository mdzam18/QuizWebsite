<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page session = "true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    body, ul, li, h1, h2, a{
        margin: 0;
        padding: 0;
        font-family: arial;
    }

    /*table design*/

    .wrapper {
        margin: 60px auto;
        padding: 20px;
        max-width: 800px;
    }

    .table {
        margin: 0 0 40px 0;
        width: 100%;
        box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
        display: table;
    }

    .row {
        display: table-row;
        background: #f6f6f6;
    }

    .row.header {
        font-weight: bold;
        font-size: 23px;
        color: #ffffff;
        background: palevioletred;
        padding: 0;
        height: 6px;
    }

    .row.yellow{
        background: darkgoldenrod;
    }

    .row.green {
        background: #27ae60;
    }

    .row.purple {
        background: purple;
    }

    .row.blue{
        background: mediumblue;
    }

    .row .cell {
        font-size: 25px;
        margin-bottom: 20px;
        text-align: center;
        padding: 30px 30px;
        display: table-cell;
    }

    .row.header .cell:before {
        margin-bottom: 3px;
        content: attr(data-title);
        min-width: 98px;
        /* min-height: 20px; */
        font-size: 10px;
        line-height: 10px;
        font-weight: bold;
        text-transform: uppercase;
        /* color: #969696; */
        display: block;
    }

    /*website design*/

    header{
        background-color: green;
        padding: 20px;
        text-align: center;
        position: fixed;
        width: 100%;
        z-index: 1;
        top: 0;
        left: 0;
    }

    header h1{
        color: white;
        border: 8px solid white;
        padding: 6px 12px;
        display: inline-block;
        border-radius: 12px;
        line-height: 1;
        border-bottom: 6px solid #e5e5e5;
        font-family: "Playfair Display", Georgia, "Times New Roman", serif;
        font-size: 2.25rem;
    }

    .banner{
        position: relative;
    }

    .banner img{
        max-width: 100%;
    }

    .banner .welcome{
        text-align: center;
        background-color: #FEB614;
        color: white;
        padding: 30px;
        position: absolute;
        right: 0;
        top: 70%;
    }

    .banner h2{
        font-size: 74px;
        font-family: "Lato Heavy";
    }

    .banner h2 span{
        font-size: 1.2em;
        /* font-size: 90px; */
    }

    nav{
        background-color: #F4F4F4;
        padding: 20px;
        position: sticky;
        top: 106px
    }

    nav ul{
        white-space: nowrap;
        max-width: 1200px;
        margin: 0 auto;
    }

    nav li{
        width: 25%;
        display: inline-block;
        font-size: 24px;
    }

    nav li a{
        text-decoration: none;
        color: #4B4B4B;

    }

    footer{
        background: green;
        color: white;
        padding: 10px;
        text-align: center;
    }

    /* Pseudo Classes */

    nav li a:hover{
        color: red;
        text-decoration: underline;
    }

    section.join p::first-letter{
        font-size: 1.5em;
    }

    p::selection{
        background-color: #F63232;
        color: white;
    }

    /* buttons design */

    .centered {
        margin:50px auto;
        text-align:center;
    }

    .button{
        width: 100px;
        height: 35px;
        border-radius: 10%;
        font-size: 18px;
        cursor: pointer;
    }

    .button:hover{
        background-color: #eee;
        color: #555;
    }

    .button:active{
        background: #e9e9e9;
        position: relative;
        top: 1px;
        text-shadow: none;
        -moz-box-shadow: 0 1px 1px rgba(0, 0, 0, .3) inset;
        -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .3) inset;
        box-shadow: 0 1px 1px rgba(0, 0, 0, .3) inset;
    }

    .button.red, .button.blue {
        color: #fff;
        text-shadow: 0 1px 0 rgba(0,0,0,.2);

        background-image: -webkit-gradient(linear, left top, left bottom, from(rgba(255,255,255,.3)), to(rgba(255,255,255,0)));
        background-image: -webkit-linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));
        background-image: -moz-linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));
        background-image: -ms-linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));
        background-image: -o-linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));
        background-image: linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));
    }

    .button.red{
        background-color: #ca3535;
        border-color: #c43c35;
    }

    .button.red:hover{
        background-color: #ee5f5b;
    }

    .button.red:active{
        background: #c43c35;
    }

    .button.blue{
        background-color: #269CE9;
        border-color: #269CE9;
    }

    .button.blue:hover{
        background-color: #70B9E8;
    }


    /*Statistics page*/

    .id_field {
        margin: 20px 0;
        padding: 10px 20px;
        font-size: 24px;
        border-radius: 28px;
        border: 4px solid white;
    }


    .id_field:focus{
        outline: none; /*remove blue outline from the input */
        border: 4px solid #4b4b4b;
    }

    .id_field:valid{
        border: 4px solid #71d300;
    }

    input[type=submit] {
        background-color: #4CAF50;
        color: white;
        font-size: 20px;
        font-family: Lato;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 3px;
        cursor: pointer;
    }

    input[type=submit]:hover{
        background-color: green;
        /*background-color: rgb(70, 160, 73);*/
    }

    ol.scores li{
        /* text-align: center; */
        font-size: 24px;
        list-style-type: square;

        text-shadow: 2px 2px #c28686
    }

    /* display: flex */
    .score-list, .div, .div1, .div2, .div3 {
        display: flex;
        justify-content: center;
    }

    h4{
        color: #269CE9;
        font-size: 25px;
        font-family: Georgia, serif;
        text-align: center;
    }

    .div{
        padding: 25px 20px;
    }

    p{
        font-size: 27px;
        text-align: center;
        font-family: arial;
    }

    p span{
        font-width: bold;
        color: red;
    }

    .user a{
        position: absolute;
        top: 12px;
        right: 55px;
        font-size: 24px;
        color: yellow;
        font-weight: bold;
    }

    .user a:link {
        text-decoration: none;
    }
    .user a:hover {
        text-decoration: underline;
        color: red;
    }

</style>
<html>
<head>
    <%--    <link rel="stylesheet" href="admin.css">--%>
    <title>Administrator Page</title>
</head>
<body>
<header>
    <div class="user">
        <a href=/UserServlet>Go as User</a>
    </div>
    <h1>Administrator</h1>
</header>
<section class="banner">
    <img src="../../resources/images/nature.jpg">
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
<div>

    <div class="div">
        <h4>Get all taken quizzes for a user:</h4><br>
        <form action="/admin/statistics" method="POST">
            <input class="id_field" type="number" name="field1" placeholder="Enter User Id" required>
            <input name="field2" type="hidden" value=${field2}>
            <input name="field3" type="hidden" value=${field3}>
            <input name="field4" type="hidden" value=${field4}>
            <input name="field5" type="hidden" value=${field5}>
            <input type = "submit" value = "Submit"/>
        </form>
    </div>

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

    <div class="div1">
        <h4>Quiz score history for a user:</h4><br>
        <form action="/admin/statistics" method="POST">
            <input class="id_field" type="number" name="field2" placeholder="Enter User id" required>
            <input class="id_field" type="number" name="field3" placeholder="Enter Quiz id" required>
            <input name="field1" type="hidden" value=${field1}>
            <input name="field4" type="hidden" value=${field4}>
            <input name="field5" type="hidden" value=${field5}>
            <input class = "submit_btn" type = "submit" value = "Submit"/>
        </form>
        <br>
    </div>
    <div class="score-list">
        <ol class="scores">
            <c:forEach var="score" items="${scores}">
                <li>
                        ${score} points
                </li>
            </c:forEach>
        </ol>
    </div>


    <div class="div2">
        <h4>Find out who got the highest score in a quiz:</h4><br>
        <form action="/admin/statistics" method="POST">
            <input class="id_field" type="number" name="field4" placeholder="Enter Quiz id" required>
            <input name="field1" type="hidden" value=${field1}>
            <input name="field2" type="hidden" value=${field2}>
            <input name="field3" type="hidden" value=${field3}>
            <input name="field5" type="hidden" value=${field5}>
            <input class = "submit_btn" type = "submit" value = "Submit"/>
        </form>
    </div>
    <c:if test="${user != null}">
        <p>And the best player is .. <span>${user.userName}</span></p>
    </c:if>

    <div class="div3">
        <h4>Get the average score in a particular quiz:</h4></br>
        <form action="/admin/statistics" method="POST">
            <input class="id_field" type="number" name="field5" placeholder="Enter Quiz id" required>
            <input name="field1" type="hidden" value=${field1}>
            <input name="field2" type="hidden" value=${field2}>
            <input name="field3" type="hidden" value=${field3}>
            <input name="field4" type="hidden" value=${field4}>
            <input class = "submit_btn" type = "submit" value = "Submit"/>
        </form>
    </div>
    <c:if test="${avg != null}">
        <p>The average score is <span>${avg}</span></p>
    </c:if>

</div>

<!-- </nav> -->
<footer>
    <p class="Copyright">Copyright 2020 QuizWebsite</p>
</footer>
</body>
</html>