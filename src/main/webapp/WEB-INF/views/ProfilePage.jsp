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


<h1 style="text-align:center">User Profile</h1>
<div class= "Profile">
    <form action="/ProfilePage" method="get">
       <h1 <label for="username">User Name: ${username} </label> </h1>
       <h2> Name: ${name}</h2>
       <h2> Surname: ${surname} </h2>
       <h2> Birth Place: ${birthPlace}  </h2>
       <h2> Status: ${status} </h2>
       <form action="MailServlet" method="post">
       <input class = "button button7" type="submit" name="button" value="sendRequestFromProfile">
       <input type="hidden" name="username" value= "${name}">
    </form>

</div>
</body>
</html>
