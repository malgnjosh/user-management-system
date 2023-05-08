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

public class _user_0list__jsp extends com.caucho.jsp.JavaPage
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
    response.setContentType("text/html; charset=utf-8");
    request.setCharacterEncoding("UTF-8");
    try {
      

    Malgn m = new Malgn(request, response, out);

    Form f = new Form();
    f.setRequest(request);

    Auth auth = new Auth(request, response);
    int userId = 0;
    String userSessionId = null;

    if(auth.isValid()) {
        userId = auth.getInt("USER_ID");
        userSessionId = auth.getString("SESSIONID");
    }

    //IP \ucc28\ub2e8
    String[] allowedIpList = {"127.0.0.1", "125.129.123.211", "106.244.224.183", "52.79.184.225"};
    String userIp = request.getRemoteAddr();
    boolean allowed = false;
    for(String s : allowedIpList) {
        if(userIp.equals(s)) {
            allowed = true;
//            break;
        }
    }
    if(!allowed) {m.redirect("/"); return;}

    Page p = new Page();
    p.setRequest(request);
    p.setWriter(out);
    p.setPageContext(pageContext);

    String sysToday = Malgn.time("yyyyMMdd");
    String sysNow = Malgn.time("yyyyMMddHHmmss");


      


    //\uc811\uadfc\uad8c\ud55c

    //\uac1d\uccb4
    UserDao user = new UserDao();

    //\ud3fc\uccb4\ud06c
//    f.addElement("");

    //\ubaa9\ub85d
    ListManager lm = new ListManager();
    lm.setRequest(request);
    lm.setListNum(15);
    lm.setTable(user.table + " a ");
//    lm.setFields("a.");
    lm.addWhere("a.status != -1");
//    lm.addSearch();
    lm.setOrderBy("a.id DESC");

    //\ud3ec\ub9f7\ud305
    DataSet list = lm.getDataSet();


    p.setLayout("main");
    p.setVar("query", m.qs());
    p.setBody("main.user_list");

    p.setLoop("list", list);
    p.setVar("pagebar", lm.getPaging());

    p.display();

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
    depend = new com.caucho.vfs.Depend(appDir.lookup("myweb/user_list.jsp"), -3455745636752434030L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("myweb/init.jsp"), -8537777119148297730L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }
}
