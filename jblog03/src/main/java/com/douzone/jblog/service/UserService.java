package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void join(UserVo userVo) {
		userRepository.insert(userVo);
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setTitle(userVo.getId()+"님의 블로그");
		blogVo.setLogo("/assets/images/profile.jpg");
		blogRepository.blogDefaultInsert(blogVo);

		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName("기본 카테고리");
		categoryVo.setDesc("카테고리 설명");
		categoryVo.setBlogId(userVo.getId());
		categoryRepository.categoryDefaultInsert(categoryVo);
	}
	
	public UserVo login(String id, String password) {
		return userRepository.login(id,password);	
	}
}