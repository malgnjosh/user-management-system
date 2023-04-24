<%@ page contentType="text/html; charset=utf-8" %><%@ include file="init.jsp"%><%

    auth.delAuthInfo();
    session.invalidate();
    m.redirect("login.jsp");
%>
