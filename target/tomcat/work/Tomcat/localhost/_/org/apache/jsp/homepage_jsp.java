/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-07-23 20:43:15 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import ProfilePackage.Mail;
import ProfilePackage.MailSqlDao;
import UserPackage.UserSqlDao;

public final class homepage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <p>\r\n");
      out.write("        ");

            MailSqlDao mailDao = new MailSqlDao();
            UserSqlDao userDao = new UserSqlDao();
            out.print(request.getParameter("username"));
            ArrayList<Mail> mails = mailDao.getMails(3);
        
      out.write("\r\n");
      out.write("    </p>\r\n");
      out.write("    <script language=javascript>\r\n");
      out.write("        function hide(id) {\r\n");
      out.write("            var div_ref = document.getElementById(id);\r\n");
      out.write("            div_ref.style.display = \"none\";\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        function show(id) {\r\n");
      out.write("            var div_ref = document.getElementById(id);\r\n");
      out.write("            div_ref.style.display = \"block\";\r\n");
      out.write("        }\r\n");
      out.write("    </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div name=frm_test id=frm_test>\r\n");
      out.write("    <input type=button Value=compose onclick=show(\"id_div\")>\r\n");
      out.write("    <div id=id_div style=\"display: none\">\r\n");
      out.write("        <form action=\"MailServlet\" method=\"post\">\r\n");
      out.write("            to: <input type=\"text\" name=\"username\"> <br/>\r\n");
      out.write("            message: <input type=\"text\" name=\"message\"> <br/>\r\n");
      out.write("            <input type=\"submit\" name=\"button\" value=\"send\">\r\n");
      out.write("            <input type=\"button\" value=\"cancel\" onclick=hide(\"id_div\")>\r\n");
      out.write("        </form>\r\n");
      out.write("    </div>\r\n");
      out.write("    <input type=button Value=inbox onclick=show(\"inbox_div\")>\r\n");
      out.write("    <div id=inbox_div>\r\n");
      out.write("        <form action=\"MailServlet\" method=\"post\">\r\n");
      out.write("            ");

                for (Mail mail : mails) {
                    String senderName = userDao.getUser(mail.getSenderId()).getUserName();
                    String output = "<li><a href=\"readMessage.jsp?id=" + mail.getMailId() + "&sender=" + senderName + "\">" + "from: " + senderName + "</a></li>";
                    out.print(output);
                }
            
      out.write("\r\n");
      out.write("        </form>\r\n");
      out.write("    </div>\r\n");
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
