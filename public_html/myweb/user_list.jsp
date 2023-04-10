<%@ page contentType="text/html; charset=utf-8" %><%@ include file="init.jsp"%><%


    //접근권한

    //객체
    UserDao user = new UserDao();

    //폼체크
//    f.addElement("");

    //목록
    ListManager lm = new ListManager();
    lm.setRequest(request);
    lm.setListNum(15);
    lm.setTable(user.table + " a ");
//    lm.setFields("a.");
    lm.addWhere("a.status != -1");
//    lm.addSearch();
    lm.setOrderBy("a.id DESC");

    //포맷팅
    DataSet list = lm.getDataSet();


    p.setLayout("main");
    p.setVar("query", m.qs());
    p.setBody("main.user_list");

    p.setLoop("list", list);
    p.setVar("pagebar", lm.getPaging());

    p.display();
%>