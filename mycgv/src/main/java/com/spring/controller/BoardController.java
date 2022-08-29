package com.spring.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvBoardDAO;
import com.mycgv.vo.CgvBoardVO;

@Controller
public class BoardController {
	/**
	 * board_list.do 페이지 호출
	 */
	@RequestMapping(value = "/board_list.do", method = RequestMethod.GET)
	public ModelAndView board_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		
		CgvBoardDAO dao = new CgvBoardDAO();
		
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
		
		ArrayList<CgvBoardVO> list = (ArrayList<CgvBoardVO>)dao.select(startCount, endCount);
		
		mv.addObject("pageSize", pageSize);
		mv.addObject("rpage", reqPage);
		mv.addObject("dbCount", dbCount);
		mv.addObject("list", list);
		mv.setViewName("/board/board_list");
		
		return mv;
	}
	
	/**
	 * board_content.do 페이지 호출
	 */
	@RequestMapping(value = "/board_content.do", method = RequestMethod.GET)
	public ModelAndView board_content(String bid) {
		ModelAndView mv = new ModelAndView();
		
		CgvBoardDAO dao = new CgvBoardDAO();
		CgvBoardVO vo = dao.select(bid);
		
		if(vo != null) {
			int result = dao.updateHits(bid);
			if(result == 1) {
				vo.setBhits(vo.getBhits() + 1);
			}
		}
		
		mv.addObject("vo", vo);
		mv.setViewName("/board/board_content");
		
		return mv;
	}
	
	/**
	 * board_write.do 페이지 호출
	 */
	@RequestMapping(value = "/board_write.do", method = RequestMethod.GET)
	public String board_write() {
		return "/board/board_write";
	}
	
	/**
	 * board_write_check.do : 게시글 작성
	 */
	@RequestMapping(value = "/board_write_check.do", method = RequestMethod.POST)
	public ModelAndView board_write_check(CgvBoardVO vo) {
		ModelAndView mv = new ModelAndView();
		
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.insert(vo);
		if(result == 1) {
			//mv.setViewName("/board/board_list");  에러는 없으나, 아무런 게시글이 출력되지 않는다.
			mv.setViewName("redirect:/board_list.do");	//DB 연동을 Controller에서 진행하므로, 새롭게 다시 호출을 요청한다.
		}else {
			mv.setViewName("error_page");
		}
		return mv;
	}
	
	/**
	 * board_update.do 페이지 호출
	 */
	@RequestMapping(value = "/board_update.do", method = RequestMethod.GET)
	public ModelAndView board_update(String bid) {
		ModelAndView mv = new ModelAndView();
		
		CgvBoardDAO dao = new CgvBoardDAO();
		CgvBoardVO vo = dao.select(bid);
		
		mv.addObject("vo", vo);
		mv.setViewName("/board/board_update");
		
		return mv;
	}
	
	/**
	 * board_update_check.do : 게시글 수정
	 */
	@RequestMapping(value = "/board_update_check.do", method = RequestMethod.POST)
	public ModelAndView board_update_check(CgvBoardVO vo) {
		ModelAndView mv = new ModelAndView();
		
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.update(vo);
		if(result == 1) {
			mv.setViewName("redirect:/board_list.do");
		}else {
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
	/**
	 * board_delete_check.do : 게시글 삭제
	 */
	@RequestMapping(value = "/board_delete_check.do", method = RequestMethod.POST)
	public ModelAndView board_delete_check(String bid) {
		ModelAndView mv = new ModelAndView();
		
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.delete(bid);
		if(result == 1) {
			mv.setViewName("redirect:/board_list.do");
		}else {
			mv.setViewName("error_page");
		}
		
		return mv;
	}
}
