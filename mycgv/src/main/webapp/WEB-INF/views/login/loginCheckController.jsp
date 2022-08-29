<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mycgv.dao.CgvMemberDAO"%>
<jsp:useBean id = "vo" class = "com.mycgv.vo.CgvMemberVO"/>
<jsp:setProperty name = "vo" property = "*"/>
<%
	CgvMemberDAO dao = new CgvMemberDAO();
	int result = dao.login(vo);
	if(result == 2){
		//로그인 성공 -> session 객체에 key, value 추가 후 index 페이지로 이동
		//response.sendRedirect 실행 후 작성하면 아무것도 안되게 됨
		
		session.setAttribute("sid", vo.getId());	//(ex).set=hong
		//session은 하나의 컨테이너처럼 저장소임. 브라우저와 서버를 오가며 옮겨주는 공간의 이름이 session
		//session을 비교할 땐, 공간인 session을 비교하는 것이 아니라 session 안의 key 값(또는 value 값)을 이용하여 비교 
		//session.setMaxInactiveInterval("시간 지정");
		response.sendRedirect("../index.jsp?login=success");
	}else{
		response.sendRedirect("login.jsp?login=" + result);
	}
%>