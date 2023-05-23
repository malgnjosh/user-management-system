<%@ page contentType="text/html; charset=utf-8" %><%@ include file="init.jsp"%><%
    //접근제한

    //객체
    UserDao user = new UserDao();


    //폼체크
    f.addElement("login_id", null, "required:'Y'");
    f.addElement("passwd", null, "required:'Y'");
    f.addElement("user_nm", null, "required:'Y'");
    f.addElement("mobile", null, null);
    f.addElement("email", null, null);

    //등록
    if(m.isPost() && f.validate()) {
         user.item("login_id", f.get("login_id"));
         user.item("password", Malgn.encrypt(f.get("passwd"), "sha-256"));
         user.item("user_nm", f.get("user_nm"));
         user.item("email", f.get("email"));
         user.item("mobile", aes.encrypt(f.get("mobile")));
         if("on".equals(f.get("is_admin"))) user.item("type", "A");
         user.item("birth", f.get("birth_date"));
         user.item("reg_date", sysNow);
         user.item("status", 1);
        if(!user.insert()) {m.jsError("등록하는 중 오류가 발생했습니다."); return;}
        m.jsReplace("user_list.jsp");
        return;
    }

    p.setLayout("main");
    p.setBody("main.user_insert");
    p.setVar("form_script", f.getScript());

    p.display();
%>