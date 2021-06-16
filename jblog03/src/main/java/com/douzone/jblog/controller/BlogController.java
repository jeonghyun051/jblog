package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;

@RequestMapping("/blog/{id:(?!assets).*}")
@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;

	//{id}
	//{id}/{categoryNo}
	//{id}/{categoryNo}/{postNo}

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
		
		System.out.println("id:"+id);
		System.out.println("category:" + categoryNo);
		System.out.println("postNo:" + postNo);
		
		BlogVo blogVo = blogService.finById(id);
		List<CategoryVo> categoryList = categoryService.findAll(id);
		
		model.addAttribute("blogVo",blogService.finById(id));
		model.addAttribute("categoryList",categoryService.findAll(id));
		

		return "blog/index";
	}
	
	@ResponseBody
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id) {
		System.out.println("id:"+id);
		return "blog/admin/basic";
	}
}
