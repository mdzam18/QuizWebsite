<%@ page import="java.util.ArrayList" %>
<%@ page import="ProfilePackage.Mail" %>
<%@ page import="ProfilePackage.MailSqlDao" %>
<%@ page import="UserPackage.UserSqlDao" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17.07.2020
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <p>
        <%
            MailSqlDao mailDao = new MailSqlDao();
            UserSqlDao userDao = new UserSqlDao();
            out.print(request.getParameter("username"));
            ArrayList<Mail> mails = mailDao.getMails(3);
        %>
    </p>
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
    <div id=inbox_div>
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
</div>
</body>
</html>
