package com.spring.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.dao.CgvNoticeDAO;
import com.mycgv.vo.CgvMemberVO;
import com.mycgv.vo.CgvNoticeVO;

@Controller
public class AdminController {
	//admin.do : 관리자 메인 페이지
	@RequestMapping(value = "/admin.do", method = RequestMethod.GET)
	public String admin() {
		return "/admin/admin";
	}

	/*************************** 공지사항 관리 *********************************/
	//admin_list.do : 공지사항 전체리스트
	@RequestMapping(value = "/admin_notice_list.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		
		CgvNoticeDAO dao = new CgvNoticeDAO();
		
		//페이징 처리
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;
		int reqPage = 1;
		int pageCount = 1;
		int dbCount = dao.totalCount();
		
		//전체 페이지
		if(dbCount % pageSize == 0) {
			pageCount = dbCount/pageSize;
		}else {
			pageCount = dbCount/pageSize + 1;
		}
		
		//현재 페이지
		if(rpage != null) {
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage - 1) * pageSize + 1;
			endCount = reqPage * pageSize;
		}else {
			startCount = 1;
			endCount = pageSize;
		}
		
		ArrayList<CgvNoticeVO> list = (ArrayList<CgvNoticeVO>)dao.selectAll(startCount, endCount);
		
		mv.addObject("pageSize", pageSize);
		mv.addObject("rpage", reqPage);
		mv.addObject("dbCount", dbCount);
		mv.addObject("list", list);
		mv.setViewName("/admin/admin_notice/admin_notice_list");
		
		return mv;
	}
	
	//admin_content.do : 공지사항 상세 보기
	@RequestMapping(value = "/admin_notice_content.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_content(String nid) {
		ModelAndView mv = new ModelAndView();
		
		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = dao.select(nid);
		
		if(vo != null) {
			int result = dao.updateNhits(nid);
			if(result == 1) {
				vo.setNhits(vo.getNhits() + 1);
			}
		}
		
		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_notice/admin_notice_content");
		
		return mv;
	}
	
	//admin_notice_wirte_check.do : 공지사항 작성
	@RequestMapping(value = "/admin_notice_wirte_check.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_wirte_check(CgvNoticeVO vo) {
		ModelAndView mv = new ModelAndView();
		
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.insert(vo);
		if(result == 1) {
			mv.setViewName("redirect://admin_notice_list.do");
		}else {
			mv.setViewName("error_page");
		}
		return mv;
	}
	
	//admin_notice_write.do : 공지사항 작성 페이지
	@RequestMapping(value = "/admin_notice_write.do", method = RequestMethod.GET)
	public String admin_notice_write() {
		return "/admin/admin_notice/admin_notice_write";
	}
	
	//admin_notice_update_check.do : 공지사항 수정
	@RequestMapping(value = "/admin_notice_update_check.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_update_check(CgvNoticeVO vo) {
		ModelAndView mv = new ModelAndView();
		
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.update(vo);
		if(result == 1) {
			mv.setViewName("redirect:/admin_notice_list.do");
		}else {
			mv.setViewName("error_page");
		}
		return mv;
	}
	
	//admin_update.do : 공지사항 수정 페이지
	@RequestMapping(value = "/admin_notice_update.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_update(String nid) {
		ModelAndView mv = new ModelAndView();
		
		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = dao.select(nid);
		
		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_notice/admin_notice_update");
		
		return mv;
	}
	
	
	//admin_delete_wirte_check.do : 공지사항 삭제
	@RequestMapping(value = "/admin_notice_delete_check.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_delete_check(String nid) {
		ModelAndView mv = new ModelAndView();
		
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.delete(nid);
		if(result == 1) {
			mv.setViewName("redirect:/admin_notice_list.do");
		}else {
			mv.setViewName("error_page");
		}
		return mv;
	}
	
	/*************************** 회원 관리 *********************************/
	//admin_member_list.do
	@RequestMapping(value = "/admin_member_list.do", method = RequestMethod.GET)
	public ModelAndView admin_member_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		
		CgvMemberDAO dao = new CgvMemberDAO();
		
		//페이징 처리
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;
		int reqPage = 1;
		int pageCount = 1;
		int dbCount = dao.totalCount();
		
		//전체 페이지
		if(dbCount % pageSize == 0) {
			pageCount = dbCount/pageSize;
		}else {
			pageCount = dbCount/pageSize + 1;
		}
		
		//현재 페이지
		if(rpage != null) {
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage - 1) * pageSize + 1;
			endCount = reqPage * pageSize;
		}else {
			startCount = 1;
			endCount = pageSize;
		}
		
		ArrayList<CgvMemberVO> list = (ArrayList<CgvMemberVO>)dao.select(startCount, endCount);
		
		mv.addObject("pageSize", pageSize);
		mv.addObject("rpage", reqPage);
		mv.addObject("dbCount", dbCount);
		mv.addObject("list", list);
		mv.setViewName("/admin/admin_member/admin_member_list");
		
		return mv;
	}
	
	//admin_member_content.do
	@RequestMapping(value = "/admin_member_content.do", method = RequestMethod.GET)
	public ModelAndView admin_member_content(String id) {
		ModelAndView mv = new ModelAndView();
		
		CgvMemberDAO dao = new CgvMemberDAO();
		CgvMemberVO vo = dao.select(id);
		
		String address = "[" + vo.getZonecode() + "] " + vo.getAddr1() + " " + vo.getAddr2();
		
		mv.addObject("vo", vo);
		mv.addObject("address", address);
		mv.setViewName("/admin/admin_member/admin_member_content");
		
		return mv;
	}
	
}
