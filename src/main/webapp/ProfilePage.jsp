<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="ProfilePackage.FriendsSqlDao" %>
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
                   <h2 <label for="name">Name: <%= request.getParameter("name")%> </label> </h2>
                   <h2 <label for="surname">Surname: <%= request.getParameter("surname")%> </label> </h2>
                   <h2 <label for="birthDate">Birth Date: <%= request.getParameter("birthDate")%> </label> </h2>
                   <h2 <label for="birthPlace">Birth Place: <%= request.getParameter("birthPlace")%> </label> </h2>
                   <h2 <label for="status">Status: <%= request.getParameter("status")%> </label> </h2>
         </div>


        <p><button type = "button" Value="friends" onclick=show("friends_id2")>Friends</button></p>
        <div id = "friends_id2" style="display: none">
                    <%
                  /*    String name = request.getParameter("username");
                      User user = uDao.getUser(uDao.getUserIdByName(name));
                      List<User> list = fDao.getFriends(user);
                      for(User user2 : list){
                        out.println("<li><a href=\"ProfilePage.jsp?id=" +  user2.getUserId() + "\">" + user2.getUserName() + "</a> </li>" );
                      }
                      */
                    %>
                   <input type="button" value="bla" onclick=hide("friends_id2")>
        </div>
  </body>
 </html>
