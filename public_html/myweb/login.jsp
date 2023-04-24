<%@ page contentType="text/html; charset=utf-8" %><%@ include file="init.jsp"%><%

    //로그인
    if(0 != userId) {
        m.redirect("index.jsp");
        return;
    }

    //객체
    UserDao user = new UserDao();
//    user.setDebug(out);

    //폼체크
    f.addElement("loginid", null, null);
    f.addElement("passwd", null, null);

    //수정
    if(m.isPost() && f.validate()) {
        DataSet info = user.find("login_id = ? AND type = 'A' AND status = 1", new Object[]{f.get("loginid")});
        if (!info.next()) {
            m.jsError("아이디/비밀번호를 확인해주세요.");
            return;
        }
        int failCnt = info.i("fail_cnt") + 1;
        if(5 < failCnt) {
            if(null == session.getAttribute("BLOCKED_TIME")) session.setAttribute("BLOCKED_TIME", sysNow);
            if(5 > Malgn.diffDate("I", session.getAttribute("BLOCKED_TIME").toString(), sysNow)) {
                m.jsError("비밀번호 입력 횟수 초과로 5분간 로그인이 차단됩니다.");
                return;
            } else {
                session.setAttribute("BLOCKED_TIME", "");
                user.item("fail_cnt", 0);
                if(!user.update("id = " + info.s("id"))) {m.jsError("회원정보 갱신중 오류가 발생하였습니다.");return;
                }
            }
        }

        String inputPw = Malgn.encrypt(f.get("passwd"), "sha-256");
        if (!inputPw.equals(info.s("password"))) {
            user.item("fail_cnt", failCnt);
            if(!user.update("id = " + info.s("id"))) {m.jsError("회원정보 갱신중 오류가 발생하였습니다."); return;}
            m.jsError("아이디/비밀번호를 확인해주세요.");
            return;
        }

        auth.put("USER_ID", info.i("id"));
        auth.put("USER_TYPE", info.s("type"));
        auth.save();

        m.jsAlert(info.s("user_nm") + "님 환영합니다!!");
        m.jsReplace("index.jsp");
        return;
    }

    p.setLayout("blank");
    p.setBody("main.login");

    p.setVar("form_script", f.getScript());
    p.display();
%>