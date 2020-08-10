<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page session = "true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    body, ul, li, h1, h2, a{
        margin: 0;
        padding: 0;
        font-family: arial;
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

    input[type=submit] {
        background-color: #4CAF50;
        color: white;
        font-size: 18px;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type=submit]:hover{
        background-color: rgb(70, 160, 73);
    }

    ol.scores li{
        /* text-align: center; */
        font-size: 24px;
        list-style-type: square;

        text-shadow: 2px 2px #c28686
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
<footer>
    <p class="Copyright">Copyright 2020 QuizWebsite</p>
</footer>
</body>
</html>