/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-07-31 21:46:50 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Quiz.*;
import UserPackage.*;
import ServletContextPackage.*;
import HistoryPackage.*;
import java.text.*;
import java.util.*;

public final class quizInfo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    ");

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
    
      out.write("\r\n");
      out.write("    <title>");
      out.print( quiz.getDescription() );
      out.write("</title>\r\n");
      out.write("\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("\r\n");
      out.write("    <!-- Styles -->\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles/quizInfo_style1.css\">\r\n");
      out.write("    <!-- Google Fonts -->\r\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"content\">\r\n");
      out.write("\r\n");
      out.write("    <a href=\"UserPage.jsp\">Go to My Page</a>\r\n");
      out.write("\r\n");
      out.write("    <p class=\"quizName\">\r\n");
      out.write("        ");

            out.print("<strong>");
            out.print((quiz == null)? "NoName": quiz.getDescription());
            out.print("</strong> by <strong>");
            if(quiz != null) {
                out.print(userDao.getUser(quiz.getCreatorId()).getUserName());
            }
            out.print("</strong>");
        
      out.write("\r\n");
      out.write("    </p>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"historyTable\">\r\n");
      out.write("        <p>Quiz History</p>\r\n");
      out.write("        <table>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th>User Name</th>\r\n");
      out.write("                <th>Score</th>\r\n");
      out.write("                <th>Start Time</th>\r\n");
      out.write("                <th>Finish Time</th>\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");

                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd MMMMm yyyy");
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
                    out.println("</td>\n<td>");
                    out.println("</tr>");
                }
            
      out.write("\r\n");
      out.write("        </table>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"otherHistories\">\r\n");
      out.write("        <div class=\"top10Performers\">\r\n");
      out.write("            <p>Top 10 Performers</p>\r\n");
      out.write("            <table>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th>User Name</th>\r\n");
      out.write("                    <th>Score</th>\r\n");
      out.write("                </tr>\r\n");
      out.write("                ");

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
                
      out.write("\r\n");
      out.write("            </table>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"last12HourDiv\">\r\n");
      out.write("            <p>Quiz Performers of last 12 Hour</p>\r\n");
      out.write("            <table>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th>User Name</th>\r\n");
      out.write("                    <th>Score</th>\r\n");
      out.write("                </tr>\r\n");
      out.write("                ");

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
                
      out.write("\r\n");
      out.write("            </table>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"quizStatistics\">\r\n");
      out.write("        <table>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th>User Name</th>\r\n");
      out.write("                <th>Attempts</th>\r\n");
      out.write("                <th>Last Attempt</th>\r\n");
      out.write("                <th>Min. Score</th>\r\n");
      out.write("                <th>Avg. Score</th>\r\n");
      out.write("                <th>Max. Score</th>\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");

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
                    for(int i = 0; i<arr.length; i++) {
                        out.print("<td>");
                        out.print(arr[i]);
                        out.println("</td>");
                    }
                    out.println("</tr>");
                }
            
      out.write("\r\n");
      out.write("        </table>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <!--<form action=\"/CheckTakenQuiz\" method=\"GET\">\r\n");
      out.write("        <input type=\"hidden\" name=\"quizId\" value=\"%= quizId %\">\r\n");
      out.write("        <input type=\"submit\" value=\"Pass Quiz\">\r\n");
      out.write("    </form>-->\r\n");
      out.write("\r\n");
      out.write("    ");

        String isuser = (String) session.getAttribute("currentUser");
        if(isuser != null) {
            out.print("<form action=\"/CheckTakenQuiz\" method=\"GET\">");
            out.print("<input type=\"hidden\" name=\"quizId\" value=\"" + quizId + "\">");
            out.print("\t<input type=\"submit\" value=\"Pass Quiz\">");
            out.print("</form>");
        }
    
      out.write("\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
