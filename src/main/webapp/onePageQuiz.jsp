<%@ page import="Quiz.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        final String QUIZ_ATR_NAME = "QUIZ";
        Quiz quiz = (Quiz) request.getAttribute(QUIZ_ATR_NAME);
    %>

    <title><%= quiz.getDescription() %></title>

    <!-- Styles -->
    <link rel="stylesheet" type="text/css" href="styles/onePageQuiz_style1.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
</head>
<body>
    <div id="quizName">
        <span><%= quiz.getDescription() %></span>
    </div>

    <div id="content">
        <form action="/CheckTakenQuiz" method="POST">
            <input type="hidden" name="startTime" value="<%= System.currentTimeMillis() %>">
            <%
                List<Question> list = quiz.getQuestionSet();
                for(int i = 0; i<list.size(); i++) {
                    out.println(QuestionToHTML.convertQuestion(list.get(i), i+1));
                }
            %>
            <input type="submit" value="Submit Quiz" class="submitButton">
        </form>
    </div>

</body>
</html>