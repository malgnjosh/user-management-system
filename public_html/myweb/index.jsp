<%@ page contentType="text/html; charset=utf-8" %><%@ include file="init.jsp"%><%

    //로그인
    if(0 == userId) {
        m.redirect("login.jsp");
        return;
    }

    p.setLayout("main");
    p.setBody("main.main");
    p.display();
%>