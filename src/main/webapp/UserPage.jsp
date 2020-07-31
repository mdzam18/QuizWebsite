<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="FriendsPackage.FriendsSqlDao" %>
<%@ page import="UserPackage.UserSqlDao" %>
<%@ page import="UserPackage.User" %>
<%@ page import="Quiz.Quiz" %>
<%@ page import="Quiz.QuizSqlDao" %>
<%@ page import="HistoryPackage.HistorySqlDao" %>
<%@ page import= "java.sql.SQLException" %>
<%@ page import="HistoryPackage.History" %>
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
        HistorySqlDao historyDao = new HistorySqlDao();
        int id = uDao.getUserIdByName((String)session.getAttribute("currentUser"));
        System.out.println(uDao.getUser(id).getName());
    %>
    <div class= "SearchBox">
        <form action= "UserServlet" method="POST">
            <input class= "search-txt" type = "text" name = "username"  id = "id" placeholder= "Type To Search" value="${username}" value = "${id}">
            <input type="submit" name="button" value="search">
            <a class= "search-btn" href = "#"></a>
        </form>
    </div>

    <div class="title">Welcome <%= (String)session.getAttribute("currentUser")%></div>

    <div class="Profile">
        <h1> User Name: <%= (String)session.getAttribute("currentUser")%> </h1>
        <h2> Name: <%= uDao.getUser(id).getName() %></h2>
        <h2> Surname: <%= uDao.getUser(id).getSurname() %> </h2>
        <h2> Birth Date: <%= uDao.getUser(id).getBirthDate() %> </h2>
        <h2> Birth Place: <%= uDao.getUser(id).getBirthPlace() %>  </h2>
        <h2> Status: <%= uDao.getUser(id).getStatus()%> </h2>
    </div>

    <button style="font-size: 25px;" onclick="toCreateQuizPage()">Create New Quiz</button>

    <p><button type = "button" Value="friends" onclick=show("friends_id")>Friends</button></p>
    <div id = "friends_id" style="display: none">
        <%
            String name = (String)session.getAttribute("currentUser");
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
            name = (String)session.getAttribute("currentUser");
            user = uDao.getUser(uDao.getUserIdByName(name));
            list = fDao.getSentRequests(user);
            for(User user2 : list){
                out.println("<li><a href=\"ProfilePage.jsp?id=" +  user2.getUserId() + "\">" + user2.getUserName() + "</a> </li>" );
            }
        %>
        <input type="button" value="close" onclick=hide("sentRequest_id")>
        <input type="hidden" name="username" VALUE= <%=(String)session.getAttribute("currentUser")%>>
    </div>

    <p><button type = "button" Value= "request" onclick= show("requests_id")>Friend Requests</button></p>
    <div id = "requests_id" style="display: none">
        <%
            name = (String)session.getAttribute("currentUser");
            user = uDao.getUser(uDao.getUserIdByName(name));
            list = fDao.getReceivedRequests(user);
            for(User user2 : list){
                out.println("<li><a href=\"ProfilePage.jsp?id=" +  user2.getUserId() + "\">" + user2.getUserName() + "</a> </li>" );
            }
        %>
        <input type="button" value="close" onclick=hide("requests_id")>
        <input type="hidden" name="username" VALUE= <%=(String)session.getAttribute("currentUser")%>>
    </div>

    <form action= UserServlet method="POST">
        <input type="submit" name="button" value="delete">
        <input type="hidden" name="username" VALUE= <%=(String)session.getAttribute("currentUser")%>>
    </form>

    <form action= UserServlet method="POST">
        <input type="submit" name="button" value="edit profile">
        <input type="hidden" name="username" VALUE= <%=(String)session.getAttribute("currentUser")%>>
    </form>

    <p> <button value = "popular quizzes" onclick= show("popular_id")>Popular quizzes</button></p>
    <div id = "popular_id" style="display: none">
        <%
            List<Quiz> popularQuizzes = qDao.getPopularQuizzes();
            for(Quiz quiz: popularQuizzes){ %>
        <% String href = "/quizInfo.jsp?id=" + quiz.getQuizId(); %>
        <li><a href="<%= href %>"><%= quiz.getDescription() %></a></li>
        <% } %>
        %>
        <input type="button" value="Hide" onclick=hide("popular_id")>
    </div>

    <p> <button value = "recently quizzes" onclick= show("recently_id")>Recently created quizzes</button></p>
    <div id = "recently quizzes" style="display: none">
                <%
                    List<Quiz> recentQuizzes = qDao.getRecentlyCreatedQuizzes();
                    for(Quiz quiz: recentQuizzes){ %>
                <% String href = "/quizInfo.jsp?id=" + quiz.getQuizId(); %>
                <li><a href="<%= href %>"><%= quiz.getDescription() %></a></li>
                <% } %>
        <input type="button" value="Hide" onclick=hide("recently_id")>
    </div>

    <p> <button value = "recently taken quizzes" onclick= show("recentlyTaken_id")>Recently taken quizzes</button></p>
    <div id = "recently taken quizzes" style="display: none">
        <%
            List<History> recentlyTakenQuizzes = historyDao.getHistories(id);
            for(History history : recentlyTakenQuizzes ){
            %>
        <% String href = "/quizInfo.jsp?id=" + history.getQuizId(); %>
        <li><a href="<%= href %>"><%= qDao.getQuiz(history.getQuizId()).getDescription() %></a></li>
        <% } %>
        <input type="button" value="Hide" onclick=hide("recentlyTaken_id")>
    </div>

    <p> <button value = "your recently created quizzes" onclick= show("your_recently_id")>Your recently created quizzes</button></p>
    <div id = "your recently created quizzes" style="display: none">
        <%
            List<Quiz> recentQuizzesByUser = qDao.getRecentlyCreatedQuizzesByUser(id);
            for(Quiz quiz: recentQuizzes){ %>
        <% String href = "/quizInfo.jsp?id=" + quiz.getQuizId(); %>
        <li><a href="<%= href %>"><%= quiz.getDescription() %></a></li>
        <% } %>
        <input type="button" value="Hide" onclick=hide("your_recently_id")>
    </div>

    <p> <button value = "friends activity" onclick= show("friends_activity_id")>Your friends' recent activity</button></p>
    <div id = "friends activity" style="display: none">
        <%
            user = uDao.getUser(uDao.getUserIdByName(name));
            list = fDao.getFriends(user);
            List<History> friendHistories = new ArrayList<History>();
            for (User u : list){
                List<History> h = historyDao.getHistories(u.getUserId());
                friendHistories.addAll(h);
            }
            for(History h : friendHistories){ %>
        <% String href = "/quizInfo.jsp?id=" + h.getQuizId(); %>
        <li><a href="<%= href %>"><%= qDao.getQuiz(h.getQuizId()).getDescription() %></a></li>
        <% } %>
        <input type="button" value="Hide" onclick=hide("friends_activity_id")>
    </div>

    <input type=button Value=challenge onclick=show("challenge_div")>
    <div id=challenge_div style="display: none">
        <form action="MailServlet" method="post">
            to: <input type="text" name="username"> <br/>
            <label for="quizzes">
                choose a quiz:
            </label>
            <select name="quizzes" id="quizzes">
                <%
                    int id2 = uDao.getUserIdByName((String)session.getAttribute("currentUser"));
                    List<String> list2 = historyDao.forChallenge(id2);
                    for (String s : list2){
                        String output = "<option value='" + s + "'>" + s + "</option>";
                        out.print(output);
                    }
                %>
            </select>
            <input type="submit" name="button" value="challenge">
            <input type="button" value="cancel" onclick=hide("challenge_div")>
        </form>
    </div>
</body>
</html>