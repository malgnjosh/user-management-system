/*
 * JSP generated by Resin-3.1.15 (built Mon, 13 Oct 2014 06:45:33 PDT)
 */

package _jsp._myweb;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import dao.*;
import malgnsoft.db.*;
import malgnsoft.util.*;

public class _init__jsp extends com.caucho.jsp.JavaPage
{
  private static final java.util.HashMap<String,java.lang.reflect.Method> _jsp_functionMap = new java.util.HashMap<String,java.lang.reflect.Method>();
  private boolean _caucho_isDead;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.WebApp _jsp_application = _caucho_getApplication();
    javax.servlet.ServletContext application = _jsp_application;
    com.caucho.jsp.PageContextImpl pageContext = _jsp_application.getJspApplicationContext().allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);
    javax.servlet.jsp.PageContext _jsp_parentContext = pageContext;
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    final javax.el.ELContext _jsp_env = pageContext.getELContext();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    response.setContentType("text/html");
    try {
      
    //
    String docRoot = Config.getDocRoot();
    String tplRoot = Config.getDocRoot() + "/myweb/html";

    Malgn m = new Malgn(request, response, out);

    Form f = new Form();
    f.setRequest(request);

    // \u00ec\u0084\u00b8\u00ec\u0085\u0098\u00ec\u009d\u00b4\u00ec\u009a\u00a9\u00ec\u008b\u009c
    // Auth auth = new Auth(request, response, session);
//    Auth auth = new Auth(request, response);
//    String userId = null;
//    if(auth.isValid()) { // \u00ec\u00bf\u00a0\u00ed\u0082\u00a4\u00ec\u0097\u0090 \u00ec\u00a0\u0080\u00ec\u009e\u00a5\u00eb\u0090\u009c \u00ec\u009d\u00b8\u00ec\u00a6\u009d\u00ed\u0082\u00a4\u00eb\u0082\u0098 \u00ec\u009d\u00b8\u00ec\u00a6\u009d\u00ec\u00a0\u0095\u00eb\u00b3\u00b4\u00ec\u009d\u0098 \u00eb\u00b3\u0080\u00ea\u00b2\u00bd \u00ec\u0098\u00a4\u00eb\u00a5\u0098 \u00ec\u0097\u00ac\u00eb\u00b6\u0080 \u00ec\u00b2\u00b4\u00ed\u0081\u00ac
//        userId = auth.getString("USER_ID");
//        m.p(userId);
//    }

    /*
    auth.domain = ".malgn.co.kr"; // 2\u00ec\u00b0\u00a8 \u00eb\u008f\u0084\u00eb\u00a9\u0094\u00ec\u009d\u00b8\u00ea\u00b0\u0084 \u00ec\u00bf\u00a0\u00ed\u0082\u00a4 \u00ec\u009d\u00b8\u00ec\u00a6\u009d\u00ec\u009d\u0084 \u00ea\u00b3\u00b5\u00ec\u009c\u00a0
    auth.keyName = "auth123"; // \u00ec\u00bf\u00a0\u00ed\u0082\u00a4\u00eb\u00b3\u0080\u00ec\u0088\u0098\u00eb\u00aa\u0085
    auth.validTime = 3600 * 8; // \u00ec\u009c\u00a0\u00ed\u009a\u00a8\u00ec\u008b\u009c\u00ea\u00b0\u0084(\u00ec\u00b4\u0088) validTime \u00ec\u009d\u00b4 \u00ec\u0084\u00b8\u00ec\u0085\u0098\u00ec\u009c\u00a0\u00ed\u009a\u00a8\u00ec\u008b\u009c\u00ea\u00b0\u0084\u00eb\u00b3\u00b4\u00eb\u008b\u00a4 \u00ed\u0081\u00ac\u00eb\u00a9\u00b4 \u00ec\u009c\u00a0\u00ed\u009a\u00a8\u00ec\u008b\u009c\u00ea\u00b0\u0084 \u00eb\u00a7\u008c\u00eb\u00a3\u008c\u00ec\u0099\u0080 \u00eb\u008f\u0099\u00ec\u008b\u009c\u00ec\u0097\u0090 \u00ec\u009d\u00b8\u00ec\u00a6\u009d \u00ed\u0095\u00b4\u00ec\u00a0\u009c -1 \u00eb\u0084\u00a3\u00ec\u009c\u00bc\u00eb\u00a9\u00b4 \u00eb\u00ac\u00b4\u00ed\u0095\u009c\u00eb\u008c\u0080\u00eb\u00a1\u009c
    */

    Page p = new Page(tplRoot);
    p.setRequest(request);
    p.setWriter(out);
    p.setPageContext(pageContext);


    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      _jsp_application.getJspApplicationContext().freePageContext(pageContext);
    }
  }

  private java.util.ArrayList _caucho_depends = new java.util.ArrayList();

  public java.util.ArrayList _caucho_getDependList()
  {
    return _caucho_depends;
  }

  public void _caucho_addDepend(com.caucho.vfs.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;
    if (com.caucho.server.util.CauchoSystem.getVersionId() != 6749855747778707107L)
      return true;
    for (int i = _caucho_depends.size() - 1; i >= 0; i--) {
      com.caucho.vfs.Dependency depend;
      depend = (com.caucho.vfs.Dependency) _caucho_depends.get(i);
      if (depend.isModified())
        return true;
    }
    return false;
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public java.util.HashMap<String,java.lang.reflect.Method> _caucho_getFunctionMap()
  {
    return _jsp_functionMap;
  }

  public void init(ServletConfig config)
    throws ServletException
  {
    com.caucho.server.webapp.WebApp webApp
      = (com.caucho.server.webapp.WebApp) config.getServletContext();
    super.init(config);
    com.caucho.jsp.TaglibManager manager = webApp.getJspApplicationContext().getTaglibManager();
    com.caucho.jsp.PageContextImpl pageContext = new com.caucho.jsp.PageContextImpl(webApp, this);
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.server.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("myweb/init.jsp"), -4732223883673291593L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }
}
