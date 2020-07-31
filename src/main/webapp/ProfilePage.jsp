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
    <%
        UserSqlDao uDao = new UserSqlDao();
        FriendsSqlDao fDao = new FriendsSqlDao();
        String name = uDao.getUser(Integer.parseInt(request.getParameter("id"))).getUserName();
        int id = Integer.parseInt(request.getParameter("id"));
    %>

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
<div> class "Profile">
        <h1 <label for="username">User Name: <%= uDao.getUser(id).getUserName()%> </label> </h1>
       <h2> Name: <%= uDao.getUser(id).getName() %></h2>
       <h2> Surname: <%= uDao.getUser(id).getSurname() %> </h2>
       <h2> Birth Place: <%= uDao.getUser(id).getBirthPlace() %>  </h2>
       <h2> Status: <%= uDao.getUser(id).getStatus()%> </h2>
</div>

<form action="MailServlet" method="post">
    <input class = "button button7" type="submit" name="button" value="sendRequestFromProfile">
    <input type="hidden" name="username" value=<%=name%>>
</form>

</body>
</html>
