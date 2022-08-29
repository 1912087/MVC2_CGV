<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mycgv.dao.CgvMemberDAO"%>
<%
	String id = request.getParameter("id");
	CgvMemberDAO dao = new CgvMemberDAO();
	int result = dao.idCheck(id);
	
	//AJAX로 데이터를 넘길 때는 무조건 문자열(String) 형태로 보내야 한다.
	out.write(String.valueOf(result));
%>