/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-08-10 18:25:08 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class welcome_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Welcome!</title>\r\n");
      out.write("\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("\r\n");
      out.write("    <!-- Login/Registraton Idea Rights: https://codepen.io/FlorinPop17/pen/vPKWjd -->\r\n");
      out.write("\r\n");
      out.write("    <!-- Styles -->\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles/welcome_style1.css\">\r\n");
      out.write("    <!-- Google Fonts -->\r\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("    <!-- JQuery -->\r\n");
      out.write("    <script type=\"text/javascript\" src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n");
      out.write("    <!-- JavaScript -->\r\n");
      out.write("    <script src=\"js_code/welcome_js1.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<!--<div id=\"mainOver\"></div>-->\r\n");
      out.write("\r\n");
      out.write("<div id=\"contentPlace\">\r\n");
      out.write("    <div id=\"loginSpace\">\r\n");
      out.write("        <span class=\"logicSpace_login\">Log In</span>\r\n");
      out.write("\r\n");
      out.write("        <!-- Login Form -->\r\n");
      out.write("        <form id=\"login_success\" method=\"POST\" action=\"/LoginServlet\" style=\"display: none\">\r\n");
      out.write("            <input type=\"hidden\" id=\"login_success_username\" name=\"username\" value=\"\">\r\n");
      out.write("            <input type=\"hidden\" id=\"login_success_password\" name=\"password\" value=\"\">\r\n");
      out.write("            <input type=\"hidden\" id=\"login_success_remember\" name=\"remember\" value=\"\">\r\n");
      out.write("        </form>\r\n");
      out.write("        <p id=\"username_input_p\">\r\n");
      out.write("            <input id=\"username_input\" name=\"username\" type=\"text\" placeholder=\"User Name\">\r\n");
      out.write("        </p>\r\n");
      out.write("        <p id=\"password_input_p\">\r\n");
      out.write("            <input id=\"password_input\" name=\"password\" type=\"password\" placeholder=\"Password\">\r\n");
      out.write("        </p>\r\n");
      out.write("        <p id=\"rememberUser_p\">\r\n");
      out.write("            <div id=\"rememberUser_div\">\r\n");
      out.write("                <label for=\"rememberUser\">Remember Me</label>\r\n");
      out.write("                <input id=\"rememberUser\" name=\"rememberUser\" type=\"checkbox\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </p>\r\n");
      out.write("        <p id=\"login_error_output\"><!-- Error Output Here --></p>\r\n");
      out.write("        <p id=\"login_button_p\">\r\n");
      out.write("            <button id=\"login_button\">Log In</button>\r\n");
      out.write("        </p>\r\n");
      out.write("        <!--<p id=\"forgotpassword_p\">\r\n");
      out.write("            Concept\r\n");
      out.write("            <span class=\"forgotpassword_span\">Forgot Password?</span>\r\n");
      out.write("        </p>-->\r\n");
      out.write("        <!-- Login Form -->\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div id=\"registerSpace\">\r\n");
      out.write("        <p class=\"registerSpaceAppName\">QuizWebsite</p>\r\n");
      out.write("        <p class=\"registerSpaceText\"><!-- Empty --></p>\r\n");
      out.write("        <p class=\"registerSpaceTextAbove\">\r\n");
      out.write("            Don't have an account? <strong>Just Create it!</strong>\r\n");
      out.write("        </p>\r\n");
      out.write("        <button onclick=\"scrollContentLeft()\" id=\"scrollRegistrationButton\">Sign Up</button>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div id=\"registrationContent\">\r\n");
      out.write("        <p class=\"registrationContent_p\">Create An Account </br> <strong>In Few Steps</strong></p>\r\n");
      out.write("\r\n");
      out.write("        <!-- Registration Form -->\r\n");
      out.write("        <form id=\"registration_success\" method=\"POST\" action=\"/RegistrationServlet\" style=\"display: none\">\r\n");
      out.write("            <input type=\"hidden\" id=\"registration_success_username\" name=\"username\" value=\"\">\r\n");
      out.write("            <input type=\"hidden\" id=\"registration_success_password\" name=\"password\" value=\"\">\r\n");
      out.write("        </form>\r\n");
      out.write("        <p id=\"usernameReg_p\">\r\n");
      out.write("            <input id=\"usernameReg_input\" name=\"username\" type=\"text\" placeholder=\"User Name\">\r\n");
      out.write("        </p>\r\n");
      out.write("        <p id=\"passwordReg_p\">\r\n");
      out.write("            <input id=\"passwordReg_input\" name=\"password\" type=\"password\" placeholder=\"Password\">\r\n");
      out.write("        </p>\r\n");
      out.write("        <p id=\"passwordRepReg_p\">\r\n");
      out.write("            <input id=\"passwordRepReg_input\" type=\"password\" placeholder=\"Confirm Password\">\r\n");
      out.write("        </p>\r\n");
      out.write("        <p id=\"registration_error_output\"><!-- Error Output Here --></p>\r\n");
      out.write("        <p id=\"signup_button_p\">\r\n");
      out.write("            <button id=\"signup_button\">Sign Up</button>\r\n");
      out.write("        </p>\r\n");
      out.write("        <!-- Registration Form -->\r\n");
      out.write("    </div>\r\n");
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
