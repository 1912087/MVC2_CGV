<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mycgv.dao.CgvNoticeDAO"%>
<jsp:useBean id = "vo" class = "com.mycgv.vo.CgvNoticeVO"/>
<jsp:setProperty name = "vo" property = "*"/>
<%	
	//System.out.println(vo.getNid());
	CgvNoticeDAO dao = new CgvNoticeDAO();
	int result = dao.delete(vo.getNid());
	if(result == 1){
		response.sendRedirect("admin_notice_list.jsp");
	}else{
		response.sendRedirect("../errorPage.jsp");
	}
	
%>