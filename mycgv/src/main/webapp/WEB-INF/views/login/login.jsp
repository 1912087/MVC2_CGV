<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String result = request.getParameter("login");
	String join = request.getParameter("join");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9000/mycgv/resources/css/mycgv.css">
<script src="http://localhost:9000/mycgv/resources/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9000/mycgv/resources/js/mycgv_jquery.js"></script>
<script>
	$(document).ready(function(){
		let login = "${ login_result }";
		if(login == "fail"){
			alert("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		let join = "${ join_result }";
		if(join == "ok"){
			alert("회원가입 성공!");
		}
	});
</script>
</head>
<body>
	<!-- Header Include -->
	<iframe src="http://localhost:9000/mycgv/header.do" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>
	
	
	<!---------------------------------------------->
	<!--------------- Content ----------------------->
	<!---------------------------------------------->
	<div class="content">
	 <h1>Login</h1>
		<form name="loginForm" action="loginCheckController.do" method="post">
		<ul>
			<li>
				<label>아이디</label>
				<input type="text" name="id" id="id" placeholder="아이디를 입력해주세요">
			</li>
			<li>
				<label>패스워드</label>
				<input type="password" name="pass" id="pass">
			</li>
			<li>
				<button type="button" id="btnLogin">로그인</button>
				<button type="reset">다시쓰기</button>
			</li>
		</ul>
		</form>
	</div>
	
	<!-- footer Include -->
	<iframe src="http://localhost:9000/mycgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>







