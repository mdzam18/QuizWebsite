/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-07-29 22:01:30 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

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
import java.sql.SQLException;

public final class UserPage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Welcome</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        function hide(id) {\r\n");
      out.write("            let div_ref = document.getElementById(id);\r\n");
      out.write("            div_ref.style.display = \"none\";\r\n");
      out.write("        }\r\n");
      out.write("        function show(id) {\r\n");
      out.write("            let div_ref = document.getElementById(id);\r\n");
      out.write("            div_ref.style.display = \"block\";\r\n");
      out.write("        }\r\n");
      out.write("        function toCreateQuizPage() {\r\n");
      out.write("            window.location.href = \"createQuiz.jsp\";\r\n");
      out.write("        }\r\n");
      out.write("    </script>\r\n");
      out.write("\r\n");
      out.write("    ");

        UserSqlDao uDao = new UserSqlDao();
        FriendsSqlDao fDao = new FriendsSqlDao();
        QuizSqlDao qDao = new QuizSqlDao();
    
      out.write("\r\n");
      out.write("    <div class= \"SearchBox\">\r\n");
      out.write("        <form action= \"UserServlet\" method=\"POST\">\r\n");
      out.write("            <input class= \"search-txt\" type = \"text\" name = \"username\"  id = \"id\" placeholder= \"Type To Search\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${username}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" value = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("            <input type=\"submit\" name=\"button\" value=\"search\">\r\n");
      out.write("            <a class= \"search-btn\" href = \"#\"></a>\r\n");
      out.write("        </form>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"title\">Welcome ");
      out.print( request.getParameter("username"));
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"Profile\">\r\n");
      out.write("        <h1> User Name: ");
      out.print( request.getParameter("username"));
      out.write(" </h1>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <button style=\"font-size: 25px;\" onclick=\"toCreateQuizPage()\">Create New Quiz</button>\r\n");
      out.write("\r\n");
      out.write("    <p><button type = \"button\" Value=\"friends\" onclick=show(\"friends_id\")>Friends</button></p>\r\n");
      out.write("    <div id = \"friends_id\" style=\"display: none\">\r\n");
      out.write("        ");

            String name = request.getParameter("username");
            User user = uDao.getUser(uDao.getUserIdByName(name));
            List<User> list = fDao.getFriends(user);
            for(User user2 : list){
                out.println("<li><a href=\"ProfilePage.jsp?id=" +  user2.getUserId() + "\">" + user2.getUserName() + "</a> </li>" );
            }
        
      out.write("\r\n");
      out.write("        <input type=\"button\" value=\"close\" onclick=hide(\"friends_id\")>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <p><button type = \"button\" Value= \"sentRequests\" onclick= show(\"sentRequest_id\")>Sent Requests</button></p>\r\n");
      out.write("    <div id = \"sentRequest_id\" style=\"display: none\">\r\n");
      out.write("        ");

            name = request.getParameter("username");
            user = uDao.getUser(uDao.getUserIdByName(name));
            list = fDao.getSentRequests(user);
            for(User user2 : list){
                out.println("<li><a href=\"ProfilePage.jsp?id=" +  user2.getUserId() + "\">" + user2.getUserName() + "</a> </li>" );
            }
        
      out.write("\r\n");
      out.write("        <input type=\"button\" value=\"close\" onclick=hide(\"sentRequest_id\")>\r\n");
      out.write("        <input type=\"hidden\" name=\"username\" VALUE= ");
      out.print(request.getParameter("username"));
      out.write(">\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <p><button type = \"button\" Value= \"request\" onclick= show(\"requests_id\")>Friend Requests</button></p>\r\n");
      out.write("    <div id = \"requests_id\" style=\"display: none\">\r\n");
      out.write("        ");

            name = request.getParameter("username");
            user = uDao.getUser(uDao.getUserIdByName(name));
            list = fDao.getReceivedRequests(user);
            for(User user2 : list){
                out.println("<li><a href=\"ProfilePage.jsp?id=" +  user2.getUserId() + "\">" + user2.getUserName() + "</a> </li>" );
            }
        
      out.write("\r\n");
      out.write("        <input type=\"button\" value=\"close\" onclick=hide(\"requests_id\")>\r\n");
      out.write("        <input type=\"hidden\" name=\"username\" VALUE= ");
      out.print(request.getParameter("username"));
      out.write(">\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <form action= UserServlet method=\"POST\">\r\n");
      out.write("     <input type=\"submit\" name=\"button\" value=\"delete\">\r\n");
      out.write("     <input type=\"hidden\" name=\"username\" VALUE= ");
      out.print(request.getParameter("username"));
      out.write(">\r\n");
      out.write("    </form>\r\n");
      out.write("\r\n");
      out.write("    <p> <button value = \"popular quizzes\" onclick= show(\"popular_id\")>popular quizzes</button></p>\r\n");
      out.write("    <div id = \"popular_id\" style=\"display: none\">\r\n");
      out.write("        ");

            /* List<Quiz> popularQuizzes = qDao.getPopularQuizzes();
             for(Quiz quiz: popularQuizzes){
                 out.println("description: "+ quiz.getDescription() + " category: " + quiz.getCategory());
             }
             */
        
      out.write("\r\n");
      out.write("        <input type=\"button\" value=\"close\" onclick=hide(\"popular_id\")>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <p> <button value = \"recently quizzes\" onclick= show(\"recently_id\")>recently created quizzes</button></p>\r\n");
      out.write("    <div id = \"recently quizzes\" style=\"display: none\">\r\n");
      out.write("        ");

            /*
            List<Quiz> recentlyCreated = qDao.getRecentlyCreatedQuizzes();
            for(Quiz quiz: recentlyCreated){
                out.println("description: "+ quiz.getDescription() + " category: " + quiz.getCategory());
            }
            */
        
      out.write("\r\n");
      out.write("        <input type=\"button\" value=\"close\" onclick=hide(\"recently_id\")>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <p> <button value = \"recently taken quizzes\" onclick= show(\"recentlyTaken_id\")>recently taken quizzes</button></p>\r\n");
      out.write("    <div id = \"recently taken quizzes\" style=\"display: none\">\r\n");
      out.write("        ");

            /*
              name = request.getParameter("username");
              user = uDao.getUser(uDao.getUserIdByName(name));
              List <Quiz> recentlyTaken = uDao.getRecentlyTakenQuizzes(user);
              for(Quiz quiz: recentlyTaken){
                  out.println("description: "+ quiz.getDescription() + " category: " + quiz.getCategory());
              }
              */
        
      out.write("\r\n");
      out.write("        <input type=\"button\" value=\"close\" onclick=hide(\"recentlyTaken_id\")>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <p> <button value = \"recently created quizzes\" onclick= show(\"recentlyCreated_id\")>recently created quizzes</button></p>\r\n");
      out.write("    <div id = \"recently taken quizzes\" style=\"display: none\">\r\n");
      out.write("        ");

        //                name = request.getParameter("username");
        //                List <Quiz> recentlyCreated = null;
        //                try {
        //                    recentlyCreated = qDao.getQuizzesForUser(uDao.getUserIdByName(name));
        //                } catch (SQLException throwables) {
        //                    throwables.printStackTrace();
        //                }
        //                List<Quiz> sortedRecentlyCreated = qDao.sortQuizzesByDate(recentlyCreated);
        //                int i = 0;
        //                for(Quiz quiz: sortedRecentlyCreated){
        //                    out.println("description: "+ quiz.getDescription() + " category: " + quiz.getCategory());
        //                    i++;
        //                    if (i >= 5) break;
        //                }
        
      out.write("\r\n");
      out.write("        <input type=\"button\" value=\"close\" onclick=hide(\"recentlyCreated_id\")>\r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
