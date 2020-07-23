<%@ page import="ProfilePackage.MailSqlDao" %>
<%@ page import="ProfilePackage.Mail" %><%--
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
from: <%=name %>
message: <%=mail.getMessage() %>
Date: <%=mail.getDate() %>
</body>
</html>