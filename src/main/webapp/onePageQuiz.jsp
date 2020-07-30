<%@ page import="Quiz.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%= getDescription(request.getAttribute("quiz")) %></title>

    <!-- Styles -->
    <link rel="stylesheet" type="text/css" href="styles/onePageQuiz_style1.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
</head>
<body>
    <%!
        Quiz getQuiz(Object obj) {
            Quiz quiz = (Quiz) obj;
            return quiz;
        }
        List<Question> getQuestionList(Object obj) {
            return getQuiz(obj).getQuestionSet();
        }
        String getDescription(Object obj) {
            return getQuiz(obj).getDescription();
        }
    %>

    <div id="quizName">
            <span><%= getDescription(request.getAttribute("quiz")) %></span>
        </div>

        <div id="content">
        <%
            List<Question> list = getQuestionList(request.getAttribute("quiz"));
            for(int i = 0; i<list.size(); i++) {
                out.println(QuestionToHTML.convertQuestion(list.get(i), i+1));
            }
        %>
        <form>
        </form>
    </div>

</body>
</html>
