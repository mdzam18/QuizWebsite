/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-08-09 18:45:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class quizzes_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
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
      out.write("<style>\r\n");
      out.write("    body, ul, li, h1, h2, a{\r\n");
      out.write("        margin: 0;\r\n");
      out.write("        padding: 0;\r\n");
      out.write("        font-family: arial;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    /*table design*/\r\n");
      out.write("\r\n");
      out.write("    .wrapper {\r\n");
      out.write("        margin: 60px auto;\r\n");
      out.write("        padding: 20px;\r\n");
      out.write("        max-width: 800px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .table {\r\n");
      out.write("        margin: 0 0 40px 0;\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);\r\n");
      out.write("        display: table;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .row {\r\n");
      out.write("        display: table-row;\r\n");
      out.write("        background: #f6f6f6;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .row.header {\r\n");
      out.write("        font-weight: bold;\r\n");
      out.write("        font-size: 23px;\r\n");
      out.write("        color: #ffffff;\r\n");
      out.write("        background: palevioletred;\r\n");
      out.write("        padding: 0;\r\n");
      out.write("        height: 6px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .row.yellow{\r\n");
      out.write("        background: darkgoldenrod;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .row.green {\r\n");
      out.write("        background: #27ae60;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .row.purple {\r\n");
      out.write("        background: purple;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .row.blue{\r\n");
      out.write("        background: mediumblue;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .row .cell {\r\n");
      out.write("        font-size: 25px;\r\n");
      out.write("        margin-bottom: 20px;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("        padding: 30px 30px;\r\n");
      out.write("        display: table-cell;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .row.header .cell:before {\r\n");
      out.write("        margin-bottom: 3px;\r\n");
      out.write("        content: attr(data-title);\r\n");
      out.write("        min-width: 98px;\r\n");
      out.write("        /* min-height: 20px; */\r\n");
      out.write("        font-size: 10px;\r\n");
      out.write("        line-height: 10px;\r\n");
      out.write("        font-weight: bold;\r\n");
      out.write("        text-transform: uppercase;\r\n");
      out.write("        /* color: #969696; */\r\n");
      out.write("        display: block;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    /*website design*/\r\n");
      out.write("\r\n");
      out.write("    header{\r\n");
      out.write("        background-color: green;\r\n");
      out.write("        padding: 20px;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("        position: fixed;\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        z-index: 1;\r\n");
      out.write("        top: 0;\r\n");
      out.write("        left: 0;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    header h1{\r\n");
      out.write("        color: white;\r\n");
      out.write("        border: 8px solid white;\r\n");
      out.write("        padding: 6px 12px;\r\n");
      out.write("        display: inline-block;\r\n");
      out.write("        border-radius: 12px;\r\n");
      out.write("        line-height: 1;\r\n");
      out.write("        border-bottom: 6px solid #e5e5e5;\r\n");
      out.write("        font-family: \"Playfair Display\", Georgia, \"Times New Roman\", serif;\r\n");
      out.write("        font-size: 2.25rem;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .banner{\r\n");
      out.write("        position: relative;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .banner img{\r\n");
      out.write("        max-width: 100%;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .banner .welcome{\r\n");
      out.write("        text-align: center;\r\n");
      out.write("        background-color: #FEB614;\r\n");
      out.write("        color: white;\r\n");
      out.write("        padding: 30px;\r\n");
      out.write("        position: absolute;\r\n");
      out.write("        right: 0;\r\n");
      out.write("        top: 70%;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .banner h2{\r\n");
      out.write("        font-size: 74px;\r\n");
      out.write("        font-family: \"Lato Heavy\";\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .banner h2 span{\r\n");
      out.write("        font-size: 1.3em;\r\n");
      out.write("        /* font-size: 90px; */\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    nav{\r\n");
      out.write("        background-color: #F4F4F4;\r\n");
      out.write("        padding: 20px;\r\n");
      out.write("        position: sticky;\r\n");
      out.write("        top: 106px\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    nav ul{\r\n");
      out.write("        white-space: nowrap;\r\n");
      out.write("        max-width: 1200px;\r\n");
      out.write("        margin: 0 auto;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    nav li{\r\n");
      out.write("        width: 25%;\r\n");
      out.write("        display: inline-block;\r\n");
      out.write("        font-size: 24px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    nav li a{\r\n");
      out.write("        text-decoration: none;\r\n");
      out.write("        color: #4B4B4B;\r\n");
      out.write("\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    footer{\r\n");
      out.write("        background: green;\r\n");
      out.write("        color: white;\r\n");
      out.write("        padding: 10px;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    /* Pseudo Classes */\r\n");
      out.write("\r\n");
      out.write("    nav li a:hover{\r\n");
      out.write("        color: red;\r\n");
      out.write("        text-decoration: underline;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    section.join p::first-letter{\r\n");
      out.write("        font-size: 1.5em;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    p::selection{\r\n");
      out.write("        background-color: #F63232;\r\n");
      out.write("        color: white;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    /* buttons design */\r\n");
      out.write("\r\n");
      out.write("    .centered {\r\n");
      out.write("        margin:50px auto;\r\n");
      out.write("        text-align:center;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .button{\r\n");
      out.write("        width: 100px;\r\n");
      out.write("        height: 35px;\r\n");
      out.write("        border-radius: 10%;\r\n");
      out.write("        font-size: 18px;\r\n");
      out.write("        cursor: pointer;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .button:hover{\r\n");
      out.write("        background-color: #eee;\r\n");
      out.write("        color: #555;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .button:active{\r\n");
      out.write("        background: #e9e9e9;\r\n");
      out.write("        position: relative;\r\n");
      out.write("        top: 1px;\r\n");
      out.write("        text-shadow: none;\r\n");
      out.write("        -moz-box-shadow: 0 1px 1px rgba(0, 0, 0, .3) inset;\r\n");
      out.write("        -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .3) inset;\r\n");
      out.write("        box-shadow: 0 1px 1px rgba(0, 0, 0, .3) inset;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .button.red, .button.blue {\r\n");
      out.write("        color: #fff;\r\n");
      out.write("        text-shadow: 0 1px 0 rgba(0,0,0,.2);\r\n");
      out.write("\r\n");
      out.write("        background-image: -webkit-gradient(linear, left top, left bottom, from(rgba(255,255,255,.3)), to(rgba(255,255,255,0)));\r\n");
      out.write("        background-image: -webkit-linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));\r\n");
      out.write("        background-image: -moz-linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));\r\n");
      out.write("        background-image: -ms-linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));\r\n");
      out.write("        background-image: -o-linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));\r\n");
      out.write("        background-image: linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .button.red{\r\n");
      out.write("        background-color: #ca3535;\r\n");
      out.write("        border-color: #c43c35;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .button.red:hover{\r\n");
      out.write("        background-color: #ee5f5b;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .button.red:active{\r\n");
      out.write("        background: #c43c35;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .button.blue{\r\n");
      out.write("        background-color: #269CE9;\r\n");
      out.write("        border-color: #269CE9;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .button.blue:hover{\r\n");
      out.write("        background-color: #70B9E8;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    /*Statistics page*/\r\n");
      out.write("\r\n");
      out.write("    .id_field {\r\n");
      out.write("        margin: 20px 0;\r\n");
      out.write("        padding: 10px 20px;\r\n");
      out.write("        font-size: 24px;\r\n");
      out.write("        border-radius: 28px;\r\n");
      out.write("        border: 4px solid white;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    .id_field:focus{\r\n");
      out.write("        outline: none; /*remove blue outline from the input */\r\n");
      out.write("        border: 4px solid #4b4b4b;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .id_field:valid{\r\n");
      out.write("        border: 4px solid #71d300;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    input[type=submit] {\r\n");
      out.write("        background-color: #4CAF50;\r\n");
      out.write("        color: white;\r\n");
      out.write("        font-size: 18px;\r\n");
      out.write("        padding: 14px 20px;\r\n");
      out.write("        margin: 8px 0;\r\n");
      out.write("        border: none;\r\n");
      out.write("        border-radius: 4px;\r\n");
      out.write("        cursor: pointer;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    input[type=submit]:hover{\r\n");
      out.write("        background-color: rgb(70, 160, 73);\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    ol.scores li{\r\n");
      out.write("        /* text-align: center; */\r\n");
      out.write("        font-size: 24px;\r\n");
      out.write("        list-style-type: square;\r\n");
      out.write("\r\n");
      out.write("        text-shadow: 2px 2px #c28686\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    /* display: flex */\r\n");
      out.write("    .score-list {\r\n");
      out.write("        display: flex;\r\n");
      out.write("        justify-content: center;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .user a{\r\n");
      out.write("        position: absolute;\r\n");
      out.write("        top: 12px;\r\n");
      out.write("        right: 55px;\r\n");
      out.write("        font-size: 24px;\r\n");
      out.write("        color: yellow;\r\n");
      out.write("        font-weight: bold;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .user a:link {\r\n");
      out.write("        text-decoration: none;\r\n");
      out.write("    }\r\n");
      out.write("    .user a:hover {\r\n");
      out.write("        text-decoration: underline;\r\n");
      out.write("        color: red;\r\n");
      out.write("    }\r\n");
      out.write("</style>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    <title>Administrator Page</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<header>\r\n");
      out.write("    <div class=\"user\">\r\n");
      out.write("        <a href=/UserServlet>Go as User</a>\r\n");
      out.write("    </div>\r\n");
      out.write("    <h1>Administrator</h1>\r\n");
      out.write("</header>\r\n");
      out.write("<section class=\"banner\">\r\n");
      out.write("    <img src=\"../../resources/images/nature.jpg\">\r\n");
      out.write("    <div class=\"welcome\">\r\n");
      out.write("        <h2>Welcome to <br><span>Administrator Page</span></h2>\r\n");
      out.write("    </div>\r\n");
      out.write("</section>\r\n");
      out.write("<nav class=\"main-nav\">\r\n");
      out.write("    <ul>\r\n");
      out.write("        <li><a href=\"/admin/admins\" class=\"admins\">Admins</a></li>\r\n");
      out.write("        <li><a href=\"/admin/users\">Users</a></li>\r\n");
      out.write("        <li><a href=\"/admin/quizzes\">Quizzes</a></li>\r\n");
      out.write("        <li><a href=\"/admin/history\">History</a></li>\r\n");
      out.write("        <li><a href=\"/admin/statistics\">Statistics</a></li>\r\n");
      out.write("    </ul>\r\n");
      out.write("</nav>\r\n");
      out.write("<div class=\"wrapper\">\r\n");
      out.write("\r\n");
      out.write("    <div class=\"table\">\r\n");
      out.write("\r\n");
      out.write("        <div class=\"row header purple\">\r\n");
      out.write("            <div class=\"cell\">\r\n");
      out.write("                Id\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"cell\">\r\n");
      out.write("                Description\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"cell\">\r\n");
      out.write("                Category\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"cell\">\r\n");
      out.write("                Creator Id\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<footer>\r\n");
      out.write("    <p class=\"Copyright\">Copyright 2020 QuizWebsite</p>\r\n");
      out.write("</footer>\r\n");
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

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/views/quizzes.jsp(356,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("quiz");
    // /WEB-INF/views/quizzes.jsp(356,8) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/quizzes.jsp(356,8) '${quizzes}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${quizzes}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("            <div class=\"row\">\r\n");
          out.write("                <div class=\"cell\" data-title=\"Id\">\r\n");
          out.write("                        ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${quiz.quizId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\r\n");
          out.write("                </div>\r\n");
          out.write("                <div class=\"cell\" data-title=\"Description\">\r\n");
          out.write("                        ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${quiz.description}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\r\n");
          out.write("                </div>\r\n");
          out.write("                <div class=\"cell\" data-title=\"Category\">\r\n");
          out.write("                        ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${quiz.category}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\r\n");
          out.write("                </div>\r\n");
          out.write("                <div class=\"cell\" data-title=\"Creator Id\">\r\n");
          out.write("                        ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${quiz.creatorId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\r\n");
          out.write("                </div>\r\n");
          out.write("                <div class=\"centered\">\r\n");
          out.write("                    <form action=\"/admin/quizzes\" method=\"POST\">\r\n");
          out.write("                        <input name=\"quizId\" type=\"hidden\" value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${quiz.quizId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\">\r\n");
          out.write("                        <button class=\"small red button\" type=\"submit\">Delete</button>\r\n");
          out.write("                    </form>\r\n");
          out.write("                </div>\r\n");
          out.write("            </div>\r\n");
          out.write("        ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }
}
