package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NoticeController {
	/**
	 * notice_list.do 페이지 호출
	 */
	@RequestMapping(value = "/notice_list.do", method = RequestMethod.GET)
	public String notice_list() {
		return "/notice/notice_list";
	}
}
