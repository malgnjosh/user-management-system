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

public class _login__jsp extends com.caucho.jsp.JavaPage
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


      

    //\ub85c\uadf8\uc778
    if(0 != userId) {
        m.redirect("index.jsp");
        return;
    }

    int failCnt = m.getSessionInt("FAIL_CNT");
    String failTime = m.getSession("BLOCKED_TIME");

    if(!"".equals(failTime) && 5 >= m.diffDate("I", failTime, sysNow)) {
        m.jsError("\ube44\ubc00\ubc88\ud638 \uc785\ub825 \ud69f\uc218 \ucd08\uacfc\ub85c 5\ubd84\uac04 \ub85c\uadf8\uc778\uc774 \ucc28\ub2e8\ub429\ub2c8\ub2e4.");
        return;
    } else if(failCnt >= 5){
        failCnt = 0;
    }

    //\uac1d\uccb4
    UserDao user = new UserDao();
//    user.setDebug(out);

    //\ud3fc\uccb4\ud06c
    f.addElement("loginid", null, "required:'Y'");
    f.addElement("passwd", null, "required:'Y'");

    //\uc218\uc815
    if(m.isPost() && f.validate()) {
//        int fail_cnt_temp;
//        if(null == session.getAttribute("FAIL_CNT")) fail_cnt_temp = 0;
//        else fail_cnt_temp = (Integer)session.getAttribute("FAIL_CNT");
//        if(5 <= fail_cnt_temp) { //\uc2e4\ud328\ud69f\uc218\uac00 5 \uc774\uc0c1
//            if(null == session.getAttribute("BLOCKED_TIME")) session.setAttribute("BLOCKED_TIME", sysNow); //\ud604\uc7ac \uc2dc\uac04\uc744 \uc800\uc7a5
//            if(5 > Malgn.diffDate("I", session.getAttribute("BLOCKED_TIME").toString(), sysNow)) { //5\ubd84 \ub3d9\uc548 \ucc28\ub2e8
//                m.jsError("\ube44\ubc00\ubc88\ud638 \uc785\ub825 \ud69f\uc218 \ucd08\uacfc\ub85c 5\ubd84\uac04 \ub85c\uadf8\uc778\uc774 \ucc28\ub2e8\ub429\ub2c8\ub2e4.");
//                session.setAttribute("FAIL_CNT", "");
//                return;
//            } else { //5\ubd84\uc774 \uc9c0\ub098\uba74 \ucd08\uae30\ud654
//                session.setAttribute("BLOCKED_TIME", "");
//                session.setAttribute("FAIL_CNT", "");
//            }
//        }

        DataSet info = user.find("login_id = ? AND type = 'A' AND status = 1", new Object[]{f.get("loginid")});
        if(!info.next()) {
//            if(0 == fail_cnt_temp) session.setAttribute("FAIL_CNT", 1);
//            else session.setAttribute("FAIL_CNT", fail_cnt_temp + 1);
            m.setSession("FAIL_CNT", ++failCnt);
            if(failCnt >= 5) m.setSession("BLOCKED_TIME", sysNow);
            m.jsError("\uc544\uc774\ub514/\ube44\ubc00\ubc88\ud638\ub97c \ud655\uc778\ud574\uc8fc\uc138\uc694.");
            return;
        } else if(5 <= info.i("fail_cnt")) {
            m.jsError("\ub85c\uadf8\uc778\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4. \uad00\ub9ac\uc790\uc5d0\uac8c \ubb38\uc758\ud574\uc8fc\uc138\uc694.");
            return;
        }

        String inputPw = Malgn.encrypt(f.get("passwd"), "sha-256");
        if(!inputPw.equals(info.s("password"))) {
            user.item("fail_cnt", info.i("fail_cnt") + 1);
            if(!user.update("id = " + info.s("id"))) {m.jsError("\ud68c\uc6d0\uc815\ubcf4 \uac31\uc2e0\uc911 \uc624\ub958\uac00 \ubc1c\uc0dd\ud558\uc600\uc2b5\ub2c8\ub2e4."); return;}
            m.jsError("\uc544\uc774\ub514/\ube44\ubc00\ubc88\ud638\ub97c \ud655\uc778\ud574\uc8fc\uc138\uc694.");
            return;
        }

        //\ub85c\uadf8\uc778 \uc131\uacf5
        m.setSession("BLOCKED_TIME", "");
        m.setSession("FAIL_CNT", "");

        user.item("fail_cnt", 0);
        if(!user.update("id = " + info.s("id"))) {m.jsError("\ud68c\uc6d0\uc815\ubcf4 \uac31\uc2e0\uc911 \uc624\ub958\uac00 \ubc1c\uc0dd\ud558\uc600\uc2b5\ub2c8\ub2e4.");return;}

        auth.put("USER_ID", info.s("id"));
        auth.put("USER_TYPE", info.s("type"));
        auth.setAuthInfo();

        m.jsAlert(info.s("user_nm") + "\ub2d8 \ud658\uc601\ud569\ub2c8\ub2e4!!");
        m.jsReplace("index.jsp");
        return;
    }

    p.setLayout("blank");
    p.setBody("main.login");

    p.setVar("form_script", f.getScript());
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
    depend = new com.caucho.vfs.Depend(appDir.lookup("myweb/login.jsp"), 1440405446078124294L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("myweb/init.jsp"), 5651392717002480471L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }
}
