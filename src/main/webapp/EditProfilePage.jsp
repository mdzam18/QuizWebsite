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
    <head>
        <link href="../style.css" rel="stylesheet" type="text/css">
        <title>Profile</title>
    </head>

    <%
        UserSqlDao uDao = new UserSqlDao();
        int id = uDao.getUserIdByName(request.getParameter("username"));
     %>

    <body>
      <div class= "edit" ><h1> Edit profile </h1> </div>
      <div class="Input">
        <form action="UserServlet" method="post">
            <label for="name">Name</label>
            <input type="text" name="name" value= <%= uDao.getUser(id).getName() %> ><br/>
            <label for="surname">Surname</label>
            <input type="text" name="surname" value= <%= uDao.getUser(id).getSurname() %>><br/>
            <label for="birthPlace">Birth Place</label>
            <input type="text" name="birthPlace" value= <%= uDao.getUser(id).getBirthPlace() %>><br/>
            <label for="status">Status</label>
            <input type="text" name="status" value= <%= uDao.getUser(id).getStatus() %>><br/>
            <div class="editButton">
                <input type="submit" name="button" value="edit">
                <input type="hidden" name="username" VALUE= <%=request.getParameter("username")%>>
            </div>
        </form>
      </div>
    </body>
</html>
