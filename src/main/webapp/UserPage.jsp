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
        int id = uDao.getUserIdByName(request.getParameter("username"));
    %>
    <div class= "SearchBox">
        <form action= "UserServlet" method="POST">
            <input class= "search-txt" type = "text" name = "username"  id = "id" placeholder= "Type To Search" value="${username}" value = "${id}">
            <input type="submit" name="button" value="search">
            <a class= "search-btn" href = "#"></a>
        </form>
    </div>

    <div class="title">Welcome <%= request.getParameter("username")%></div>

    <div class="Profile">
        <h1> User Name: <%= request.getParameter("username")%> </h1>
        <h2> Name: <% uDao.getUser(id).getName(); %></h2>
        <h2> Surname: <% uDao.getUser(id).getSurname(); %> </h2>
        <h2> Birth Date: <% uDao.getUser(id).getBirthDate(); %> </h2>
        <h2> Birth Place: <% uDao.getUser(id).getBirthPlace(); %>  </h2>
        <h2> Status: <% uDao.getUser(id).getStatus();%> </h2>
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
        <input type="button" value="close" onclick=hide("friends_id")>
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
        <input type="button" value="close" onclick=hide("sentRequest_id")>
        <input type="hidden" name="username" VALUE= <%=request.getParameter("username")%>>
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
        <input type="button" value="close" onclick=hide("requests_id")>
        <input type="hidden" name="username" VALUE= <%=request.getParameter("username")%>>
    </div>

    <form action= UserServlet method="POST">
     <input type="submit" name="button" value="delete">
     <input type="hidden" name="username" VALUE= <%=request.getParameter("username")%>>
    </form>

    <form action= UserServlet method="POST">
         <input type="submit" name="button" value="edit profile">
         <input type="hidden" name="username" VALUE= <%=request.getParameter("username")%>>
    </form>

    <p> <button value = "Popular quizzes" onclick= show("popular_id")>Popular quizzes</button></p>
    <div id = "popular_id" style="display: none">
<<<<<<< HEAD
<%--        <%--%>
<%--            List<Quiz> popularQuizzes = qDao.getPopularQuizzes();--%>
<%--             for(Quiz quiz: popularQuizzes){ %>--%>
<%--                 <% String href = "/quizInfo.jsp?id=" + quiz.getQuizId(); %>--%>
<%--                <li><a href="<%= href %>"><%= quiz.getDescription() %></a></li>--%>
<%--            <% } %>--%>

        <input type="button" value="bla" onclick=hide("popular_id")>
=======
        <%
            /* List<Quiz> popularQuizzes = qDao.getPopularQuizzes();
             for(Quiz quiz: popularQuizzes){
                 out.println("description: "+ quiz.getDescription() + " category: " + quiz.getCategory());
             }
             */
        %>
        <input type="button" value="close" onclick=hide("popular_id")>
>>>>>>> 9bd0b7ea6a2d588adf4cb4822470cbdc32092290
    </div>

    <p> <button value = "recently quizzes" onclick= show("recently_id")>recently created quizzes</button></p>
    <div id = "recently quizzes" style="display: none">
<<<<<<< HEAD
<%--        <%--%>
<%--            List<Quiz> recentQuizzes = qDao.getRecentlyCreatedQuizzes();--%>
<%--            for(Quiz quiz: recentQuizzes){ %>--%>
<%--        <% String href = "/quizInfo.jsp?id=" + quiz.getQuizId(); %>--%>
<%--        <li><a href="<%= href %>"><%= quiz.getDescription() %></a></li>--%>
<%--        <% } %>--%>
        <input type="button" value="bla" onclick=hide("recently_id")>
=======
        <%
            /*
            List<Quiz> recentlyCreated = qDao.getRecentlyCreatedQuizzes();
            for(Quiz quiz: recentlyCreated){
                out.println("description: "+ quiz.getDescription() + " category: " + quiz.getCategory());
            }
            */
        %>
        <input type="button" value="close" onclick=hide("recently_id")>
>>>>>>> 9bd0b7ea6a2d588adf4cb4822470cbdc32092290
    </div>

    <p> <button value = "recently taken quizzes" onclick= show("recentlyTaken_id")>recently taken quizzes</button></p>
    <div id = "recently taken quizzes" style="display: none">
        <%
<<<<<<< HEAD
=======
            /*
              name = request.getParameter("username");
              user = uDao.getUser(uDao.getUserIdByName(name));
              List <Quiz> recentlyTaken = uDao.getRecentlyTakenQuizzes(user);
              for(Quiz quiz: recentlyTaken){
                  out.println("description: "+ quiz.getDescription() + " category: " + quiz.getCategory());
              }
              */
        %>
        <input type="button" value="close" onclick=hide("recentlyTaken_id")>
    </div>
>>>>>>> 9bd0b7ea6a2d588adf4cb4822470cbdc32092290

//              name = request.getParameter("username");
//              user = uDao.getUser(uDao.getUserIdByName(name));
//              List <Quiz> recentlyTaken = uDao.getRecentlyTakenQuizzes(user);
//              for(Quiz quiz: recentlyTaken){
//                  out.println("description: "+ quiz.getDescription() + " category: " + quiz.getCategory());
//              }
        %>
<<<<<<< HEAD
        <input type="button" value="bla" onclick=hide("recentlyTaken_id")>
=======
        <input type="button" value="close" onclick=hide("recentlyCreated_id")>
>>>>>>> 9bd0b7ea6a2d588adf4cb4822470cbdc32092290
    </div>
</body>
</html>
