<%@ page import="java.util.*, Quiz.*, UserPackage.*" %>
<%@ page import="HistoryPackage.History" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your Results</title>

    <!-- Styles -->
    <link rel="stylesheet" type="text/css" href="styles/quizResults_style1.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">

    <script>
        function toMyPage() {
            document.getElementById('toMyPage').submit();
        }
    </script>
</head>
<body>
<div id="content">
    <%
        final String ATTR = "RESULTS";
        final String ATTR2 = "HISTORY";
        Map<Integer, QuestionPassResult> questionResults = (Map<Integer, QuestionPassResult>) request.getAttribute(ATTR);
        List<History> histories = (List<History>) request.getAttribute(ATTR2);
    %>

    <p class="headerNameP">Your Quiz Results</p>

    <p><form action="UserServlet" method="get" id="toMyPage">
        <a href="#" onclick="toMyPage()">My Page</a>
    </form></p>

    <div class="currentResult">
        <table>
            <tr>
                <th colspan="6" width="1200">Current Results</th>
            </tr>
            <tr>
                <th>Question №</th>
                <th>Question</th>
                <th>Question Type</th>
                <th>Score</th>
                <th>Your Answer(s)</th>
                <th>Valid Answer(s)</th>
            </tr>
            <%
                for(Integer q : questionResults.keySet()) {
                    QuestionPassResult questionPass = questionResults.get(q);
                    Question question = questionPass.question;
                    out.println("<tr>");
                    out.print("<td style=\"text-align: center;\">");
                    out.print(q);
                    out.println("</td>");
                    out.print("<td>");
                    out.print(question.getQuestion());
                    out.println("</td>");

                    String typeName = "";
                    int type = question.getType();
                    if(type == QuestionType.QUESTION_RESPONSE) {
                        typeName = "Question-Response";
                    } else if(type == QuestionType.MULTIPLE_CHOICE_QUESTION) {
                        typeName = "Multiple Choice";
                    } else if(type == QuestionType.PICTURE_RESPONSE_QUESTION) {
                        typeName = "Picture-Response Questions";
                    } else if(type == QuestionType.MULTI_ANSWER_QUESTION) {
                        typeName = "Multi-Answer Questions";
                    } else if(type == QuestionType.MULTIPLE_CHOICE_AND_ANSWER_QUESTION) {
                        typeName = "Multiple Choice with Multiple Answers";
                    }
                    out.print("<td>");
                    out.print(typeName);
                    out.println("</td>");

                    String stl = "color: green;";
                    if(questionPass.passType == QuestionPassResult.NOT_QUESTION_PASS) {
                        stl = "color: red;";
                    } else if(questionPass.passType == QuestionPassResult.PARTIAL_QUESTION_PASS) {
                        stl = "color: yellow;";
                    }
                    stl += "font-weight: bold; font-size: 18px; text-align: center;";
                    out.print("<td style=\"" + stl + "\">");
                    out.print(questionPass.userScore);
                    out.print("/");
                    out.print(question.getScore());
                    out.println("</td>");
                    List<String> list = questionPass.userAnswers;
                    out.print("<td>");
                    for(int i = 0; i<list.size(); i++) {
                        if(i != 0) {
                            out.print(", ");
                        }
                        out.print(list.get(i));
                    }
                    out.println("</td>");
                    out.print("<td>");
                    if(type == QuestionType.MULTI_ANSWER_QUESTION) {
                        MultipleAnswerQuestion multipleAnswer = (MultipleAnswerQuestion) question;
                        if(multipleAnswer.isOrdered()) {
                            List<String> orderAnswers = multipleAnswer.getOrderedAnswers();
                            for(int i = 0; i<orderAnswers.size(); i++) {
                                if(i != 0) {
                                    out.print(", ");
                                }
                                out.print("[");
                                out.print(orderAnswers.get(i));
                                out.print("]");
                            }
                        } else {
                            Set<String> set = multipleAnswer.getAnswerSet();
                            boolean bool = false;
                            for(String str : set) {
                                if(bool) {
                                    out.print(", ");
                                }
                                out.print(str);
                            }
                        }
                    } else {
                        Set<String> set = question.getAnswerSet();
                        boolean bool = false;
                        for(String str : set) {
                            if(bool) {
                                out.print(", ");
                            }
                            out.print(str);
                        }
                    }
                    out.println("</td>");
                    out.println("</tr>");
            }%>
        </table>
    </div>

    <table class="otherHistories">
        <tr>
            <th colspan="3">All Your Activity</th>
        </tr>
        <tr>
            <th>Score</th>
            <th>Start Time</th>
            <th>Start Time</th>
        </tr>
        <%
            int fullScore = 0;
            for(QuestionPassResult questionPass : questionResults.values()) {
                fullScore += questionPass.question.getScore();
            }
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd MMM yyyy");
            for(History history : histories) { %>
        <tr>
            <%String stl = "color: green;";
                if(history.getScore() == 0) {
                    stl = "color: red;";
                } else if(history.getScore() == fullScore) {
                    stl = "color: yellow;";
                }
                stl += "font-weight: bold;";%>
            <td width="100\" style="<%= stl %>">
                <%=history.getScore()%>/<%=fullScore%>
            </td>
            <td width="340">
                <%=format.format(history.getStartDate())%>
            </td>
            <td width="340">
                <%=format.format(history.getEndDate())%>
            </td>
        </tr>
        <%}%>
    </table>

</div>
</body>
</html>
