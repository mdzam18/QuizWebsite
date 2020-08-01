<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="FriendsPackage.FriendsSqlDao" %>
<%@ page import="UserPackage.UserSqlDao" %>
<%@ page import="UserPackage.User" %>
<%@ page import="Quiz.Quiz" %>
<%@ page import="Quiz.QuizSqlDao" %>
<%@ page import="HistoryPackage.HistorySqlDao" %>
<%@ page import= "java.sql.SQLException" %>
<%@ page import="MailPackage.Mail" %>
<%@ page import="MailPackage.MailSqlDao" %>
<%@ page import="HistoryPackage.History" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>

<html>
<head>
    <link href="../style.css" rel="stylesheet" type="text/css">

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
    MailSqlDao mailDao = new MailSqlDao();
    UserSqlDao userDao = new UserSqlDao();
    //out.print(request.getParameter("username"));
    ArrayList<Mail> mails = mailDao.getMails(3);
%>
<div class= "SearchBox">
    <form action= "UserServlet" method="POST">
        <input class= "search-txt" type = "text" name = "username"  id = "id" placeholder= "Type To Search" value="${username}" value = "${id}">
        <input class = "button button4" type="submit" name="button" value="search">
        <a class= "search-btn" href = "#"></a>
    </form>
</div>

<div class="Profile">
    <h1> User Name: <%= (String)session.getAttribute("currentUser")%> </h1>
    <h2> Name: <%= uDao.getUser(id).getName() %></h2>
    <h2> Surname: <%= uDao.getUser(id).getSurname() %> </h2>
    <h2> Birth Place: <%= uDao.getUser(id).getBirthPlace() %>  </h2>
    <h2> Status: <%= uDao.getUser(id).getStatus()%> </h2>
</div>

<button class = "button" style="font-size: 20px;" onclick="toCreateQuizPage()">Create New Quiz</button>

<p><button class = "button button4" type = "button" Value="friends" onclick=show("friends_id")>Friends</button></p>
<div id = "friends_id" style="display: none">
    <%
        String name = (String)session.getAttribute("currentUser");
        User user = uDao.getUser(uDao.getUserIdByName(name));
        List<User> list = fDao.getFriends(user);
        for(User user2 : list){
            out.println("<li><a href=\"ProfilePage.jsp?id=" +  user2.getUserId() + "\">" + user2.getUserName() + "</a> </li>" );
        }
    %>
    <input class="button button6" type="button" value="close" onclick=hide("friends_id")>
</div>

<p><button class = "button button4" type = "button" Value= "sentRequests" onclick= show("sentRequest_id")>Sent Requests</button></p>
<div id = "sentRequest_id" style="display: none">
    <%
        name = (String)session.getAttribute("currentUser");
        user = uDao.getUser(uDao.getUserIdByName(name));
        list = fDao.getSentRequests(user);
        for(User user2 : list){
            out.println("<li><a href=\"ProfilePage.jsp?id=" +  user2.getUserId() + "\">" + user2.getUserName() + "</a> </li>" );
        }
    %>
    <input class="button button6" type="button" value="close" onclick=hide("sentRequest_id")>
    <input type="hidden" name="username" VALUE= <%=(String)session.getAttribute("currentUser")%>>
</div>

<p><button class = "button button4" type = "button" Value= "request" onclick= show("requests_id")>Friend Requests</button></p>
<div id = "requests_id" style="display: none">
    <%
        name = (String)session.getAttribute("currentUser");
        user = uDao.getUser(uDao.getUserIdByName(name));
        list = fDao.getReceivedRequests(user);
        for(User user2 : list){
            out.println("<li><a href=\"ProfilePage.jsp?id=" +  user2.getUserId() + "\">" + user2.getUserName() + "</a> </li>" );
            String s = "<form action=\"MailServlet\" method=\"post\">\n" +
                    "        <input type=\"hidden\" name=\"username\" value=" + user2.getUserId() +
                    ">\n" +
                    "        <input class=\"button button7\" type=\"submit\" name=\"button\" value=\"confirmRequest\">\n" +
                    "    </form>";
            out.println(s);
        }
    %>
    <input class="button button6" type="button" value="close" onclick=hide("requests_id")>
    <input type="hidden" name="username" VALUE= <%=(String)session.getAttribute("currentUser")%>>
</div>

<form action= UserServlet method="POST">
    <input class = "button button3" type="submit" name="button" value="delete">
    <input type="hidden" name="username" VALUE= <%=(String)session.getAttribute("currentUser")%>>
</form>

<form action= UserServlet method="POST">
    <input class = "button button3" type="submit" name="button" value="edit profile">
    <input type="hidden" name="username" VALUE= <%=(String)session.getAttribute("currentUser")%>>
</form>

<style>
    .admin{
        position: absolute;
        top: 8px;
        right: 16px;
        font-size: 24px;
        color: #008CBA;
        font-weight: bold;
    }

    a:link {
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }
</style>

<div class="admin">
    <c:if test="${isAdmin != null}">
        <a href="/admin">Go as Administrator</a>
    </c:if>
</div>

<p> <button class = "button" value = "popular quizzes" onclick= show("popular_id")>Popular quizzes</button></p>
<div id = "popular_id" style="display: none">
    <ul>
    <%
        List<Quiz> popularQuizzes = qDao.getPopularQuizzes();
        List<Quiz> sorted = qDao.sortByQuizIdDescending(popularQuizzes);
        for(Quiz quiz: sorted) {
            out.println("<li><a href=\"/quizInfo.jsp?id=" +  quiz.getQuizId() + "\">" + quiz.getDescription() + "</a> </li>");
        }
    %>
    </ul>
    <input class="button button6" type="button" value="Hide" onclick=hide("popular_id")>
</div>

<p> <button class = "button" value = "recently quizzes" onclick= show("recently_id")>Recently created quizzes</button></p>
<div id = "recently_id" style="display: none">
    <ul>
    <%
        List<Quiz> recentQuizzes = qDao.getRecentlyCreatedQuizzes();
        sorted = qDao.sortByQuizIdDescending(recentQuizzes);
        for(Quiz quiz: sorted){
            System.out.println(quiz.getQuizId() + " " + quiz.getDescription());
            out.println("<li><a href=\"/quizInfo.jsp?id=" +  quiz.getQuizId() + "\">" + quiz.getDescription() + "</a> </li>");
        }
    %>
    </ul>
    <input class="button button6" type="button" value="Hide" onclick=hide("recently_id")>
</div>

<p> <button class = "button" value = "recently taken quizzes" onclick= show("recentlyTaken_id")>Recently taken quizzes</button></p>
<div id = "recentlyTaken_id" style="display: none">
    <ul>
    <%
        List<History> recentlyTakenQuizzes = historyDao.getHistories(id);
        List<History> sort = historyDao.sortByEndDate(recentlyTakenQuizzes);
        for(History history : sort){
            out.println("<li><a href=\"/quizInfo.jsp?id=" +  history.getQuizId() + "\">" + qDao.getQuiz(history.getQuizId()).getDescription() + "</a> </li>");
        }
    %>
    </ul>
    <input class="button button6" type="button" value="Hide" onclick=hide("recentlyTaken_id")>
</div>

<p> <button class = "button" value = "your recently created quizzes" onclick= show("your_recently_id")>Your recently created quizzes</button></p>
<div id = "your_recently_id" style="display: none">
    <ul>
    <%
        recentQuizzes = qDao.getRecentlyCreatedQuizzes();
        sorted = qDao.sortByQuizIdDescending(recentQuizzes);
        for(Quiz quiz: sorted){
            System.out.println(quiz.getQuizId() + " " + quiz.getDescription());
            out.println("<li><a href=\"/quizInfo.jsp?id=" +  quiz.getQuizId() + "\">" + quiz.getDescription() + "</a> </li>");
        }
    %>
    </ul>
    <input class="button button6" type="button" value="Hide" onclick=hide("your_recently_id")>
</div>

<p> <button class = "button" value = "friends activity" onclick= show("friends_activity_id")>Your friends' recent activity</button></p>
<div id = "friends_activity_id" style="display: none">
    <ul>
    <%
        user = uDao.getUser(uDao.getUserIdByName(name));
        list = fDao.getFriends(user);
        List<History> friendHistories = new ArrayList<History>();
        for (User u : list){
            List<History> h = historyDao.getHistories(u.getUserId());
            friendHistories.addAll(h);
        }
        sort = HistorySqlDao.sortByEndDate(friendHistories);
        for(History history : sort){
            out.println("<li><a href=\"/quizInfo.jsp?id=" +  history.getQuizId() + "\">" + qDao.getQuiz(history.getQuizId()).getDescription() + "</a> </li>");
        }
        %>
        </ul>
    <input class="button button6" type="button" value="Hide" onclick=hide("friends_activity_id")>
</div>


<input class="button button1" type=button Value=challenge onclick=show("challenge_div")>
<div id=challenge_div style="display: none">
    <form action="MailServlet" method="post">
        to: <input type="text" name="username"> <br/>
        <label for="quizzes">
            choose a quiz:
        </label>
        <select name="quizzes" id="quizzes">
            <%
                /*
                int id2 = uDao.getUserIdByName((String)session.getAttribute("currentUser"));
                List<String> list2 = historyDao.forChallenge(id2);
                for (String s : list2){
                    String output = "<option value='" + s + "'>" + s + "</option>";
                    out.print(output);
                }
                */
            %>
        </select>
        <input class="button button7" type="submit" name="button" value="challenge">
        <input class="button button6" type="button" value="cancel" onclick=hide("challenge_div")>
    </form>
</div>

<input class="button button1" type=button Value=request onclick=show("request_div")>
<div id=request_div style="display: none">
    <form action="MailServlet" method="post">
        to: <input type="text" name="username"> <br/>
        <input class="button button7" type="submit" name="button" value="sendRequest">
    </form>
    <input class="button button6" type="button" value="hide" onclick=hide("request_div")>
</div>

<input class="button button1" type=button Value=compose onclick=show("id_div")>
<div id=id_div style="display: none">
    <form action="MailServlet" method="post">
        to: <input type="text" name="username"> <br/>
        message: <input type="text" name="message"> <br/>
        <input class="button button7" type="submit" name="button" value="send">
        <input class="button button6" type="button" value="cancel" onclick=hide("id_div")>
    </form>
</div>


<input class="button button1" type=button Value=inbox onclick=show("inbox_div")>
<div id=inbox_div style="display: none">
    <form action="MailServlet" method="post">
        <%
            for (Mail mail : mails) {
                String senderName = userDao.getUser(mail.getSenderId()).getUserName();
                String output = "<li><a href=\"readMessage.jsp?id=" + mail.getMailId() + "&sender=" + senderName + "\">" + "from: " + senderName + "</a></li>";
                out.print(output);
            }
        %>
        <input class="button button6"  type="button" value="cancel" onclick=hide("inbox_div")>
    </form>
</div>
</body>
</html>
