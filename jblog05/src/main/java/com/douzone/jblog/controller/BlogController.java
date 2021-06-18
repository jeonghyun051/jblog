package com.douzone.jblog.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@RequestMapping("/blog/{id:(?!assets).*}")			
@Controller
public class BlogController {			
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUploadService fileUploadService;

	//{id}/{categoryNo}/{postNo}
	@RequestMapping({"","/{pathNo1}","/{pathNo1}/{pathNo2}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2,
			Model model,
			HttpSession session) {
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) { // null이 아니다.
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		Map<String, Object> map = postService.findAll(categoryNo, postNo, id);
	
		ServletContext application = session.getServletContext();
		application.setAttribute("title", blogService.finById(id).getTitle());
		
		model.addAttribute("map",map);
		model.addAttribute("blogVo",blogService.finById(id));
		model.addAttribute("categoryList",categoryService.findAll(id));
		
		return "blog/index";
	}
	
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id, Model model) {
		model.addAttribute("blogVo",blogService.finById(id));
		return "blog/admin/basic";
	}
	
	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String upload(@PathVariable("id") String id, @ModelAttribute BlogVo blogVo, @RequestParam("file") MultipartFile file, Model model) {
		String url = fileUploadService.restore(file);
		if (url == null || blogVo.getTitle().isEmpty() == true) {
			return "blog/admin/basic";
		}
		blogVo.setLogo(url);
		blogService.modify(blogVo);

		return "blog/admin/basic";
	}
	
	@RequestMapping("/admin/category")
	public String adminCategory(@ModelAttribute BlogVo blogVo, Model model) {
		model.addAttribute("categoryList",categoryService.findAllAndCount(blogVo.getId())); 
		return "blog/admin/category";
	}
	
	@RequestMapping(value = "/admin/category/add", method = RequestMethod.POST)
	public String addCategory(@ModelAttribute BlogVo blogVo, CategoryVo categoryVo,Model model,@PathVariable String id) {
		categoryService.add(categoryVo);
		model.addAttribute("categoryList",categoryService.findAllAndCount(blogVo.getId()));
		return "redirect:/blog/{id}/admin/category";
	}	
	
	@RequestMapping("/admin/write")
	public String write(@ModelAttribute BlogVo blogVo, Model model) {
		model.addAttribute("categoryList",categoryService.findAll(blogVo.getId()));
		return "blog/admin/write";
	}
	
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BlogVo blogVo, BindingResult result, PostVo post, Model model, @PathVariable String id) {
		postService.insert(post);
		model.addAttribute("categoryList",categoryService.findAll(blogVo.getId()));
		return "blog/admin/write";
	}
	
	@RequestMapping(value = "/admin/category/delete/{no}", method = RequestMethod.GET)
	public String delete(@ModelAttribute BlogVo blogVo, @PathVariable Long no, Model model, @PathVariable String id) {
		categoryService.delete(no);
		return "redirect:/blog/{id}/admin/category";
	}
}