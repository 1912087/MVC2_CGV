package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.vo.CgvMemberVO;

@Controller
public class LoginController {
	
	/**
	 * loginCheckController.do 
	 */
	@RequestMapping(value = "/loginCheckController.do", method = RequestMethod.POST)
	public ModelAndView loginCheckController(CgvMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.login(vo);
		if(result == 1) {
			mv.addObject("login_result", "ok");
			mv.setViewName("index");
		}else {
			mv.addObject("login_result", "fail");
			mv.setViewName("/login/login");
		}
		return mv;
	}
	
	/**
	 * login.do 페이지 호출
	 */
	//Mapping에서 /login.do는 context Path 뒤에 오는 것으로, mycgv/login.do가 됨
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		return "login/login";	 //login만 쓰면 login.jsp가 된다. login.jsp는 login폴더 안에 있으니 정확하게 명시해 줄 것
	}
}
