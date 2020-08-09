<%@ page import="Quiz.*, UserPackage.*, ServletContextPackage.*, HistoryPackage.*" %>
<%@ page import="java.text.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        /* DAOs */
        ServletContext context = request.getServletContext();
        UserDao userDao = (UserDao) context.getAttribute(ContextDataNames.USER_DAO);
        HistoryDao historyDao = (HistoryDao) context.getAttribute(ContextDataNames.HISTORY_DAO);
        QuizDao quizDao = (QuizDao) context.getAttribute(ContextDataNames.QUIZ_DAO);

        String quizIdStr = request.getParameter("id");
        if(quizIdStr == null) {
            System.out.println(">>> No Quiz Here <<<");
            return;
        }
        int quizId = Integer.parseInt(quizIdStr);
        Quiz quiz = quizDao.getQuiz(quizId);
        if(quiz == null) {
            System.out.println(">>> No Quiz Here <<<");
            return;
        }
    %>
    <title><%= quiz.getDescription() %></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Styles -->
    <link rel="stylesheet" type="text/css" href="styles/quizInfo_style1.css">
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
        String userExists = (String) session.getAttribute("currentUser");
        if(userExists != null) {
            out.print("<p><form action=\"UserServlet\" method=\"get\" id=\"toMyPage\">");
            out.print("\t<a href=\"#\" onclick=\"toMyPage()\">My Page</a>");
            out.print("</form></p>");
        }
    %>

    <p class="quizName">
        <%
            out.print("<strong>");
            out.print(quiz.getDescription());
            out.print("</strong> by <strong>");
            out.print(userDao.getUser(quiz.getCreatorId()).getUserName());
            out.print("</strong>");
        %>
    </p>

    <%
        if(userExists != null) {
            out.print("<form action=\"/CheckTakenQuiz\" method=\"GET\">");
            out.print("<input type=\"hidden\" name=\"quizId\" value=\"" + quizId + "\">");
            out.print("\t<input class=\"perfQuizButton\" type=\"submit\" value=\"Perform Quiz\">");
            out.print("</form>");
        }
    %>

    <div class="historyTable">
        <table>
            <tr>
                <th align="center" colspan="4">Quiz History</th>
            </tr>
            <tr>
                <th>User Name</th>
                <th>Score</th>
                <th>Start Time</th>
                <th>Finish Time</th>
            </tr>
            <%
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd MMM yyyy");
                List<History> historiesUnsorted = historyDao.getHistoriesByQuizId(quizId);
                List<History> historiesByTime = HistorySqlDao.sortByEndDate(historiesUnsorted);
                for(History history : historiesByTime) {
                    String userName = userDao.getUser(history.getUserId()).getUserName();
                    out.println("<tr>");
                    out.print("<td>");
                    out.print(userName);
                    out.println("</td>\n<td>");
                    out.print(history.getScore());
                    out.println("</td>\n<td>");
                    out.print(format.format(history.getStartDate()));
                    out.println("</td>\n<td>");
                    out.print(format.format(history.getEndDate()));
                    out.println("</td>\n");
                    out.println("</tr>");
                }
            %>
        </table>
    </div>

    <div class="otherHistories">
        <div class="top10Performers">
            <table>
                <tr>
                    <th align="center" colspan="2">Top 10 Performers</th>
                </tr>
                <tr>
                    <th>User Name</th>
                    <th>Score</th>
                </tr>
                <%
                    List<History> historiesByScore = HistorySqlDao.sortByScore(historiesUnsorted);
                    List<History> subListHistoriesByScore = historiesByScore.subList(0, Math.min(historiesByScore.size(), 10));
                    for(History history : subListHistoriesByScore) {
                        String userName = userDao.getUser(history.getUserId()).getUserName();
                        out.println("<tr>");
                        out.print("<td>");
                        out.print(userName);
                        out.println("</td>\n<td>");
                        out.print(history.getScore());
                        out.println("</td>");
                        out.println("</tr>");
                    }
                %>
            </table>
        </div>
        <div class="last12HourDiv">
            <table>
                <tr>
                    <th align="center" colspan="2">Quiz Performers of last 12 Hour</th>
                </tr>
                <tr>
                    <th>User Name</th>
                    <th>Score</th>
                </tr>
                <%
                    final long HOUR_12 = 1000*60*60*12;
                    long currentTime = System.currentTimeMillis();
                    for(History history : historiesByTime) {
                        Date currEndDate = history.getEndDate();
                        if((currentTime - currEndDate.getTime()) > HOUR_12) {
                            break;
                        }
                        String userName = userDao.getUser(history.getUserId()).getUserName();
                        out.println("<tr>");
                        out.print("<td>");
                        out.print(userName);
                        out.println("</td>\n<td>");
                        out.print(history.getScore());
                        out.println("</td>");
                        out.println("</tr>");
                    }
                %>
            </table>
        </div>
    </div>

    <div class="quizStatistics">
        <table>
            <tr>
                <th align="center" colspan="6">Statistics</th>
            </tr>
            <tr>
                <th>User Name</th>
                <th>Attempts</th>
                <th>Last Attempt</th>
                <th>Min. Score</th>
                <th>Avg. Score</th>
                <th>Max. Score</th>
            </tr>
            <%
                Map<Integer, List<Integer>> scoresPerUser = new HashMap<Integer, List<Integer>>();
                Map<Integer, Date> lastAttempt = new HashMap<Integer, Date>();
                for(History history : historiesUnsorted) {
                    int userId = history.getUserId();
                    int score = history.getScore();
                    if(scoresPerUser.containsKey(userId)) {
                        scoresPerUser.get(userId).add(score);
                    } else {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(score);
                        scoresPerUser.put(userId, list);
                    }
                    Date lastD = history.getEndDate();
                    if(!lastAttempt.containsKey(userId)) {
                        lastAttempt.put(userId, lastD);
                    } else {
                        Date d = lastAttempt.get(userId);
                        if(d.getTime() < lastD.getTime()) {
                            lastAttempt.put(userId, lastD);
                        }
                    }
                }

                Map<Integer, Double[]> minAvgMaxPerUser = new HashMap<Integer, Double[]>();
                for(Integer userId : scoresPerUser.keySet()) {
                    Collections.sort(scoresPerUser.get(userId));
                    List<Integer> list = scoresPerUser.get(userId);
                    Double[] arr = new Double[3];
                    arr[0] = Double.valueOf(list.get(0));
                    arr[2] = Double.valueOf(list.get(list.size() - 1));
                    double sum = 0;
                    for(int i = 0; i<list.size(); i++) {
                        sum += list.get(i);
                    }
                    arr[1] = sum/list.size();
                    minAvgMaxPerUser.put(userId, arr);
                }

                for(Integer userId : minAvgMaxPerUser.keySet()) {
                    Double[] arr = minAvgMaxPerUser.get(userId);
                    String userName = userDao.getUser(userId).getUserName();
                    out.println("<tr>");
                    out.print("<td>");
                    out.print(userName);
                    out.println("</td>");
                    out.print("<td>");
                    out.print(scoresPerUser.get(userId).size());
                    out.println("</td>");
                    out.print("<td>");
                    out.print(format.format(lastAttempt.get(userId)));
                    out.println("</td>");
                    for(int i = 0; i<arr.length; i++) {
                        out.print("<td>");
                        out.print(arr[i]);
                        out.println("</td>");
                    }
                    out.println("</tr>");
                }
            %>
        </table>
    </div>

</div>
</body>
</html>
