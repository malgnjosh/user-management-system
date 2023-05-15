<%@ page contentType="text/html; charset=utf-8" %><%@ include file="init.jsp"%><%

    //객체
    UserDao user = new UserDao();
    MailDao mail = new MailDao();

    boolean sendBlock = false;
    boolean authBlock = false;
    DataSet uinfo = new DataSet();
    //발송
    if("send".equals(m.rs("mode"))) {
        if("true".equals(m.rs("isSend"))) {
            m.jsAlert("이미 메일이 발송되었습니다.");
        } else {
            uinfo = user.find("login_id = ? AND birth = ?", new Object[] {f.get("loginid"), f.get("birthday")});
            if(!uinfo.next()) {
                m.jsAlert("해당 회원이 존재하지 않습니다.");
            } else {
                String authCode = "" + Malgn.getRandInt(1000, 8999);
                mail.send(uinfo.s("email"), authCode);
                m.setSession("AUTH_CODE", authCode);
                m.setSession("EMAIL_SENDDATE", sysNow);
                m.setSession("USER_ID", uinfo.s("id"));
                m.jsAlert("메일이 발송되었습니다.");
                sendBlock = true;
            }
        }
    }
    //인증
    else if("auth".equals(m.rs("mode"))) {
        if(!f.get("auth_code").equals(m.getSession("AUTH_CODE"))) {
            m.jsAlert("인증번호가 일치하지 않습니다.");
            sendBlock = true;
        } else {
            authBlock = true;
        }
    }
    //변경
    else if("change".equals(m.rs("mode"))) {
        user.item("password", Malgn.encrypt(f.get("new_pw"), "sha-256"));
        user.item("fail_cnt", 0);
        user.item("status", 1);
        if(!user.update("id = '" + m.getSession("USER_ID") + "'")) {
            m.jsAlert("비밀번호 변경 중 오류가 발생했습니다.");
            authBlock = true;
        } else {
            m.jsAlert("비밀번호 변경에 성공했습니다.");
            m.jsReplace("login.jsp");
            return;
        }
    }

    //출력
    p.setLayout("blank");
    p.setBody("main.find");
    p.setVar("send_block", sendBlock);
    p.setVar("auth_block", authBlock);
    p.display();
%>
