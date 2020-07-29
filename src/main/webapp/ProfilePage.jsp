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

        <%
            UserSqlDao uDao = new UserSqlDao();
            FriendsSqlDao fDao = new FriendsSqlDao();
        %>
        <h1 style="text-align:center">User Profile</h1>
        <div class "Profile">
                   <h1 <label for="username">User Name: <%= uDao.getUser(Integer.parseInt(request.getParameter("id"))).getUserName()%> </label> </h1>
         </div>
  </body>
 </html>
