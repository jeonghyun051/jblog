package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.vo.CategoryVo;

@RestController
@RequestMapping("/blog/category/api")		
public class BlogControllerApi {
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/add")
	public JsonResult add(@RequestBody CategoryVo vo) {
		categoryService.add(vo);
		return JsonResult.success(vo);
	}	
}