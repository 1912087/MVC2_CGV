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
	 * login.do ������ ȣ��
	 */
	//Mapping���� /login.do�� context Path �ڿ� ���� ������, mycgv/login.do�� ��
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		return "login/login";	 //login�� ���� login.jsp�� �ȴ�. login.jsp�� login���� �ȿ� ������ ��Ȯ�ϰ� ����� �� ��
	}
}
