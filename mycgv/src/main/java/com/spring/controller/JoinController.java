package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.vo.CgvMemberVO;

@Controller
public class JoinController {
	//Controller로 만드는 방법
	//1. @Controller 어노테이션 선언
	//2. @RequestMapping 선언 후 Mapping 값과 method 설정
	//3. view name을 반환할 메소드 생성
	
	@RequestMapping(value = "/joinCheckController.do", method = RequestMethod.POST)
	public ModelAndView joinController(CgvMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.insert(vo);
		if(result == 1) {
			mv.addObject("join_result", "ok");
			mv.setViewName("/login/login");
		}else {
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String join() {
		return "/join/join";
	}
}
