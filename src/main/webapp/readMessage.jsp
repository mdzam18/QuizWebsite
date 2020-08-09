<%@ page import="MailPackage.MailSqlDao" %>
<%@ page import="MailPackage.Mail" %><%--
  Created by IntelliJ IDEA.
  User: Nanuka
  Date: 15/07/2020
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MailSqlDao mailDao = new MailSqlDao();
    Mail mail = mailDao.getMailById(Integer.parseInt(request.getParameter("id")));
    String name = request.getParameter("sender");

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form>
    <h1> from: <%=name %> </h1>
    <h1> Type: <%=mail.getType() %> </h1>
   <%
        if(mail.getType().equals( Mail.challengeType)){
           out.print("<a href=\"quizInfo.jsp?id=" + mail.getMessage() + "\">Perform Quiz</a>");
        }else {
            out.print("<h1> message: " + mail.getMessage() +" </h1>");
        }
    %>
   <h1> Date: <%=mail.getDate() %> </h1>
</form>


<a href="/UserServlet"> Back to homepage</a>
</body>
</html>
