<%@ page import="java.util.*, java.io.*, dao.*, malgnsoft.db.*, malgnsoft.util.*" %><%

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



    //IP 차단
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

%>