<%@ page import="ProfilePackage.Mail" %>
<%@ page import="HistoryPackage.HistorySqlDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ProfilePackage.Mail" %>
<%@ page import="ProfilePackage.MailSqlDao" %>
<%@ page import="UserPackage.UserSqlDao" %>
<%@ page import="java.util.List" %>
<%@ page import="HistoryPackage.HistorySqlDao" %>

<html>
<head>
    <title>Welcome</title>
    <%
        MailSqlDao mailDao = new MailSqlDao();
        UserSqlDao userDao = new UserSqlDao();
        HistorySqlDao historyDao = new HistorySqlDao();
        out.print(request.getParameter("username"));
        ArrayList<Mail> mails = mailDao.getMails(3);
    %>

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

</head>
<body>
<div class="SearchBox">
    <form action="UserServlet" method="POST">
        <input class="search-txt" type="text" name="username" placeholder="Type To Search" value="${username}">
        <a class= "search-btn" href = "#"></a>
    </form>
</div>
<h1 style="text-align:center">User Profile</h1>
<div class= "Profile">
    <h1> <label >User Name: <%= request.getParameter("username")%> </label> </h1>
    <h2> <label >Name: <%= request.getParameter("name")%> </label> </h2>
    <h2> <label >Surname: <%= request.getParameter("surname")%> </label> </h2>
    <h2> <label >Birth Date: <%= request.getParameter("birthDate")%> </label> </h2>
    <h2> <label >Birth Place: <%= request.getParameter("birthPlace")%> </label> </h2>
    <h2> <label >Status: <%= request.getParameter("status")%> </label> </h2>
</div>
<form action= "UserServlet" method="GET">
    <p><button>Friends</button></p>
    <p><button>Friend Requests</button></p>
    <p><button>Sent Requests</button></p>
    <p><button>Edit Profile</button></p>
    <p><button>delete account</button></p>
</form>

<div name=frm_test id=frm_test>
    <input type=button Value=compose onclick=show("id_div")>
    <div id=id_div style="display: none">
        <form action="MailServlet" method="post">
            to: <input type="text" name="username"> <br/>
            message: <input type="text" name="message"> <br/>
            <input type="submit" name="button" value="send">
            <input type="button" value="cancel" onclick=hide("id_div")>
        </form>
    </div>


    <input type=button Value=inbox onclick=show("inbox_div")>
    <div id=inbox_div style="display: none">
        <form action="MailServlet" method="post">
            <%
                for (Mail mail : mails) {
                    String senderName = userDao.getUser(mail.getSenderId()).getUserName();
                    String output = "<li><a href=\"readMessage.jsp?id=" + mail.getMailId() + "&sender=" + senderName + "\">" + "from: " + senderName + "</a></li>";
                    out.print(output);
                }
            %>
        </form>
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
                    int id = userDao.getUserIdByName(request.getParameter("username"));
                    List<String> list = historyDao.forChallenge(id);
                    for (String s : list){
                        String output = "<option value=\"" + s + "\">" + s + "</option>";
                        out.print(output);
                    }
                %>
            </select>
            <input type="submit" name="button" value="challenge">
            <input type="button" value="cancel" onclick=hide("challenge_div")>
        </form>
    </div>

    <input type=button Value=request onclick=show("request_div")>
    <div id=request_div style="display: none">
        <form action="MailServlet" method="post">
            to: <input type="text" name="username"> <br/>
            <input type="submit" name="button" value="sendRequest">
        </form>
        <input type="button" value="hide" onclick=hide("request_div")>
    </div>


</div>
</body>
</html>