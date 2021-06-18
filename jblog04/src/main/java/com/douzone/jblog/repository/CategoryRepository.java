package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;

	public void categoryDefaultInsert(CategoryVo categoryVo) {
		sqlSession.insert("category.categoryDefaultInsert",categoryVo);	
	}

	public List<CategoryVo> findAll(String id) {
		return sqlSession.selectList("category.findAll",id);
	}

	public void insert(CategoryVo categoryVo) {
		sqlSession.insert("category.insert",categoryVo);
	}

	public List<CategoryVo> findAllAndCount(String id) {
		return sqlSession.selectList("category.findAllAndCount",id);	
	}

	public void delete(Long no) {
		sqlSession.delete("category.delete",no);
		
	}
}