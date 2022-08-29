<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
	//Spring에서는 session 체크를 별도로 하기 때문에 이 로직은 더이상 필요하지 않음
	String sid = (String)session.getAttribute("sid");

	//페이지 별로 보안을 위해 들어가는 session 처리(페이지 별로 Model들의 Access Controller을 진행해주어야 함)
	if(sid == null){
%>
<script>
	alert("로그인 후 이용가능합니다.");
	location.href = "http://localhost:8020/mycgv/login/login.jsp";
</script>
<%
	} else {
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9000/mycgv/resources/css/mycgv.css">
</head>
<body>
	<!-- Header Include -->
	<iframe src="http://localhost:9000/mycgv/header.do" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>
	
	
	<!---------------------------------------------->
	<!--------------- Content ----------------------->
	<!---------------------------------------------->
	<div class="content">
		<h1>마이CGV</h1>	
		<section class="mycgv">
			<article>예매확인</article>
			<article>정보수정</article>
		</section>
	</div>
	
	<!-- footer Include -->
	<iframe src="http://localhost:9000/mycgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>
<%-- <%
	}
%> --%>






