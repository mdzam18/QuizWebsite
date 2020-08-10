<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="FriendsPackage.FriendsSqlDao" %>
<%@ page import="UserPackage.UserSqlDao" %>
<%@ page import="UserPackage.User" %>
<%@ page import="Quiz.Quiz" %>
<%@ page import="Quiz.QuizSqlDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>


<html>
<head>
    <link href="../style.css" rel="stylesheet" type="text/css">
    <title>Welcome</title>
</head>
<body>
<script language=javascript>
    function hide(id) {
        var div_ref = document.getElementById(id);
        div_ref.style.display = "none";
    }

    function show(id) {
        var div_ref = document.getElementById(id);
        div_ref.style.display = "block";
    }
</script>

<style>
        .box ul li{
            List-style: none;
            padding: 10px;
            width: 80%;
            background: #fff;
            box-shadow: 0 5px 25px rgba(0, 0, 0, .1);
            transition: transform 0.5s;
        }

        .box ul li:hover {
            transform: scale(1.1);
            z-index: 100;
            background: #25bcff;
            box-shadow: 0 5px 25px rgba(0,0,0, .2);
            color: #fff;
        }
</style>


<h1 style="text-align:center">Friends Requests</h1>
 <a href="/UserServlet"> RETURN TO HOMEPAGE</a>
     <div class= "box">
        <ul>
            <c:forEach var="Friend" items="${Requests}">
                 <a href= "/ProfilePage?id=${Friend.getUserId()}"><li style="list-style-type: circle;" >${Friend.getUserName()}</li></a>
                <form action="MailServlet" method="post">
                <input type="hidden" name="username" value= ${Friend.getUserId()} <br/>
                <input class="button button7" type="submit" name="button" value="confirmRequest" >
                </form>
            </c:forEach>
        </ul>
   </div>
</body>
</html>
