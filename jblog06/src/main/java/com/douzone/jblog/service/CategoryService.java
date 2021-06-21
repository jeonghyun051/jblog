package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryVo> findAll(String id) {
		return categoryRepository.findAll(id);
	}

	public void add(CategoryVo categoryVo) {
		categoryRepository.insert(categoryVo);
	}

	public List<CategoryVo> findAllAndCount(String id) {
		return categoryRepository.findAllAndCount(id);	
	}

	public int delete(Long no) {
		return categoryRepository.delete(no);
		
	}
}