<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="FriendsPackage.FriendsSqlDao" %>
<%@ page import="UserPackage.UserSqlDao" %>
<%@ page import="UserPackage.User" %>
<%@ page import="Quiz.Quiz" %>
<%@ page import="Quiz.QuizSqlDao" %>
<%@ page import= "java.sql.SQLException" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>


<html>
<link href="../style.css" rel="stylesheet" type="text/css">
<head>
    <title>Profile</title>
</head>

<%
    UserSqlDao uDao = new UserSqlDao();
    int id = uDao.getUserIdByName(request.getParameter("username"));
%>

<body>
<form action="UserServlet" method="post">
    <label  for="name">Name</label>
    <input type="text" id = "name" name="name" value= <% uDao.getUser(id).getName(); %> /><br/>
    <label for="surname">Surname</label>
    <input type="text" id = "surname" name="surname" value= <% uDao.getUser(id).getSurname(); %>/><br/>
    <label for="birthDate">Birth Date</label>
    <input id = "birthDate" type="date" name="birthDate" value= <% uDao.getUser(id).getBirthDate(); %>/><br/>
    <label for="birthPlace">Birth Place</label>
    <input id = "birthPlace" type="text" name="birthPlace" value= <% uDao.getUser(id).getBirthPlace(); %>/><br/>
    <label for="status">Status</label>
    <input id = "status" type="text" name="status" value= <% uDao.getUser(id).getStatus(); %>/><br/>
    <input class="button button7" type="submit" name="button" value="edit">
    <input type="hidden" name="username" VALUE= <%=request.getParameter("username")%>>
</form>
</body>
</html>
