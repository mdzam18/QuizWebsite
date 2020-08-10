<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="FriendsPackage.FriendsSqlDao" %>
<%@ page import="UserPackage.UserSqlDao" %>
<%@ page import="UserPackage.User" %>
<%@ page import="Quiz.Quiz" %>
<%@ page import="Quiz.QuizSqlDao" %>
<%@ page import="HistoryPackage.HistorySqlDao" %>
<%@ page import="AchievementsPackage.AchievementsSqlDao" %>
<%@ page import="AchievementsPackage.AchievementsDao" %>
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

    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <!-- JQuery -->
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body style="font-family: 'Roboto', sans-serif;">
<script type="text/javascript">
    function hide(id) {
        let div_ref = document.getElementById(id);
        div_ref.style.display = "none";
        if(id == "request_div") {
            $("#FriendRequesting_output").html("");
        }
    }
    function show(id) {
        let div_ref = document.getElementById(id);
        div_ref.style.display = "block";
    }
    function toCreateQuizPage() {
        window.location.href = "createQuiz.jsp";
    }
    $(document).ready(function() {
        $("#FriendRequesting_button").on("click", function () {
            let username = $("#FriendRequesting_username").val();
            let button = $("#FriendRequesting_button").val();
            $.ajax({
                type: 'POST',
                data: {
                    username: username,
                    button: button
                },
                url: 'MailServlet',
                success: function (res) {
                    const success1 = "friend request";
                    const success2 = "successfully";
                    let output = $("#FriendRequesting_output");
                    if(res.includes(success1) && res.includes(success2)) {
                        output.css("color", "green");
                    } else {
                        output.css("color", "red");
                    }
                    output.html(res);
                    //alert(res);
                }
            });
        })
        $("#Composing_button").on("click", function () {
            let username = $("#Composing_username").val();
            let message = $("#Composing_message").val();
            let button = $("#Composing_button").val();
            $.ajax({
                type: 'POST',
                data: {
                    username: username,
                    message : message,
                    button: button
                },
                url: 'MailServlet',
                success: function (res) {
                    $("#Composing_output").html(res);
                    //alert(res);
                }
            });
        })
        $("#Challenging_button").on("click", function () {
            let username = $("#Challenging_username").val();
            let quizzes = $("#Challenging_quizzes").val();
            let button = $("#Challenging_button").val();
            $.ajax({
                type: 'POST',
                data: {
                    username: username,
                    quizzes : quizzes,
                    button: button
                },
                url: 'MailServlet',
                success: function (res) {
                    $("#Challenging_output").html(res);
                    //alert(res);
                }
            });
        })
    });
</script>

<%
    UserSqlDao uDao = new UserSqlDao();
    FriendsSqlDao fDao = new FriendsSqlDao();
    QuizSqlDao qDao = new QuizSqlDao();
    HistorySqlDao historyDao = new HistorySqlDao();
    AchievementsSqlDao aDao = new AchievementsSqlDao();
    int id = uDao.getUserIdByName((String)session.getAttribute("currentUser"));
    System.out.println(uDao.getUser(id).getName());
    MailSqlDao mailDao = new MailSqlDao();
    UserSqlDao userDao = new UserSqlDao();
    //out.print(request.getParameter("username"));
    ArrayList<Mail> mails = mailDao.getMails(id);
%>
<div class= "SearchBox">
    <form action= "UserServlet" method="POST">
        <input class= "search-txt" type = "text" name = "username"  id = "id" placeholder= "Type To Search" value="${username}" value = "${id}">
        <input class = "button button4" type="submit" name="button" value="search">
        <a class= "search-btn" href = "#"></a>
        <c:if test="${error != null}">
            <p style="color:red;"> ${error} </p>
        </c:if>
    </form>
</div>

<div class="Profile">
    <h2> User Name: <%= (String)session.getAttribute("currentUser")%> </h2>
    <h2> Name: ${name}</h2>
    <h2> Surname: ${surname} </h2>
    <h2> Birth Place: ${birthPlace}  </h2>
    <h2> Status: ${status} </h2>
</div>

<button class = "button" style="font-size: 20px;" onclick="toCreateQuizPage()">Create New Quiz</button>

<form action= UserServlet method="POST">
    <input class = "button button4" type="submit" name="button" value="friends">
    <input type="hidden" name="username" VALUE= <%=(String)session.getAttribute("currentUser")%>>
</form>

<form action= UserServlet method="POST">
    <input class = "button button4" type="submit" name="button" value="sent requests">
    <input type="hidden" name="username" VALUE= <%=(String)session.getAttribute("currentUser")%>>
</form>

<form action= UserServlet method="POST">
    <input class = "button button4" type="submit" name="button" value="friend requests">
    <input type="hidden" name="username" VALUE= <%=(String)session.getAttribute("currentUser")%>>
</form>

<form action= UserServlet method="POST">
    <input class = "button button3" type="submit" name="button" value="delete">
    <input type="hidden" name="username" VALUE= <%=(String)session.getAttribute("currentUser")%>>
</form>

<form action= UserServlet method="POST">
    <input class = "button button3" type="submit" name="button" value="edit profile">
    <input type="hidden" name="username" VALUE= <%=(String)session.getAttribute("currentUser")%>>
</form>

<style>
    .admin a{
        position: absolute;
        top: 8px;
        right: 16px;
        font-size: 24px;
        color: purple;
        font-weight: bold;
    }
    .admin a:link {
        text-decoration: none;
    }
    .admin a:hover {
        text-decoration: underline;
        color: red;
    }
    .logOutButton {
        margin-top: 20px;
        padding: 8px 14px;
        font-size: 20px;
        border-radius: 6px;
    }
</style>

<div class="admin">
<%--    <c:if test="${isAdmin != null}">--%>
<%--        <a href="/admin">Go as Administrator</a>--%>
<%--    </c:if>--%>
    <%
        String name = (String)session.getAttribute("currentUser");
        User user = uDao.getUser(uDao.getUserIdByName(name));
        if(user.isAdministrator()) {
            out.print("<a href=\"/admin\">Go as Administrator</a>");
        }
    %>
</div>

<p> <button class = "button" value = "popular quizzes" onclick= show("popular_id")>Popular quizzes</button></p>
<div id = "popular_id" style="display: none">
    <ul>
        <%
            List<Quiz> popularQuizzes = historyDao.getPopularQuizzes();
            for(Quiz quiz: popularQuizzes) {
                out.println("<li><a href=\"/quizInfo.jsp?id=" +  quiz.getQuizId() + "\">" + quiz.getDescription() + " (Author: " + uDao.getUser(quiz.getCreatorId()).getUserName() + ")" + "</a> </li>");
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
            List<Quiz> sorted = qDao.sortByQuizIdDescending(recentQuizzes);
            for(Quiz quiz: sorted){
                out.println("<li><a href=\"/quizInfo.jsp?id=" +  quiz.getQuizId() + "\">" + quiz.getDescription() + " (Author: " + uDao.getUser(quiz.getCreatorId()).getUserName() + ")" + "</a> </li>");
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
                out.println("<li><a href=\"/quizInfo.jsp?id=" +  history.getQuizId() + "\">" + qDao.getQuiz(history.getQuizId()).getDescription() + " (Author: " + uDao.getUser(history.getUserId()).getUserName() + ")" + "</a> </li>");
            }
        %>
    </ul>
    <input class="button button6" type="button" value="Hide" onclick=hide("recentlyTaken_id")>
</div>

<p> <button class = "button" value = "your recently created quizzes" onclick= show("your_recently_id")>Your recently created quizzes</button></p>
<div id = "your_recently_id" style="display: none">
    <ul>
        <%
            recentQuizzes = qDao.getRecentlyCreatedQuizzesByUser(id);
            sorted = qDao.sortByQuizIdDescending(recentQuizzes);
            for(Quiz quiz: sorted){
                out.println("<li><a href=\"/quizInfo.jsp?id=" +  quiz.getQuizId() + "\">" + quiz.getDescription() + " (Author: " + uDao.getUser(quiz.getCreatorId()).getUserName() + ")" + "</a> </li>");
            }
        %>
    </ul>
    <input class="button button6" type="button" value="Hide" onclick=hide("your_recently_id")>
</div>

<p> <button class = "button" value = "achievements" onclick= show("achievements_id")>Achievements</button></p>
<div id = "achievements_id" style="display: none">
    <ul>
        <%
            List<String> achievements = aDao.getAchievements(id);
            for(String achievement : achievements){
                out.println("<li>" + achievement + "</li>");
            }
        %>
    </ul>
    <input class="button button6" type="button" value="Hide" onclick=hide("achievements_id")>
</div>

<p> <button class = "button" value = "friends activity" onclick= show("friends_activity_id")>Your friends' recent activity</button></p>
<div id = "friends_activity_id" style="display: none">
    <ul>
        <%
            user = uDao.getUser(uDao.getUserIdByName(name));
            List <User> list = fDao.getFriends(user);
            List<History> friendHistories = new ArrayList<History>();
            for (User u : list){
                List<History> h = historyDao.getHistories(u.getUserId());
                friendHistories.addAll(h);
            }
            sort = HistorySqlDao.sortByEndDate(friendHistories);
            for(History history : sort){
                out.println("<li><a href=\"/quizInfo.jsp?id=" +  history.getQuizId() + "\">" + qDao.getQuiz(history.getQuizId()).getDescription() + " (Author: " + uDao.getUser(history.getUserId()).getUserName() + ")" + "</a> </li>");
            }
        %>
    </ul>
    <input class="button button6" type="button" value="Hide" onclick=hide("friends_activity_id")>
</div>


<input class="button button1" type=button Value=challenge onclick=show("challenge_div")>
<div id=challenge_div style="display: none">
    <form action="MailServlet" method="post" id="Challenging">
        to: <input type="text" name="username" id="Challenging_username"> <br/>
        <label for="Challenging_quizzes">
            choose a quiz:
        </label>
        <select name="quizzes" id="Challenging_quizzes">
            <%
                int id2 = uDao.getUserIdByName((String)session.getAttribute("currentUser"));
                List<String> list2 = historyDao.forChallenge(id2);
                for (String s : list2){
                    String output = "<option value='" + s + "'>" + s + "</option>";
                    out.print(output);
                }
            %>
        </select>
        <input class="button button7" type="button" name="button" value="challenge" id = "Challenging_button">
        <span style="color: red;" id="Challenging_output"></span>
        <input class="button button6" type="button" value="cancel" onclick=hide("challenge_div")>
    </form>
</div>

<input class="button button1" type=button Value=request onclick=show("request_div")>
<div id=request_div style="display: none">
    <form action="MailServlet" method="post" id="FriendRequesting">
        to: <input type="text" name="username" id="FriendRequesting_username"> <br/>
        <input class="button button7" type="button" name="button" value="sendRequest" id="FriendRequesting_button">
        <span id="FriendRequesting_output"></span>
    </form>
    <input class="button button6" type="button" value="hide" onclick=hide("request_div")>
</div>

<input class="button button1" type=button Value=compose onclick=show("id_div")>
<div id=id_div style="display: none">
    <form action="MailServlet" method="post" id="Composing">
        to: <input type="text" name="username" id="Composing_username"> <br/>
        message: <input type="text" name="message" id="Composing_message"> <br/>
        <input class="button button7" type="button" name="button" value="send" id="Composing_button">
        <span style="color: red;" id="Composing_output"></span>

    </form>
    <input class="button button6" type="button" value="cancel" onclick=hide("id_div")>
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

<form method="POST" action="/IndexServlet">
    <input type="submit" value="Log Out" class="logOutButton">
</form>

</body>
</html>