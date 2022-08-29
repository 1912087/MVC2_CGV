<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mycgv.dao.CgvMemberDAO, com.mycgv.vo.CgvMemberVO"%>
<jsp:useBean id = "vo" class = "com.mycgv.vo.CgvMemberVO"/>
<jsp:setProperty name = "vo" property = "*"/>
<%
	String id = request.getParameter("id");
	CgvMemberDAO dao = new CgvMemberDAO();
	int result = dao.insert(vo);
	if(result == 1){
		response.sendRedirect("../login/login.jsp?join=ok");
	}else{
		response.sendRedirect("../errorPage.jsp");
	}
%>