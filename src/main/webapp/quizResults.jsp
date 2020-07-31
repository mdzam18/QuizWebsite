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

        <p><a href="UserPage.jsp">My Page</a></p>

        <table class="currentResult">
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
                    int type = question.getType();
                    out.println("<tr>");
                    out.print("<td style=\"text-align: center;\">");
                    out.print(q);
                    out.println("<td>");
                    out.print("<td>");
                    out.print(question.getQuestion());
                    out.println("<td>");

                    String typeName = "";
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
                    out.println("<td>");

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
                    out.println("<td>");
                    List<String> list = questionPass.userAnswers;
                    out.print("<td>");
                    for(int i = 0; i<list.size(); i++) {
                        if(i != 0) {
                            out.print(", ");
                        }
                        out.print("[");
                        out.print(list.get(i));
                        out.print("]");
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
                                out.print("[");
                                out.print(str);
                                out.print("]");
                            }
                        }
                    } else {
                        Set<String> set = question.getAnswerSet();
                        boolean bool = false;
                        for(String str : set) {
                            if(bool) {
                                out.print(", ");
                            }
                            out.print("[");
                            out.print(str);
                            out.print("]");
                        }
                    }
                    out.println("</td>");
                    out.println("</tr>");
                }
            %>
        </table>

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
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd MMMMm yyyy");
                for(History history : histories) {
                    out.println("<tr>");
                    String stl = "color: green;";
                    if(history.getScore() == 0) {
                        stl = "color: red;";
                    } else if(history.getScore() == fullScore) {
                        stl = "color: yellow;";
                    }
                    stl += "font-weight: bold;";
                    out.print("<td width=\"100\" style=\"" + stl + "\">");
                    out.print(history.getScore());
                    out.print("/");
                    out.print(fullScore);
                    out.println("</td>");
                    out.print("<td width=\"340\">");
                    out.print(format.format(history.getStartDate()));
                    out.println("</td>");
                    out.print("<td width=\"340\">");
                    out.print(format.format(history.getEndDate()));
                    out.println("</td>\n</tr>");
                }
            %>
        </table>

    </div>
</body>
</html>
