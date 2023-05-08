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
        int fail_cnt_temp;
        if(null == session.getAttribute("FAIL_CNT")) fail_cnt_temp = 0;
        else fail_cnt_temp = (Integer)session.getAttribute("FAIL_CNT");
        if(5 <= fail_cnt_temp) { //실패횟수가 5 이상
            if(null == session.getAttribute("BLOCKED_TIME")) session.setAttribute("BLOCKED_TIME", sysNow); //현재 시간을 저장
            if(5 > Malgn.diffDate("I", session.getAttribute("BLOCKED_TIME").toString(), sysNow)) { //5분 동안 차단
                m.jsError("비밀번호 입력 횟수 초과로 5분간 로그인이 차단됩니다.");
                return;
            } else { //5분이 지나면 초기화
                session.setAttribute("BLOCKED_TIME", "");
                session.setAttribute("FAIL_CNT", "");
            }
        }

        DataSet info = user.find("login_id = ? AND type = 'A' AND status = 1", new Object[]{f.get("loginid")});
        if(!info.next()) {
            if(0 == fail_cnt_temp) session.setAttribute("FAIL_CNT", 1);
            else session.setAttribute("FAIL_CNT", fail_cnt_temp + 1);
            m.jsError("아이디/비밀번호를 확인해주세요.");
            return;
        } else if(5 <= info.i("fail_cnt")) {
            m.jsError("비밀번호 입력 횟수 초과로 로그인이 차단된 계정입니다.");
            return;
        }

        String inputPw = Malgn.encrypt(f.get("passwd"), "sha-256");
        if(!inputPw.equals(info.s("password"))) {
            user.item("fail_cnt", info.i("fail_cnt") + 1);
            if(!user.update("id = " + info.s("id"))) {m.jsError("회원정보 갱신중 오류가 발생하였습니다."); return;}
            m.jsError("아이디/비밀번호를 확인해주세요.");
            return;
        }

        //로그인 성공
        session.setAttribute("BLOCKED_TIME", "");
        session.setAttribute("FAIL_CNT", "");

        user.item("fail_cnt", 0);
        if(!user.update("id = " + info.s("id"))) {m.jsError("회원정보 갱신중 오류가 발생하였습니다.");return;}

        auth.put("USER_ID", info.s("id"));
        auth.put("USER_TYPE", info.s("type"));
        auth.setAuthInfo();

        m.jsAlert(info.s("user_nm") + "님 환영합니다!!");
        m.jsReplace("index.jsp");
        return;
    }

    p.setLayout("blank");
    p.setBody("main.login");

    p.setVar("form_script", f.getScript());
    p.display();
%>