<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--WAS 서버에서 Session Table의 key, value 값을 삭제--%>
<%
	String sid = (String)session.getAttribute("sid");
	if(sid != null){	//로그인이 되어있음
		session.invalidate();	//session 삭제하는 법..!
		// 이때 설정된 세션의 값들을 모두 사라지도록 하기 위해서는 세션 객체의 invalidate 함수를 사용한다. 
		// invalidate 함수는 세션을 없애고 세션에 속해있는 값들을 모두 없앤다.
	}
%>

<script>
	alert("정상적으로 로그아웃 되었습니다.");
	location.href = "http://localhost:8020/mycgv/index.jsp";
</script>