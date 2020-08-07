/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-08-07 20:13:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import java.util.List;
import FriendsPackage.FriendsSqlDao;
import UserPackage.UserSqlDao;
import UserPackage.User;
import Quiz.Quiz;
import Quiz.QuizSqlDao;
import HistoryPackage.HistorySqlDao;
import AchievementsPackage.AchievementsSqlDao;
import AchievementsPackage.AchievementsDao;
import java.sql.SQLException;
import MailPackage.Mail;
import MailPackage.MailSqlDao;
import HistoryPackage.History;

public final class UserPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
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
      response.setContentType("text/html");
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <link href=\"../style.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("    <title>Welcome</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    function hide(id) {\r\n");
      out.write("        let div_ref = document.getElementById(id);\r\n");
      out.write("        div_ref.style.display = \"none\";\r\n");
      out.write("    }\r\n");
      out.write("    function show(id) {\r\n");
      out.write("        let div_ref = document.getElementById(id);\r\n");
      out.write("        div_ref.style.display = \"block\";\r\n");
      out.write("    }\r\n");
      out.write("    function toCreateQuizPage() {\r\n");
      out.write("        window.location.href = \"createQuiz.jsp\";\r\n");
      out.write("    }\r\n");
      out.write("    /*window.onload = function () {\r\n");
      out.write("        let elements = document.getElementsByTagName(\"form\");\r\n");
      out.write("        for(let i = 0; i<elements.length; i++) {\r\n");
      out.write("            elements[i].addEventListener(\"click\", function(event) {\r\n");
      out.write("                event.preventDefault();\r\n");
      out.write("            });\r\n");
      out.write("        }\r\n");
      out.write("    }*/\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");

    UserSqlDao uDao = new UserSqlDao();
    FriendsSqlDao fDao = new FriendsSqlDao();
    QuizSqlDao qDao = new QuizSqlDao();
    HistorySqlDao historyDao = new HistorySqlDao();
    AchievementsSqlDao aDao = new AchievementsSqlDao();
    int id = uDao.getUserIdByName((String)session.getAttribute("currentUser"));
    System.out.println(uDao.getUser(id).getName());
    MailSqlDao mailDao = new MailSqlDao();
    UserSqlDao userDao = new UserSqlDao();
    //out.print(request.getParameter("username"));
    ArrayList<Mail> mails = mailDao.getMails(3);

      out.write("\r\n");
      out.write("<div class= \"SearchBox\">\r\n");
      out.write("    <form action= \"UserServlet\" method=\"POST\">\r\n");
      out.write("        <input class= \"search-txt\" type = \"text\" name = \"username\"  id = \"id\" placeholder= \"Type To Search\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${username}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" value = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("        <input class = \"button button4\" type=\"submit\" name=\"button\" value=\"search\">\r\n");
      out.write("        <a class= \"search-btn\" href = \"#\"></a>\r\n");
      out.write("        ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    </form>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div class=\"Profile\">\r\n");
      out.write("    <h2> User Name: ");
      out.print( (String)session.getAttribute("currentUser"));
      out.write(" </h2>\r\n");
      out.write("     <h2> Name: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</h2>\r\n");
      out.write("    <h2> Surname: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${surname}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(" </h2>\r\n");
      out.write("    <h2> Birth Place: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${birthPlace}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("  </h2>\r\n");
      out.write("    <h2> Status: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(" </h2>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<button class = \"button\" style=\"font-size: 20px;\" onclick=\"toCreateQuizPage()\">Create New Quiz</button>\r\n");
      out.write("\r\n");
      out.write("<form action= UserServlet method=\"POST\">\r\n");
      out.write("    <input class = \"button button4\" type=\"submit\" name=\"button\" value=\"friends\">\r\n");
      out.write("    <input type=\"hidden\" name=\"username\" VALUE= ");
      out.print((String)session.getAttribute("currentUser"));
      out.write(">\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<form action= UserServlet method=\"POST\">\r\n");
      out.write("    <input class = \"button button4\" type=\"submit\" name=\"button\" value=\"sent requests\">\r\n");
      out.write("    <input type=\"hidden\" name=\"username\" VALUE= ");
      out.print((String)session.getAttribute("currentUser"));
      out.write(">\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<form action= UserServlet method=\"POST\">\r\n");
      out.write("    <input class = \"button button4\" type=\"submit\" name=\"button\" value=\"friend requests\">\r\n");
      out.write("    <input type=\"hidden\" name=\"username\" VALUE= ");
      out.print((String)session.getAttribute("currentUser"));
      out.write(">\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<form action= UserServlet method=\"POST\">\r\n");
      out.write("    <input class = \"button button3\" type=\"submit\" name=\"button\" value=\"delete\">\r\n");
      out.write("    <input type=\"hidden\" name=\"username\" VALUE= ");
      out.print((String)session.getAttribute("currentUser"));
      out.write(">\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<form action= UserServlet method=\"POST\">\r\n");
      out.write("    <input class = \"button button3\" type=\"submit\" name=\"button\" value=\"edit profile\">\r\n");
      out.write("    <input type=\"hidden\" name=\"username\" VALUE= ");
      out.print((String)session.getAttribute("currentUser"));
      out.write(">\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("    .admin{\r\n");
      out.write("        position: absolute;\r\n");
      out.write("        top: 8px;\r\n");
      out.write("        right: 16px;\r\n");
      out.write("        font-size: 24px;\r\n");
      out.write("        color: #008CBA;\r\n");
      out.write("        font-weight: bold;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    a:link {\r\n");
      out.write("        text-decoration: none;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    a:hover {\r\n");
      out.write("        text-decoration: underline;\r\n");
      out.write("    }\r\n");
      out.write("    .logOutButton {\r\n");
      out.write("        margin-top: 20px;\r\n");
      out.write("        padding: 8px 14px;\r\n");
      out.write("        font-size: 20px;\r\n");
      out.write("        border-radius: 6px;\r\n");
      out.write("    }\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<div class=\"admin\">\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    ");

        String name = (String)session.getAttribute("currentUser");
        User user = uDao.getUser(uDao.getUserIdByName(name));
        if(user.isAdministrator()) {
            out.print("<a href=\"/admin\">Go as Administrator</a>");
        }
    
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<p> <button class = \"button\" value = \"popular quizzes\" onclick= show(\"popular_id\")>Popular quizzes</button></p>\r\n");
      out.write("<div id = \"popular_id\" style=\"display: none\">\r\n");
      out.write("    <ul>\r\n");
      out.write("    ");

        List<Quiz> popularQuizzes = historyDao.getPopularQuizzes();
        List<Quiz> sorted = qDao.sortByQuizIdDescending(popularQuizzes);
        for(Quiz quiz: sorted) {
            out.println("<li><a href=\"/quizInfo.jsp?id=" +  quiz.getQuizId() + "\">" + quiz.getDescription() + " (Author: " + uDao.getUser(quiz.getCreatorId()).getUserName() + ")" + "</a> </li>");
        }
    
      out.write("\r\n");
      out.write("    </ul>\r\n");
      out.write("    <input class=\"button button6\" type=\"button\" value=\"Hide\" onclick=hide(\"popular_id\")>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<p> <button class = \"button\" value = \"recently quizzes\" onclick= show(\"recently_id\")>Recently created quizzes</button></p>\r\n");
      out.write("<div id = \"recently_id\" style=\"display: none\">\r\n");
      out.write("    <ul>\r\n");
      out.write("    ");

        List<Quiz> recentQuizzes = qDao.getRecentlyCreatedQuizzes();
        sorted = qDao.sortByQuizIdDescending(recentQuizzes);
        for(Quiz quiz: sorted){
            out.println("<li><a href=\"/quizInfo.jsp?id=" +  quiz.getQuizId() + "\">" + quiz.getDescription() + " (Author: " + uDao.getUser(quiz.getCreatorId()).getUserName() + ")" + "</a> </li>");
        }
    
      out.write("\r\n");
      out.write("    </ul>\r\n");
      out.write("    <input class=\"button button6\" type=\"button\" value=\"Hide\" onclick=hide(\"recently_id\")>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<p> <button class = \"button\" value = \"recently taken quizzes\" onclick= show(\"recentlyTaken_id\")>Recently taken quizzes</button></p>\r\n");
      out.write("<div id = \"recentlyTaken_id\" style=\"display: none\">\r\n");
      out.write("    <ul>\r\n");
      out.write("    ");

        List<History> recentlyTakenQuizzes = historyDao.getHistories(id);
        List<History> sort = historyDao.sortByEndDate(recentlyTakenQuizzes);
        for(History history : sort){
            out.println("<li><a href=\"/quizInfo.jsp?id=" +  history.getQuizId() + "\">" + qDao.getQuiz(history.getQuizId()).getDescription() + " (Author: " + uDao.getUser(history.getUserId()).getUserName() + ")" + "</a> </li>");
        }
    
      out.write("\r\n");
      out.write("    </ul>\r\n");
      out.write("    <input class=\"button button6\" type=\"button\" value=\"Hide\" onclick=hide(\"recentlyTaken_id\")>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<p> <button class = \"button\" value = \"your recently created quizzes\" onclick= show(\"your_recently_id\")>Your recently created quizzes</button></p>\r\n");
      out.write("<div id = \"your_recently_id\" style=\"display: none\">\r\n");
      out.write("    <ul>\r\n");
      out.write("    ");

        recentQuizzes = qDao.getRecentlyCreatedQuizzesByUser(id);
        sorted = qDao.sortByQuizIdDescending(recentQuizzes);
        for(Quiz quiz: sorted){
            out.println("<li><a href=\"/quizInfo.jsp?id=" +  quiz.getQuizId() + "\">" + quiz.getDescription() + " (Author: " + uDao.getUser(quiz.getCreatorId()).getUserName() + ")" + "</a> </li>");
        }
    
      out.write("\r\n");
      out.write("    </ul>\r\n");
      out.write("    <input class=\"button button6\" type=\"button\" value=\"Hide\" onclick=hide(\"your_recently_id\")>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<p> <button class = \"button\" value = \"achievements\" onclick= show(\"achievements_id\")>Achievements</button></p>\r\n");
      out.write("<div id = \"achievements_id\" style=\"display: none\">\r\n");
      out.write("    <ul>\r\n");
      out.write("    ");

        List<String> achievements = aDao.getAchievements(id);
        for(String achievement : achievements){
            out.println("<li>" + achievement + "</li>");

        }
    
      out.write("\r\n");
      out.write("    </ul>\r\n");
      out.write("    <input class=\"button button6\" type=\"button\" value=\"Hide\" onclick=hide(\"achievements_id\")>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<p> <button class = \"button\" value = \"friends activity\" onclick= show(\"friends_activity_id\")>Your friends' recent activity</button></p>\r\n");
      out.write("<div id = \"friends_activity_id\" style=\"display: none\">\r\n");
      out.write("    <ul>\r\n");
      out.write("        ");

            user = uDao.getUser(uDao.getUserIdByName(name));
            List <User> list = fDao.getFriends(user);
            List<History> friendHistories = new ArrayList<History>();
            for (User u : list){
                List<History> h = historyDao.getHistories(u.getUserId());
                friendHistories.addAll(h);
            }
            sort = HistorySqlDao.sortByEndDate(friendHistories);
            for(History history : sort){
                out.println("<li><a href=\"/quizInfo.jsp?id=" +  history.getQuizId() + "\">" + qDao.getQuiz(history.getQuizId()).getDescription() + " (Author: " + uDao.getUser(history.getUserId()).getUserName() + ")" + "</a> </li>");
            }
        
      out.write("\r\n");
      out.write("    </ul>\r\n");
      out.write("    <input class=\"button button6\" type=\"button\" value=\"Hide\" onclick=hide(\"friends_activity_id\")>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<input class=\"button button1\" type=button Value=challenge onclick=show(\"challenge_div\")>\r\n");
      out.write("<div id=challenge_div style=\"display: none\">\r\n");
      out.write("    <form action=\"MailServlet\" method=\"post\">\r\n");
      out.write("        to: <input type=\"text\" name=\"username\"> <br/>\r\n");
      out.write("        <label for=\"quizzes\">\r\n");
      out.write("            choose a quiz:\r\n");
      out.write("        </label>\r\n");
      out.write("        <select name=\"quizzes\" id=\"quizzes\">\r\n");
      out.write("            ");

                /*
                int id2 = uDao.getUserIdByName((String)session.getAttribute("currentUser"));
                List<String> list2 = historyDao.forChallenge(id2);
                for (String s : list2){
                    String output = "<option value='" + s + "'>" + s + "</option>";
                    out.print(output);
                }
                */
            
      out.write("\r\n");
      out.write("        </select>\r\n");
      out.write("        <input class=\"button button7\" type=\"submit\" name=\"button\" value=\"challenge\">\r\n");
      out.write("        <input class=\"button button6\" type=\"button\" value=\"cancel\" onclick=hide(\"challenge_div\")>\r\n");
      out.write("    </form>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<input class=\"button button1\" type=button Value=request onclick=show(\"request_div\")>\r\n");
      out.write("<div id=request_div style=\"display: none\">\r\n");
      out.write("    <form action=\"MailServlet\" method=\"post\">\r\n");
      out.write("        to: <input type=\"text\" name=\"username\"> <br/>\r\n");
      out.write("        <input class=\"button button7\" type=\"submit\" name=\"button\" value=\"sendRequest\">\r\n");
      out.write("    </form>\r\n");
      out.write("    <input class=\"button button6\" type=\"button\" value=\"hide\" onclick=hide(\"request_div\")>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<input class=\"button button1\" type=button Value=compose onclick=show(\"id_div\")>\r\n");
      out.write("<div id=id_div style=\"display: none\">\r\n");
      out.write("    <form action=\"MailServlet\" method=\"post\">\r\n");
      out.write("        to: <input type=\"text\" name=\"username\"> <br/>\r\n");
      out.write("        message: <input type=\"text\" name=\"message\"> <br/>\r\n");
      out.write("        <input class=\"button button7\" type=\"submit\" name=\"button\" value=\"send\">\r\n");
      out.write("        <input class=\"button button6\" type=\"button\" value=\"cancel\" onclick=hide(\"id_div\")>\r\n");
      out.write("    </form>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<input class=\"button button1\" type=button Value=inbox onclick=show(\"inbox_div\")>\r\n");
      out.write("<div id=inbox_div style=\"display: none\">\r\n");
      out.write("    <form action=\"MailServlet\" method=\"post\">\r\n");
      out.write("        ");

            for (Mail mail : mails) {
                String senderName = userDao.getUser(mail.getSenderId()).getUserName();
                String output = "<li><a href=\"readMessage.jsp?id=" + mail.getMailId() + "&sender=" + senderName + "\">" + "from: " + senderName + "</a></li>";
                out.print(output);
            }
        
      out.write("\r\n");
      out.write("        <input class=\"button button6\"  type=\"button\" value=\"cancel\" onclick=hide(\"inbox_div\")>\r\n");
      out.write("    </form>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<form method=\"POST\" action=\"/IndexServlet\">\r\n");
      out.write("    <input type=\"submit\" value=\"Log Out\" class=\"logOutButton\">\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
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

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /WEB-INF/views/UserPage.jsp(65,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error != null}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("            <p style=\"color:red;\"> ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write(" </p>\r\n");
        out.write("        ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }
}
