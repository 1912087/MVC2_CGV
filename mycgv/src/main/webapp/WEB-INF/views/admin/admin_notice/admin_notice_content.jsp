<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9000/mycgv/resources/css/mycgv.css">
	<style>
		.background{
			position : fixed;
			top : 0;
			left : 0;
			width : 100%;
			height : 100vh;
			background-color : rgba(0, 0, 0, 0.3);
			z-index : -1;
			opacity : 0;
		}
		.window {
			position : relative;
			width : 100%;
			height : 100%;
		}
		.popup {
			position : absolute;
			top : 50%;
			left : 50%;
			transform : translate(-50%, -30%);
			width : 500px;
			height : 400px;
			background-color : white;
			border-radius : 15px;
			z-index : -1;
		}
		.show {
			opacity : 1;
			z-index : 10;
			transition : all 0.5s;
		}
		.show .popup{
			transform : translate(-50%, -50%);
			z-index : 10;
			transition : all 0.5s;
		}
	</style>
	<script src = "http://localhost:9000/mycgv/resources/js/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
			$("#openDelete").click(function(){
				$(".background").addClass("show");
				$(".window").addClass("show");
			});
			$("#close").click(function(){
				$(".background").removeClass("show");
				$(".window").removeClass("show");
			});
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
		<h1>공지사항-상세보기</h1>
		<table class="boardContent">	
			<tr>				
				<th>등록일자</th>
				<td>${ vo.ndate }</td>
				<th>조회수</th>
				<td>${ vo.nhits }</td>
			</tr>		
			<tr>				
				<th>제목</th>
				<td colspan="3">${ vo.ntitle }</td>
			</tr>
			<tr>				
				<th>내용</th>
				<td colspan="3">${ vo.ncontent }<br><br><br><br></td>
			</tr>
			<tr>
				<td colspan="4">
					<a href="admin_notice_update.do?nid=${ vo.nid }"><button type="button" class="btn_style">수정하기</button></a>
					<button type="button" class="btn_style" id = "openDelete">삭제하기</button>
					<a href="admin_notice_list.do">
						<button type="button" class="btn_style">리스트</button></a>
					<a href="http://localhost:9000/mycgv/admin.do"><button type="button" class="btn_style">관리자홈</button></a>
				</td>
			</tr>			
		</table>	
	</div>
	
	<div class = "background">
		<div class = "window">
			<div class = "popup">
				<form name="boardDeleteForm" action="admin_notice_delete_check.do" method="post"
					style = "margin : 0; padding : 0; width : 100%; height : 100%">
					<input type = "hidden" name = "nid" value = "${ vo.nid }">
					<ul style = "margin : 0; padding : 0; width : 100%; height : 100%; border : none;">
						<li style = "padding-top : 50px;">
							<img src="http://localhost:9000/mycgv/resources/images/delete.jpg"> <!-- 휴지통 이미지 -->					
						</li>				
						<li>
							<div>정말로 삭제하시겠습니까?</div>
						</li>
						<li>
							<button type="submit" class="btn_style">삭제완료</button>					
							<button type="button" class="btn_style" id = "close">창닫기</button>
							<a href="admin_notice_list.do"><button type="button" class="btn_style">리스트</button></a>
						</li>
					</ul>
				</form>
			</div>
		</div>
	</div>
	<!-- footer Include -->
	<iframe src="http://localhost:9000/mycgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>




