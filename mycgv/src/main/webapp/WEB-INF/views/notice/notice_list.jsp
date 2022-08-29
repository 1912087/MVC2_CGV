<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mycgv.dao.CgvNoticeDAO, com.mycgv.vo.CgvNoticeVO" %>
<%
	String rpage = request.getParameter("rpage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9000/mycgv/resources/css/mycgv.css">
<link rel="stylesheet" href = "http://localhost:9000/mycgv/resources/css/am-pagination.css">
<script src = "http://localhost:9000/mycgv/resources/js/jquery-3.6.0.min.js"></script>
<script src = "http://localhost:9000/mycgv/resources/js/am-pagination.js"></script>
<script>
	$(document).ready(function(){
		
		initAjax(1);
		function initAjax(rpage){
			$.ajax({
				url : "adminNoticeJsonController.jsp?rpage="+rpage,
				success : function(result){
					//서버에서 데이터를 주고 받을 때는 String 형태로 전송하기 때문에 다시 JSON 객체로 변환하는 작업이 필요
					let data = JSON.parse(result);
					output = "<table class = 'board'>";
					output += "<tr><th>번호</th><th>제목</th><th>등록날짜</th><th>조회수</th></tr>";
					if(data.list.length == 0){
						output += "<tr><td colspan='4'>등록된 공지사항이 없습니다.</td></tr>"
					}else{
						for(dataset of data.list){
							output += "<tr>";
							output += "<td>" + dataset.rno + "</td>";
							output += "<td><a href='#' class='bclass' id='" + dataset.nid + "'>" + dataset.ntitle + "</a></td>";
							output += "<td>" + dataset.ndate + "</td>";
							output += "<td>" + dataset.nhits + "</td>";
							output += "</tr>";
						}
					}
					output += "<tr><td colspan='4'><div id='ampaginationsm'></div></td></tr>";
					output += "<table>";
					
					//출력
					$("table.board").remove();
					$("h1").after(output);
					
					//페이징 처리 이벤트는 외부 함수를 호출하여 처리
					noticePagination(data.dbCount, data.pageSize, data.rpage);
					
					jQuery('#ampaginationsm').on('am.pagination.change',function(e){
						jQuery('.showlabelsm').text('The selected page no: '+e.page);
						initAjax(e.page);
					});
					
					//게시판 상세보기
					$(".bclass").click(function(){
						noticeContent($(this).attr("id"));
					});
				}//success
			});//ajax
		}//initAjax
		
		function noticePagination(dbCount, pageSize, rpage){
			var pager = jQuery('#ampaginationsm').pagination({
				
			    maxSize: 7,	    		// max page size
			    totals: dbCount,		// total pages	
			    page: rpage,		// initial page		
			    pageSize: pageSize,		// max number items per page
			
			    // custom labels		
			    lastText: '&raquo;&raquo;', 		
			    firstText: '&laquo;&laquo;',		
			    prevText: '&laquo;',		
			    nextText: '&raquo;',
					     
			    btnSize:'sm'	// 'sm'  or 'lg'		
			});
		}//noticePagination()
		
		function noticeContent(nid){
			$.ajax({
				url : "noticeContentJsonController.jsp?nid="+nid,
				success : function(result){
					//String 객체를 JSON 객체로 변환
					let data = JSON.parse(result);
					
					output = "<table class = 'boardContent'>";
					output += "<tr><th>등록일자</th><td>" + data.ndate + "</td>";
					output += "<th>조회수</th><td>" + data.nhits + "</td></tr>";
					output += "<tr><th>제목</th><td colspan = '3'>" + data.ntitle + "</td></tr>";
					output += "<tr><th>내용</th><td colspan = '3'>" + data.ncontent + "</td></tr>";
					
					output += "<tr>";
					output += "<td colspan = '4'>";
					output += "<button type='button' class='btn_style' id = 'backList'>리스트</button>";
					output += "<button type='button' class='btn_style' id = 'backHome'>홈으로</button>";
					output += "</td>";
					output += "</tr>";
					output += "</table>";
					
					//출력
					$("table.board").remove();
					$("h1").after(output);
					
					$("#backList").click(function(){
						$(location).attr("href", "http://localhost:9000/mycgv/notice/notice_list.jsp");
					});
					$("#backHome").click(function(){
						$(location).attr("href", "http://localhost:9000/mycgv/index.jsp");
					});
				}
			})//ajax
		}//noticeContent
	});//ready
</script>
</head>
<body>
	<!-- Header Include -->
	<iframe src="http://localhost:9000/mycgv/header.do" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>
	
	
	<!---------------------------------------------->
	<!--------------- Content ----------------------->
	<!---------------------------------------------->
	<div class="content">
		<h1>공지사항</h1>
<!-- 		<table class="board">			
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>등록날짜</th>
				<th>조회수</th>
			</tr>
			<tr>
				<td>1</td>
				<td><a href="notice_content.jsp">7월 마지막 이벤트 입니다~ ^^</a></td>
				<td>2022-07-22</td>
				<td>1245</td>
			</tr>
			<tr>
				<td>2</td>
				<td>탑건 재밌어요~ ^^</td>
				<td>2022-07-22</td>
				<td>1245</td>
			</tr>
			<tr>
				<td>3</td>
				<td>탑건 재밌어요~ ^^</td>
				<td>2022-07-22</td>
				<td>1245</td>
			</tr>
			<tr>
				<td>4</td>
				<td>탑건 재밌어요~ ^^</td>
				<td>2022-07-22</td>
				<td>1245</td>
			</tr>
			<tr>
				<td>5</td>
				<td>탑건 재밌어요~ ^^</td>
				<td>2022-07-22</td>
				<td>1245</td>
			</tr>
			<tr>
				<td>6</td>
				<td>탑건 재밌어요~ ^^</td>
				<td>2022-07-22</td>
				<td>1245</td>
			</tr>
			<tr>
				<td>7</td>
				<td>탑건 재밌어요~ ^^</td>
				<td>2022-07-22</td>
				<td>1245</td>
			</tr>
			<tr>
				<td>8</td>
				<td>탑건 재밌어요~ ^^</td>
				<td>2022-07-22</td>
				<td>1245</td>
			</tr>
			<tr>
				<td>9</td>
				<td>탑건 재밌어요~ ^^</td>
				<td>2022-07-22</td>
				<td>1245</td>
			</tr>
			<tr>
				<td>10</td>
				<td>탑건 재밌어요~ ^^</td>
				<td>2022-07-22</td>
				<td>1245</td>
			</tr>
			<tr>
				<td colspan="4"> << 1 2 3 4 5 >> </td>
			</tr>
		</table> -->	
	</div>
	
	<!-- footer Include -->
	<iframe src="http://localhost:9000/mycgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>







