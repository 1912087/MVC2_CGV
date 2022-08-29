<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mycgv.vo.CgvNoticeVO, com.mycgv.dao.CgvNoticeDAO"%>
<%@ page import = "com.google.gson.*" %>
<%
	String nid = request.getParameter("nid");
	CgvNoticeDAO dao = new CgvNoticeDAO();
	CgvNoticeVO vo = dao.select(nid);
	
	//저장된 쿠키 불러오기
	Cookie[] cookieList = request.getCookies();
	
	//쿠키를 담을 변수
	String cookieMember = null;
	
	//쿠키 세션 처리
	if(session.getAttribute(nid + ":cookies") == null){
		session.setAttribute(nid + ":cookies", nid + ":" + cookieMember);
	}else{
		session.setAttribute(nid + ":cookies ex", nid + ":" + cookieMember);
		if(!session.getAttribute(nid + ":cookies").equals(nid + ":" + cookieMember)){
			session.setAttribute(nid + ":cookies", nid + ":" + cookieMember);
		}
	}
	
	//조회수 업데이트
	if(!session.getAttribute(nid + ":cookies").equals(session.getAttribute(nid + ":cookies ex"))){
		if(vo != null){
			int result = dao.updateNhits(nid);
			if(result == 1){
				vo.setNhits(vo.getNhits() + 1);
			}
		}
	}
	
	//JSON으로 변환
	JsonObject jobject = new JsonObject();
	Gson gson = new Gson();
	
	jobject.addProperty("nid", vo.getNid());
	jobject.addProperty("ntitle", vo.getNtitle());
	jobject.addProperty("ncontent", vo.getNcontent());
	jobject.addProperty("nhits", vo.getNhits());
	jobject.addProperty("ndate", vo.getNdate());
	
	out.write(gson.toJson(jobject));
%>