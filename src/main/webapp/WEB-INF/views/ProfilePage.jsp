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
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">

    function hide(id) {
        var div_ref = document.getElementById(id);
        div_ref.style.display = "none";
    }

    function show(id) {
        var div_ref = document.getElementById(id);
        div_ref.style.display = "block";
    }
    $(document).ready(function() {
        $("#friendRequestingFromProfile_button").on("click", function () {
            let username = $("#friendRequestingFromProfile_username").val();
            let button = $("#friendRequestingFromProfile_button").val();
            $.ajax({
                type: 'POST',
                data: {
                    username: username,
                    button: button
                },
                url: 'MailServlet',
                success: function (res) {
                    $("#friendRequestingFromProfile_output").html(res);
                    //alert(res);
                }
            });
        })
    });
</script>


<h1 style="text-align:center">User Profile</h1>
<a href="/UserServlet"> Back to homepage</a>
<div class= "Profile">
    <form action="/ProfilePage" method="get">
       <h1>User Name: ${username} </> </h1>
       <h2> Name: ${name}</h2>
       <h2> Surname: ${surname} </h2>
       <h2> Birth Place: ${birthPlace}  </h2>
       <h2> Status: ${status} </h2>

    </form>


</div>

<form action="MailServlet" method="post" id = "friendRequestingFromProfile">
    <input class = "button button7" type="button" name="button" value="sendRequestFromProfile" id = "friendRequestingFromProfile_button">
    <input type="hidden" name="username" value="nana" id = "friendRequestingFromProfile_username">
    <span style="color: red;" id="friendRequestingFromProfile_output"></span>
</form>



</body>
</html>
