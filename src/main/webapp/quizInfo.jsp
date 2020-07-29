<%@ page import="Quiz.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        <%
            Quiz quiz = (Quiz) request.getAttribute("Quiz");
            if(quiz == null) {
                out.print("NoName");
            } else {
                out.print(quiz.getDescription());
            }
        %>
    </title>
</head>
<body>

</body>
</html>
