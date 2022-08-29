<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9000/mycgv/resources/css/mycgv.css">
<link rel="stylesheet" href="http://localhost:9000/mycgv/resources/css/am-pagination.css">
<script src="http://localhost:9000/mycgv/resources/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9000/mycgv/resources/js/am-pagination.js"></script>
<script>
	$(document).ready(function(){
		
		var pager = jQuery('#ampaginationsm').pagination({
		
		    maxSize: 7,	    		// max page size
		    totals: "${ dbCount }",	// total pages	
		    page: "${ rpage }",		// initial page		
		    pageSize: "${ pageSize }",			// max number items per page
		
		    // custom labels		
		    lastText: '&raquo;&raquo;', 		// 오른쪽 꺾인 괄호 (>)
		    firstText: '&laquo;&laquo;',		// 왼쪽 꺾인 괄호 (<)
		    prevText: '&laquo;',		
		    nextText: '&raquo;',
				     
		    btnSize:'sm'	// 'sm'  or 'lg'		
		});
		
		jQuery('#ampaginationsm').on('am.pagination.change',function(e){
			   jQuery('.showlabelsm').text('The selected page no: '+e.page);
	           $(location).attr('href', "http://localhost:9000/mycgv/admin_notice_list.do?rpage="+e.page);         
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
		<h1>공지사항-리스트</h1>
		<table class="board">
			<tr>
				<td colspan="4">
					<a href="admin_notice_write.do">
					<button type="button" class="btn_style">글쓰기</button>
					</a>
				</td>
			</tr>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>등록날짜</th>
				<th>조회수</th>
			</tr>
			<c:choose>
				<c:when test = "${ fn:length(list) == 0 }">
					<tr>
						<td colspan="4">등록된 공지사항이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var = "vo" items = "${ list }">
						<tr>
							<td>${ vo.rno }</td>
							<td><a href="admin_notice_content.do?nid=${ vo.nid }">${ vo.ntitle }</a></td>
							<td>${ vo.ndate }</td>
							<td>${ vo.nhits }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tr>
				<td colspan="4"><div id="ampaginationsm"></div></td>
			</tr>
		</table>	
	</div>
	
	<!-- footer Include -->
	<iframe src="http://localhost:9000/mycgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>







