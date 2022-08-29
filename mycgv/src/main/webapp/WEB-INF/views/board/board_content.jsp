<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9000/mycgv/resources/css/mycgv.css">
<script src="http://localhost:9000/mycgv/resources/js/jquery-3.6.0.min.js"></script>

	<style>
		.background{
			position : fixed;
			top: 0;
			left : 0;
			width : 100%;
			height : 100vh;
			background-color : rgba(0, 0, 0, 0.3);
			z-index : -1;
			opacity : 0;
		}
		.window{
			position : relative;
			width : 100%;
			height : 100vh;
		}
		.popup{
			position : absolute;
			top : 50%;
			left : 50%;
			transform : translate(-50%, -30%);
			background-color : white;
			width : 500px;
			height : 500px;
			border-radius : 15px;
		}
		.show {
			opacity : 1;
			z-index : 10;
			transition : all 0.3s;
		}
		.show .popup {
			z-index : 10;
			transform : translate(-50%, -50%);
			transition : all 0.3s;
		}
	</style>
</head>
<body>
	<!-- Header Include -->
	<iframe src="http://localhost:9000/mycgv/header.do" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>
	
	
	<!---------------------------------------------->
	<!--------------- Content ----------------------->
	<!---------------------------------------------->
	<div class="content">
		<h1>게시판-상세보기</h1>
		<table class="boardContent">	
			<tr>				
				<th>등록일자</th>
				<td>${ vo.bdate }</td>
				<th>조회수</th>
				<td>${ vo.bhits }</td>
			</tr>		
			<tr>				
				<th>제목</th>
				<td colspan="3">${ vo.btitle }</td>
			</tr>
			<tr>				
				<th>내용</th>
				<td colspan="3">${ vo.bcontent }<br><br><br><br></td>
			</tr>
			<tr>
				<td colspan="4">
					<a href="board_update.do?bid=${ vo.bid }"><button type="button" class="btn_style">수정하기</button></a>
					<button type="button" class="btn_style" id="boardListDelete">삭제하기</button>
					<a href="board_list.do">
						<button type="button" class="btn_style">리스트</button>
					</a>
				</td>
			</tr>			
		</table>	
	</div>
	<div class = "background">
		<div class = "window">
			<div class = "popup">
				<form name="boardDeleteForm" action="board_delete_check.do" method="post"
					 style = "width : inherit; height : inherit;">
					<input type = "hidden" name = "bid" value = "${ vo.bid }">
					<ul style = "width : inherit; height : inherit; padding : 0; border : none; 
								display : grid; align-content : center;">
						<li>
							<img src="http://localhost:9000/mycgv/resources/images/delete.jpg"> <!-- 휴지통 이미지 -->					
						</li>				
						<li>
							<div>정말로 삭제하시겠습니까?</div>
						</li>
						<li>
							<button type="submit" class="btn_style">삭제완료</button>					
							<button type="button" class="btn_style" id ="close">닫기</button>
							<a href="board_list.do"><button type="button" class="btn_style">리스트</button></a>
						</li>
					</ul>
				</form>
			</div>
		</div>
	</div>
	<script>
		$("#boardListDelete").click(function(){
			$(".background").addClass("show");
			$(".window").addClass("show");
		});
		$("#close").click(function(){
			$(".background").removeClass("show");
			$(".window").removeClass("show");
		});
	</script>
	<!-- footer Include -->
	<iframe src="http://localhost:9000/mycgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>







