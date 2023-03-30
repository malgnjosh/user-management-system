<%@ page import="java.util.*, java.io.*, dao.*, malgnsoft.db.*, malgnsoft.util.*" %><%

    Malgn m = new Malgn(request, response, out);

    Form f = new Form();
    f.setRequest(request);

    Auth auth = new Auth(request, response);
    String userId = null;
    String userSessionId = null;
    SessionDao mSession = new SessionDao(request, response);
    if(auth.isValid()) {
        userId = auth.getString("USER_ID");
        userSessionId = auth.getString("SESSIONID");
    }
//    mSession.put("id", userSessionId);
//    mSession.save();

    Page p = new Page();
    p.setRequest(request);
    p.setWriter(out);
    p.setPageContext(pageContext);

    String sysToday = Malgn.time("yyyyMMdd");
    String sysNow = Malgn.time("yyyyMMddHHmmss");

%>