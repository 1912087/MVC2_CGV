package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	/**
	 * footer.do 페이지 호출
	 */
	@RequestMapping(value = "/footer.do", method = RequestMethod.GET)
	public String footer() {
		return "footer";
	}
	
	/**
	 * hedaer.do 페이지 호출
	 */
	@RequestMapping(value = "/header.do", method = RequestMethod.GET)
	public String header() {
		return "header";
	}
	
	/**
	 * index.do 페이지 호출
	 */
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	
}
