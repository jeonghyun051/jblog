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
		System.out.println("id레파지토리:" +id);
		return sqlSession.selectList("category.findAll",id);
	}


}