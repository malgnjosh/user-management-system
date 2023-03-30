package dao;

import malgnsoft.db.*;
import malgnsoft.util.*;

public class MailDao extends DataObject {

    Malgn m;
    public MailDao() {
        this.table = "TB_MAIL";

        m = new Malgn();
    }

    public void send(String email, String content) {
        //설정
        String subject = "인증 메일입니다.";
        String body = "인증번호: " + content;

        //발송
        boolean result = false;
        m.mailFrom = "josh@malgnsoft.com";
        m.mail(email, "[숙제] " + subject, body);

        return;
    }
}

