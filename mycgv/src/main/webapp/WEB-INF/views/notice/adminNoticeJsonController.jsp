<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mycgv.vo.CgvNoticeVO, com.mycgv.dao.CgvNoticeDAO, java.util.ArrayList"%>
<%@ page import = "com.google.gson.*" %>
<%
	//현재 페이지
	String rpage = request.getParameter("rpage");
	CgvNoticeDAO dao = new CgvNoticeDAO();
	
	//페이징 처리
	int startCount = 0;
	int endCount = 0;
	int pageSize = 5;	//한 페이지 당 출력되는 게시글 수
	int reqPage = 1;	//요청 페이지(현재 몇 페이지인지)
	int pageCount = 1;	//전체 페이지(dbCount/pageSize)
	int dbCount = dao.totalCount();
	
	//전체 페이지
	if(dbCount % pageSize == 0){
		pageCount = dbCount/pageSize;
	}else{
		pageCount = dbCount/pageSize + 1;
	}
	
	//요청 페이지
	if(rpage != null){
		reqPage = Integer.parseInt(rpage);
		startCount = (reqPage - 1) * pageSize + 1;	//reqPage-1 : 이전 페이지 , 이전페이지 *pageSize : 이전페이지의 마지막 행, +1 : 마지막 행의 다음 행
		endCount = reqPage * pageSize;
	}else{
		startCount = 1;
		endCount = pageSize;
	}
	
	ArrayList<CgvNoticeVO> list = (ArrayList<CgvNoticeVO>)dao.selectAll(startCount, endCount);
	
	//JSON으로 변환
	JsonObject jobject = new JsonObject();
	JsonArray jarray = new JsonArray();
	Gson gson = new Gson();
	
	//페이징 처리 변수들을 JSON 객체에 같이 넣어서 보냄
	jobject.addProperty("dbCount", dbCount);
	jobject.addProperty("pageSize", pageSize);
	jobject.addProperty("rpage", reqPage);
	
	for(CgvNoticeVO vo : list){
		JsonObject jo = new JsonObject();
		jo.addProperty("rno", vo.getRno());
		jo.addProperty("nid", vo.getNid());
		jo.addProperty("ntitle", vo.getNtitle());
		jo.addProperty("ncontent", vo.getNcontent());
		jo.addProperty("nhits", vo.getNhits());
		jo.addProperty("ndate", vo.getNdate());
		
		jarray.add(jo);
	}

	jobject.add("list", jarray);
	
	out.write(gson.toJson(jobject));
	
%>