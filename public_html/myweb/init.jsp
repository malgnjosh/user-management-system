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

    //IP 차단
    String[] allowedIpList = {"127.0.0.1", "125.129.123.211"};
    String userIp = request.getRemoteAddr();
    boolean allowed = false;
//    if(!Site.checkIP(userIp, siteinfo.s("allowed_ip_list"))) m.redirect("/"); return;
    for(String s : allowedIpList) {
        if(userIp.equals(s)) allowed = true;
    }
    if(!allowed) {m.redirect("/"); return;}

    Page p = new Page();
    p.setRequest(request);
    p.setWriter(out);
    p.setPageContext(pageContext);

    String sysToday = Malgn.time("yyyyMMdd");
    String sysNow = Malgn.time("yyyyMMddHHmmss");

%>