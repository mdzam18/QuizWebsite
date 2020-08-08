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


<h1 style="text-align:center">Friends Requests</h1>
<a href="/UserServlet"> Back to homepage</a>
    <form action="MailServlet" method="post">
        <ul>
            <c:forEach var="Friend" items="${Requests}">
                 <li style="list-style-type: circle;" > <a href= "/ProfilePage?id=${Friend.getUserId()}">${Friend.getUserName()}</a> </li>
                <input type="hidden" name="username" value= ${Friend.getUserName()} <br/>
                <input class="button button7" type="submit" name="button" value="confirmRequest" >
            </c:forEach>
        </ul>
    </form>
</body>
</html>
