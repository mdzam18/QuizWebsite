/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-08-01 10:34:08 UTC
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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<style>\n");
      out.write("    body, ul, li, h1, h2, a{\n");
      out.write("        margin: 0;\n");
      out.write("        padding: 0;\n");
      out.write("        font-family: arial;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /*table design*/\n");
      out.write("\n");
      out.write("    .wrapper {\n");
      out.write("        margin: 60px auto;\n");
      out.write("        padding: 20px;\n");
      out.write("        max-width: 800px;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .table {\n");
      out.write("        margin: 0 0 40px 0;\n");
      out.write("        width: 100%;\n");
      out.write("        box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);\n");
      out.write("        display: table;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .row {\n");
      out.write("        display: table-row;\n");
      out.write("        background: #f6f6f6;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .row.header {\n");
      out.write("        font-weight: bold;\n");
      out.write("        font-size: 23px;\n");
      out.write("        color: #ffffff;\n");
      out.write("        background: palevioletred;\n");
      out.write("        padding: 0;\n");
      out.write("        height: 6px;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .row.yellow{\n");
      out.write("        background: darkgoldenrod;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .row.green {\n");
      out.write("        background: #27ae60;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .row.purple {\n");
      out.write("        background: purple;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .row.blue{\n");
      out.write("        background: mediumblue;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .row .cell {\n");
      out.write("        font-size: 25px;\n");
      out.write("        margin-bottom: 20px;\n");
      out.write("        text-align: center;\n");
      out.write("        padding: 30px 30px;\n");
      out.write("        display: table-cell;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .row.header .cell:before {\n");
      out.write("        margin-bottom: 3px;\n");
      out.write("        content: attr(data-title);\n");
      out.write("        min-width: 98px;\n");
      out.write("        /* min-height: 20px; */\n");
      out.write("        font-size: 10px;\n");
      out.write("        line-height: 10px;\n");
      out.write("        font-weight: bold;\n");
      out.write("        text-transform: uppercase;\n");
      out.write("        /* color: #969696; */\n");
      out.write("        display: block;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /*website design*/\n");
      out.write("\n");
      out.write("    header{\n");
      out.write("        background-color: green;\n");
      out.write("        padding: 20px;\n");
      out.write("        text-align: center;\n");
      out.write("        position: fixed;\n");
      out.write("        width: 100%;\n");
      out.write("        z-index: 1;\n");
      out.write("        top: 0;\n");
      out.write("        left: 0;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    header h1{\n");
      out.write("        color: white;\n");
      out.write("        border: 8px solid white;\n");
      out.write("        padding: 6px 12px;\n");
      out.write("        display: inline-block;\n");
      out.write("        border-radius: 12px;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .banner{\n");
      out.write("        position: relative;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .banner img{\n");
      out.write("        max-width: 100%;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .banner .welcome{\n");
      out.write("\n");
      out.write("        background-color: #FEB614;\n");
      out.write("        color: white;\n");
      out.write("        padding: 30px;\n");
      out.write("        position: absolute;\n");
      out.write("        right: 0;\n");
      out.write("        top: 70%;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .banner h2{\n");
      out.write("        font-size: 74px;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .banner h2 span{\n");
      out.write("        font-size: 1.3em;\n");
      out.write("        /* font-size: 90px; */\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    nav{\n");
      out.write("        background-color: #F4F4F4;\n");
      out.write("        padding: 20px;\n");
      out.write("        position: sticky;\n");
      out.write("        top: 106px\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    nav ul{\n");
      out.write("        white-space: nowrap;\n");
      out.write("        max-width: 1200px;\n");
      out.write("        margin: 0 auto;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    nav li{\n");
      out.write("        width: 25%;\n");
      out.write("        display: inline-block;\n");
      out.write("        font-size: 24px;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    nav li a{\n");
      out.write("        text-decoration: none;\n");
      out.write("        color: #4B4B4B;\n");
      out.write("\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    footer{\n");
      out.write("        background: green;\n");
      out.write("        color: white;\n");
      out.write("        padding: 10px;\n");
      out.write("        text-align: center;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* Pseudo Classes */\n");
      out.write("\n");
      out.write("    nav li a:hover{\n");
      out.write("        color: red;\n");
      out.write("        text-decoration: underline;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    section.join p::first-letter{\n");
      out.write("        font-size: 1.5em;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    p::selection{\n");
      out.write("        background-color: #F63232;\n");
      out.write("        color: white;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* buttons design */\n");
      out.write("\n");
      out.write("    .centered {\n");
      out.write("        margin:50px auto;\n");
      out.write("        text-align:center;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .button{\n");
      out.write("        width: 100px;\n");
      out.write("        height: 35px;\n");
      out.write("        border-radius: 10%;\n");
      out.write("        font-size: 18px;\n");
      out.write("        cursor: pointer;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .button:hover{\n");
      out.write("        background-color: #eee;\n");
      out.write("        color: #555;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .button:active{\n");
      out.write("        background: #e9e9e9;\n");
      out.write("        position: relative;\n");
      out.write("        top: 1px;\n");
      out.write("        text-shadow: none;\n");
      out.write("        -moz-box-shadow: 0 1px 1px rgba(0, 0, 0, .3) inset;\n");
      out.write("        -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .3) inset;\n");
      out.write("        box-shadow: 0 1px 1px rgba(0, 0, 0, .3) inset;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .button.red, .button.blue {\n");
      out.write("        color: #fff;\n");
      out.write("        text-shadow: 0 1px 0 rgba(0,0,0,.2);\n");
      out.write("\n");
      out.write("        background-image: -webkit-gradient(linear, left top, left bottom, from(rgba(255,255,255,.3)), to(rgba(255,255,255,0)));\n");
      out.write("        background-image: -webkit-linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));\n");
      out.write("        background-image: -moz-linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));\n");
      out.write("        background-image: -ms-linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));\n");
      out.write("        background-image: -o-linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));\n");
      out.write("        background-image: linear-gradient(top, rgba(255,255,255,.3), rgba(255,255,255,0));\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .button.red{\n");
      out.write("        background-color: #ca3535;\n");
      out.write("        border-color: #c43c35;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .button.red:hover{\n");
      out.write("        background-color: #ee5f5b;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .button.red:active{\n");
      out.write("        background: #c43c35;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .button.blue{\n");
      out.write("        background-color: #269CE9;\n");
      out.write("        border-color: #269CE9;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .button.blue:hover{\n");
      out.write("        background-color: #70B9E8;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("\n");
      out.write("    /*Statistics page*/\n");
      out.write("\n");
      out.write("    .id_field {\n");
      out.write("        margin: 20px 0;\n");
      out.write("        padding: 10px 20px;\n");
      out.write("        font-size: 24px;\n");
      out.write("        border-radius: 28px;\n");
      out.write("        border: 4px solid white;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("\n");
      out.write("    .id_field:focus{\n");
      out.write("        outline: none; /*remove blue outline from the input */\n");
      out.write("        border: 4px solid #4b4b4b;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .id_field:valid{\n");
      out.write("        border: 4px solid #71d300;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    input[type=submit] {\n");
      out.write("        background-color: #4CAF50;\n");
      out.write("        color: white;\n");
      out.write("        font-size: 18px;\n");
      out.write("        padding: 14px 20px;\n");
      out.write("        margin: 8px 0;\n");
      out.write("        border: none;\n");
      out.write("        border-radius: 4px;\n");
      out.write("        cursor: pointer;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    input[type=submit]:hover{\n");
      out.write("        background-color: rgb(70, 160, 73);\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    ol.scores li{\n");
      out.write("        /* text-align: center; */\n");
      out.write("        font-size: 24px;\n");
      out.write("        list-style-type: square;\n");
      out.write("\n");
      out.write("        text-shadow: 2px 2px #c28686\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* display: flex */\n");
      out.write("    .score-list {\n");
      out.write("        display: flex;\n");
      out.write("        justify-content: center;\n");
      out.write("    }\n");
      out.write("</style>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    ");
      out.write("\n");
      out.write("    <title>Administrator Page</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<header>\n");
      out.write("    <h1>Administrator</h1>\n");
      out.write("</header>\n");
      out.write("<section class=\"banner\">\n");
      out.write("    <img src=\"../../resources/images/nature.jpg\">\n");
      out.write("    <div class=\"welcome\">\n");
      out.write("        <h2>Welcome to <br><span>Administrator Page</span></h2>\n");
      out.write("    </div>\n");
      out.write("</section>\n");
      out.write("<nav class=\"main-nav\">\n");
      out.write("    <ul>\n");
      out.write("        <li><a href=\"/admin/admins\" class=\"admins\">Admins</a></li>\n");
      out.write("        <li><a href=\"/admin/users\">Users</a></li>\n");
      out.write("        <li><a href=\"/admin/quizzes\">Quizzes</a></li>\n");
      out.write("        <li><a href=\"/admin/history\">History</a></li>\n");
      out.write("        <li><a href=\"/admin/statistics\">Statistics</a></li>\n");
      out.write("    </ul>\n");
      out.write("</nav>\n");
      out.write("<div class=\"wrapper\">\n");
      out.write("\n");
      out.write("    <div class=\"table\">\n");
      out.write("\n");
      out.write("        <div class=\"row header purple\">\n");
      out.write("            <div class=\"cell\">\n");
      out.write("                Id\n");
      out.write("            </div>\n");
      out.write("            <div class=\"cell\">\n");
      out.write("                Description\n");
      out.write("            </div>\n");
      out.write("            <div class=\"cell\">\n");
      out.write("                Category\n");
      out.write("            </div>\n");
      out.write("            <div class=\"cell\">\n");
      out.write("                Creator Id\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<footer>\n");
      out.write("    <p class=\"Copyright\">Copyright 2020 QuizWebsite</p>\n");
      out.write("</footer>\n");
      out.write("</body>\n");
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
    // /WEB-INF/views/quizzes.jsp(331,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("quiz");
    // /WEB-INF/views/quizzes.jsp(331,8) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/quizzes.jsp(331,8) '${quizzes}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${quizzes}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("            <div class=\"row\">\n");
          out.write("                <div class=\"cell\" data-title=\"Id\">\n");
          out.write("                        ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${quiz.quizId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\n");
          out.write("                </div>\n");
          out.write("                <div class=\"cell\" data-title=\"Description\">\n");
          out.write("                        ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${quiz.description}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\n");
          out.write("                </div>\n");
          out.write("                <div class=\"cell\" data-title=\"Category\">\n");
          out.write("                        ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${quiz.category}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\n");
          out.write("                </div>\n");
          out.write("                <div class=\"cell\" data-title=\"Creator Id\">\n");
          out.write("                        ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${quiz.creatorId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\n");
          out.write("                </div>\n");
          out.write("                <div class=\"centered\">\n");
          out.write("                    <form action=\"/admin/quizzes?id=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${quiz.quizId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\" method=\"POST\">\n");
          out.write("                        <button class=\"small red button\" type=\"submit\">Delete</button>\n");
          out.write("                    </form>\n");
          out.write("                </div>\n");
          out.write("            </div>\n");
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
