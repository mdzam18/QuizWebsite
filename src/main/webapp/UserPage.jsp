<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="FriendsPackage.FriendsSqlDao" %>
<%@ page import="UserPackage.UserSqlDao" %>
<%@ page import="UserPackage.User" %>
<%@ page import="Quiz.Quiz" %>
<%@ page import="Quiz.QuizSqlDao" %>
<%@ page import="HistoryPackage.HistorySqlDao" %>
<%@ page import= "java.sql.SQLException" %>
<<<<<<< HEAD
<%@ page import="HistoryPackage.History" %>
=======
<%@ page import="MailPackage.MailSqlDao" %>
<%@ page import="MailPackage.Mail" %>
>>>>>>> d0fc37a5f5a3465aaab991f56bba0bb68cdf9b22
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>

<html>
<style>
    .button {
        background-color: #4CAF50; /* Green */
        border: none;
        color: white;
        padding: 16px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        transition-duration: 0.4s;
        cursor: pointer;
    }
    input[type=text], select {
        width: 10%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    .button1 {
        background-color: white;
        color: black;
        border: 2px solid #4CAF50;
    }

    .button1:hover {
        background-color: #4CAF50;
        color: white;
    }

    .button2 {
        background-color: white;
        color: black;
        border: 2px solid #008CBA;
    }

    .button2:hover {
        background-color: #008CBA;
        color: white;
    }

    .button3 {
        background-color: white;
        color: black;
        border: 2px solid #f44336;
    }

    .button3:hover {
        background-color: #f44336;
        color: white;
    }

    .button4 {
        background-color: white;
        color: black;
        border: 2px solid #e7e7e7;
    }

    .button4:hover {background-color: #e7e7e7;}

    .button5 {
        background-color: white;
        color: black;
        border: 2px solid #555555;
    }

    .button5:hover {
        background-color: #555555;
        color: white;
    }
</style>
<head>
    <title>Welcome</title>
</head>
<body>
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
<<<<<<< HEAD
            List<History> recentlyTakenQuizzes = historyDao.getHistories(id);
            for(History history : recentlyTakenQuizzes ){
            %>
        <% String href = "/quizInfo.jsp?id=" + history.getQuizId(); %>
        <li><a href="<%= href %>"><%= qDao.getQuiz(history.getQuizId()).getDescription() %></a></li>
        <% } %>
        <input type="button" value="Hide" onclick=hide("recentlyTaken_id")>
=======
            /*
              name = (String)session.getAttribute("currentUser");
              user = uDao.getUser(uDao.getUserIdByName(name));
              List <Quiz> recentlyTaken = historyDao.getRecentlyTakenQuizzes(user);
              for(Quiz quiz: recentlyTaken){
                  out.println("description: "+ quiz.getDescription() + " category: " + quiz.getCategory());
              }
              */
        %>
        <input type="button" value="close" onclick=hide("recentlyTaken_id")>
>>>>>>> d0fc37a5f5a3465aaab991f56bba0bb68cdf9b22
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
        <input type="submit" name="button" value="challenge">
        <input type="button" value="cancel" onclick=hide("challenge_div")>
    </form>
</div>

<input class="button button1" type=button Value=request onclick=show("request_div")>
<div id=request_div style="display: none">
    <form action="MailServlet" method="post">
        to: <input type="text" name="username"> <br/>
        <input type="submit" name="button" value="sendRequest">
    </form>
    <input type="button" value="hide" onclick=hide("request_div")>
</div>

<input class="button button1" type=button Value=compose onclick=show("id_div")>
<div id=id_div style="display: none">
    <form action="MailServlet" method="post">
        to: <input type="text" name="username"> <br/>
        message: <input type="text" name="message"> <br/>
        <input type="submit" name="button" value="send">
        <input type="button" value="cancel" onclick=hide("id_div")>
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
        <input type="button" value="cancel" onclick=hide("inbox_div")>
    </form>
</div>
</body>
</html>
