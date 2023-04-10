<%@ page contentType="text/html; charset=utf-8" %><%@ include file="init.jsp"%><%

    //로그인
    if(null != userId && !"null".equals(userId)) {
        m.redirect("index.jsp");
        return;
    }

    //객체
    UserDao user = new UserDao();
//    user.setDebug(out);

    //폼체크
    f.addElement("loginid", null, null);
    f.addElement("passwd", null, null);

    String msg = "";
    //수정
    if(m.isPost() && f.validate()) {
        DataSet info = user.find("login_id = '" + f.get("loginid") + "' AND password = '" + Malgn.encrypt(f.get("passwd"), "sha-256") + "' AND type = 'A'");

        if(!info.next()) { //비밀번호 틀림
            info = user.find("login_id = '" + f.get("loginid") + "' AND type = 'A'");
            if(!info.next()) {} //존재하지 않는 아이디
            else {
                int failCnt = info.i("fail_cnt") + 1;
                if(failCnt > 5) {
                    mSession.put("blocked_time", sysNow);
                    mSession.save();
                    user.item("status", 2);
                    msg = "회원 상태 변경 실패";
                }
                else {
                    user.item("fail_cnt", failCnt);
                    msg = "실패 횟수 설정 실패";
                }
            }
            m.jsError("아이디/비밀번호를 확인해주세요.");
        } else {
            //제한 - 5회
            if(5 > Malgn.diffDate("I", mSession.get("blocked_time"), sysNow) && 2 == info.i("status")) {
                m.jsAlert("비밀번호 입력 횟수 초과로 5분간 로그인이 차단됩니다.");
                return;
            } else {
                mSession.put("blocked_time", "");
            }

//            user.item("access_token", m.md5(m.getUniqId()));
//            msg = "Access token 발급 실패";

            auth.put("user_id", info.i("id"));
            auth.put("user_type", info.s("type"));
            auth.save();
            user.item("fail_cnt", 0);
            user.item("status", 1);
            m.jsAlert(info.s("user_nm") + "님 환영합니다!!");
            m.redirect("index.jsp");
            return;
        }
        if(!"".equals(msg) && !user.update("id = " + info.s("id"))) { m.jsAlert(msg); return; };
        return;
    }

    p.setLayout("blank");
    p.setBody("main.login");

    p.setVar("form_script", f.getScript());
    p.display();
%>