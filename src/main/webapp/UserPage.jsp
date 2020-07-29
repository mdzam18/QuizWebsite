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
    <title>Welcome</title>
</head>
<body>
    <script type="text/javascript">
        function hide(id) {
            let div_ref = document.getElementById(id);
            div_ref.style.display = "none";
        }
        function show(id) {
            let div_ref = document.getElementById(id);
            div_ref.style.display = "block";
        }
        function toCreateQuizPage() {
            window.location.href = "createQuiz.jsp";
        }
    </script>

    <%
        UserSqlDao uDao = new UserSqlDao();
        FriendsSqlDao fDao = new FriendsSqlDao();
        QuizSqlDao qDao = new QuizSqlDao();
    %>
    <div class= "SearchBox">
        <form action= "UserServlet" method="POST">
            <input class= "search-txt" type = "text" name = "username"  id = "id" placeholder= "Type To Search" value="${username}" value = "${id}">
            <a class= "search-btn" href = "#"></a>
        </form>
    </div>

    <div class="title">Welcome <%= request.getParameter("username")%></div>

    <div class="Profile">
        <h1> User Name: <%= request.getParameter("username")%> </h1>
        <h2> Name: <%= request.getParameter("name")%></h2>
        <h2> Surname: <%= request.getParameter("surname")%> </h2>
        <h2> Birth Date: <%= request.getParameter("birthDate")%> </h2>
        <h2> Birth Place: <%= request.getParameter("birthPlace")%>  </h2>
        <h2> Status: <%= request.getParameter("status")%> </h2>
    </div>

    <button style="font-size: 25px;" onclick="toCreateQuizPage()">Create New Quiz</button>

    <p><button type = "button" Value="friends" onclick=show("friends_id")>Friends</button></p>
    <div id = "friends_id" style="display: none">
        <%
            String name = request.getParameter("username");
            User user = uDao.getUser(uDao.getUserIdByName(name));
            List<User> list = fDao.getFriends(user);
            for(User user2 : list){
                out.println("<li><a href=\"ProfilePage.jsp?id=" +  user2.getUserId() + "\">" + user2.getUserName() + "</a> </li>" );
            }
        %>
        <input type="button" value="bla" onclick=hide("friends_id")>
    </div>

    <p><button type = "button" Value= "sentRequests" onclick= show("sentRequest_id")>Sent Requests</button></p>
    <div id = "sentRequest_id" style="display: none">
        <%
            name = request.getParameter("username");
            user = uDao.getUser(uDao.getUserIdByName(name));
            list = fDao.getSentRequests(user);
            for(User user2 : list){
                out.println("<li><a href=\"ProfilePage.jsp?id=" +  user2.getUserId() + "\">" + user2.getUserName() + "</a> </li>" );
            }
        %>
        <input type="button" value="bla" onclick=hide("sentRequest_id")>
    </div>

    <p><button type = "button" Value= "request" onclick= show("requests_id")>Friend Requests</button></p>
    <div id = "requests_id" style="display: none">
        <%
            name = request.getParameter("username");
            user = uDao.getUser(uDao.getUserIdByName(name));
            list = fDao.getReceivedRequests(user);
            for(User user2 : list){
                out.println("<li><a href=\"ProfilePage.jsp?id=" +  user2.getUserId() + "\">" + user2.getUserName() + "</a> </li>" );
            }
        %>
        <input type="button" value="bla" onclick=hide("requests_id")>
    </div>

    <p><button value = "delete" onclick= show("delete_id")>delete account</button></p>
    <div id = "delete_id" style="display: none">
        <%--<%
            name = request.getParameter("username");
            user = uDao.getUser(uDao.getUserIdByName(name));
            try{
                uDao.deleteUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>--%>
        <input type="submit" value="bla" onclick=hide("delete_id")>
    </div>

    <form action= "UserServlet" method="POST">
        <p><button>Edit Profile</button></p>
    </form>

    <p> <button value = "popular quizzes" onclick= show("popular_id")>popular quizzes</button></p>
    <div id = "popular_id" style="display: none">
        <%
            /* List<Quiz> popularQuizzes = qDao.getPopularQuizzes();
             for(Quiz quiz: popularQuizzes){
                 out.println("description: "+ quiz.getDescription() + " category: " + quiz.getCategory());
             }
             */
        %>
        <input type="button" value="bla" onclick=hide("popular_id")>
    </div>

    <p> <button value = "recently quizzes" onclick= show("recently_id")>recently created quizzes</button></p>
    <div id = "recently quizzes" style="display: none">
        <%
            /*
            List<Quiz> recentlyCreated = qDao.getRecentlyCreatedQuizzes();
            for(Quiz quiz: recentlyCreated){
                out.println("description: "+ quiz.getDescription() + " category: " + quiz.getCategory());
            }
            */
        %>
        <input type="button" value="bla" onclick=hide("recently_id")>
    </div>

    <p> <button value = "recently taken quizzes" onclick= show("recentlyTaken_id")>recently taken quizzes</button></p>
    <div id = "recently taken quizzes" style="display: none">
        <%
            /*
              name = request.getParameter("username");
              user = uDao.getUser(uDao.getUserIdByName(name));
              List <Quiz> recentlyTaken = uDao.getRecentlyTakenQuizzes(user);
              for(Quiz quiz: recentlyTaken){
                  out.println("description: "+ quiz.getDescription() + " category: " + quiz.getCategory());
              }
              */
        %>
        <input type="button" value="bla" onclick=hide("recentlyTaken_id")>
    </div>

    <p> <button value = "recently created quizzes" onclick= show("recentlyCreated_id")>recently created quizzes</button></p>
    <div id = "recently taken quizzes" style="display: none">
        <%
        //                name = request.getParameter("username");
        //                List <Quiz> recentlyCreated = null;
        //                try {
        //                    recentlyCreated = qDao.getQuizzesForUser(uDao.getUserIdByName(name));
        //                } catch (SQLException throwables) {
        //                    throwables.printStackTrace();
        //                }
        //                List<Quiz> sortedRecentlyCreated = qDao.sortQuizzesByDate(recentlyCreated);
        //                int i = 0;
        //                for(Quiz quiz: sortedRecentlyCreated){
        //                    out.println("description: "+ quiz.getDescription() + " category: " + quiz.getCategory());
        //                    i++;
        //                    if (i >= 5) break;
        //                }
        %>
        <input type="button" value="bla" onclick=hide("recentlyCreated_id")>
    </div>
</body>
</html>