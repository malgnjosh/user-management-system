<%@ page contentType="text/html; charset=utf-8" %><%@ include file="init.jsp"%><%
    //접근제한

    //객체
    UserDao user = new UserDao();

    //폼체크

    //수정
    if(m.isPost() && f.validate()) {

//        if(!user.update()) {m.jsError("수정하는 중 오류가 발생했습니다."); return;}
//        m.jsReplace("user_list.jsp");
//        return;
    }

    p.setLayout("main");
    p.setBody("main.mypage");
    p.setVar("form_script", f.getScript());

    p.display();
%>