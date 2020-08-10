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


<h1 style="text-align:center">Sent Requests</h1>
<a href="/UserServlet"> RETURN TO HOMEPAGE</a>
    <form action="/SentRequests" method="get">
    <div class= "box">
        <ul>
            <c:forEach var="Friend" items="${sentRequests}">
                 <a href= "/ProfilePage?id=${Friend.getUserId()}"><li style="list-style-type: circle;" >${Friend.getUserName()}</li></a>
            </c:forEach>
        </ul>
     </div>
    </form>
</body>
</html>
