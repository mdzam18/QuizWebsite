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
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd MMM yyyy");

        String quizIdStr = request.getParameter("id");
        if(quizIdStr == null) {
            //System.out.println(">>> No Quiz Here <<<");
            return;
        }
        int quizId = Integer.parseInt(quizIdStr);
        Quiz quiz = quizDao.getQuiz(quizId);
        if(quiz == null) {
            //System.out.println(">>> No Quiz Here <<<");
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
        let userPerformance_date;
        let userPerformance_score;
        let userPerformance_time;
        let userPerformance_date_button;
        let userPerformance_score_button;
        let userPerformance_time_button;

        window.onload = function() {
            userPerformance_date = document.getElementById("userPerformance_date");
            userPerformance_score = document.getElementById("userPerformance_score");
            userPerformance_time = document.getElementById("userPerformance_time");

            userPerformance_date_button = document.getElementById("userPerformance_date_button");
            userPerformance_score_button = document.getElementById("userPerformance_score_button");
            userPerformance_time_button = document.getElementById("userPerformance_time_button");

            userPerformance_date_button.setAttribute("onclick", "showByDate()");
            userPerformance_score_button.setAttribute("onclick", "showByScore()");
            userPerformance_time_button.setAttribute("onclick", "showByTime()");
        }
        function showByDate() {
            userPerformance_date.style.display = "block";
            userPerformance_score.style.display = "none";
            userPerformance_time.style.display = "none";

            userPerformance_date_button.disabled = true;
            userPerformance_score_button.disabled = false;
            userPerformance_time_button.disabled = false;
        }
        function showByScore() {
            userPerformance_date.style.display = "none";
            userPerformance_score.style.display = "block";
            userPerformance_time.style.display = "none";

            userPerformance_date_button.disabled = false;
            userPerformance_score_button.disabled = true;
            userPerformance_time_button.disabled = false;
        }
        function showByTime() {
            userPerformance_date.style.display = "none";
            userPerformance_score.style.display = "none";
            userPerformance_time.style.display = "block";

            userPerformance_date_button.disabled = false;
            userPerformance_score_button.disabled = false;
            userPerformance_time_button.disabled = true;
        }
    </script>
</head>
<body>
<div id="content">

    <%!
        String convertTime(int time) {
            StringBuilder builder = new StringBuilder();
            time /= 1000;
            if(time/60 != 0) {
                builder.append(time/60);
                builder.append("m ");
            }
            builder.append(time%60);
            builder.append('s');
            return builder.toString();
        }
    %>

    <%
        String userExists = (String) session.getAttribute("currentUser");
        if(userExists != null) {
            out.print("\t<a href=\"UserServlet\">My Page</a>");
        }
    %>

    <p class="quizName">
        <strong><%=quiz.getDescription()%></strong> by
        <strong><%=userDao.getUser(quiz.getCreatorId()).getUserName()%></strong>
    </p>

    <%
        if(userExists != null) {
            out.print("<form action=\"/CheckTakenQuiz\" method=\"GET\">");
            out.print("<input type=\"hidden\" name=\"quizId\" value=\"" + quizId + "\">");
            out.print("\t<input class=\"perfQuizButton\" type=\"submit\" value=\"Perform Quiz\">");
            out.print("</form>");
        }
    %>

    <%
        String thisUserName = (String) session.getAttribute("currentUser");
        int thisUserId = userDao.getUserIdByName(thisUserName);
        List<History> historiesForCurrent = historyDao.getUsersHistoryForQuiz(thisUserId, quizId);

        List<History> historiesForCurrentByDate = HistorySqlDao.sortByEndDate(historiesForCurrent);
        List<History> historiesForCurrentByScore = HistorySqlDao.sortByScore(historiesForCurrent);
        final Map<Integer, Integer> timeForQuizId = new HashMap<Integer, Integer>();
        for(History history : historiesForCurrent) {
            int diff = (int)(history.getEndDate().getTime() - history.getStartDate().getTime());
            timeForQuizId.put(history.getQuizId(), diff);
        }
        List<History> historiesForCurrentByTime = new ArrayList<History>(historiesForCurrent);
        historiesForCurrentByTime.sort(new Comparator<History>() {
            @Override
            public int compare(History o1, History o2) {
                return timeForQuizId.get(o1.getQuizId()) - timeForQuizId.get(o2.getQuizId());
            }
        });
    %>

    <div class="usersPerformance">
        <p align="center">
            Order By
            <button id="userPerformance_date_button" disabled>Date</button>
            <button id="userPerformance_score_button">Score</button>
            <button id="userPerformance_time_button">Time</button>
        </p>
        <div style="display: block;" id="userPerformance_date">
            <table class="userPerformance_table">
                <tr>
                    <th>Date</th>
                    <th>Score</th>
                    <th>Time</th>
                </tr>
            <% for(History history : historiesForCurrentByDate) { %>
                <tr>
                    <td><%= format.format(history.getEndDate()) %></td>
                    <td><%= history.getScore() %></td>
                    <td><%= convertTime(timeForQuizId.get(history.getQuizId())) %></td>
                </tr>
            <% } %>
            </table>
        </div>
        <div style="display: none;" id="userPerformance_score">
            <table class="userPerformance_table">
                <tr>
                    <th>Date</th>
                    <th>Score</th>
                    <th>Time</th>
                </tr>
            <% for(History history : historiesForCurrentByScore) { %>
                <tr>
                    <td><%= format.format(history.getEndDate()) %></td>
                    <td><%= history.getScore() %></td>
                    <td><%= convertTime(timeForQuizId.get(history.getQuizId())) %></td>
                </tr>
            <% } %>
            </table>
        </div>
        <div style="display: none" id="userPerformance_time">
            <table class="userPerformance_table">
                <tr>
                    <th>Date</th>
                    <th>Score</th>
                    <th>Time</th>
                </tr>
            <% for(History history : historiesForCurrentByTime) { %>
                <tr>
                    <td><%= format.format(history.getEndDate()) %></td>
                    <td><%= history.getScore() %></td>
                    <td><%= convertTime(timeForQuizId.get(history.getQuizId())) %></td>
                </tr>
            <% } %>
            </table>
        </div>
    </div>

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
