package com.douzone.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/blog")
@Controller
public class BlogController {

	//{id}
	//{id}/{categoryNo}
	//{id}/{categoryNo}/{postNo}
	
	@RequestMapping("")
	public String index() {
		return "";
	}
}
