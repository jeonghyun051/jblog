package com.douzone.jblog.controller;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;

//{id}/{categoryNo}/{postNo}
@RequestMapping("/blog/{id:(?!assets).*}")			
@Controller
public class BlogController {			
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping({"","/{pathNo1}","/{pathNo1}/{pathNo2}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2,
			Model model) {
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) { // null이 아니다.
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		model.addAttribute("blogVo",blogService.finById(id));
		model.addAttribute("categoryList",categoryService.findAll(id));
		
		return "blog/index";
	}
	
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id, Model model) {
		System.out.println("id:"+id);
		model.addAttribute("blogVo",blogService.finById(id));
		return "blog/admin/basic";
	}
	
	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String upload(@PathVariable("id") String id, BlogVo blogVo, @RequestParam("file") MultipartFile file, Model model) {
		System.out.println("실행됨?");
		String url = fileUploadService.restore(file);
		if (url == null || blogVo.getTitle().isEmpty() == true) {
			return "blog/admin/basic";
		}
		blogVo.setLogo(url);
		blogService.modify(blogVo);
	
		model.addAttribute("blogVo",blogService.finById(id));
		return "blog/admin/basic";
	}
}