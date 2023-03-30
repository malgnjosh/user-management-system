<%@ page contentType="text/html; charset=utf-8" %><%@ include file="init.jsp"%><%

    boolean sendBlock = false;
    boolean authBlock = false;

    UserDao user = new UserDao();
    MailDao mail = new MailDao();

    //발송
    if("send".equals(m.rs("mode"))) {
        sendBlock = true;
        //제한-3분
//    int gapLimit = 3;
//    int gapMinutes = !"".equals(m.getSession("EMAIL_SENDDATE")) ? Malgn.diffDate("I", m.getSession("EMAIL_SENDDATE"), sysNow) : 999;
//    if(gapMinutes < gapLimit) {
//        m.jsAlert(_message.get("alert.member.find.remain", new String[] {"gapMinutes=>" + gapMinutes, "remain=>" + (gapLimit - gapMinutes)})); return;
//    }
        DataSet uinfo = user.find("login_id = ? AND birth = ?", new Object[] {f.get("loginid"), f.get("birthday")});
        if(!uinfo.next()) { m.jsError("해당 회원이 존재하지 않습니다."); return;}

        String authCode = "" + Malgn.getRandInt(1000, 4);
        mail.send(uinfo.s("email"), authCode);
        m.setSession("AUTH_CODE", authCode);
        m.setSession("EMAIL_SENDDATE", sysNow);
        m.jsAlert("메일이 발송되었습니다.");
    }
    else if("auth".equals(m.rs("mode"))) {
        if(!f.get("auth_code").equals(m.getSession("AUTH_CODE"))) {
            m.jsError("인증번호가 일치하지 않습니다.");
            return;
        } else {
            authBlock = true;
        }
    }

    else if("change".equals(m.rs("mode"))) {
        m.jsAlert("비밀번호 변경에 성공했습니다.");
        m.redirect("login.jsp");
    }

    p.setLayout("blank");
    p.setBody("main.find");
    p.setVar("send_block", sendBlock);
    p.setVar("auth_block", authBlock);
    p.display();
%>
