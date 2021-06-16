package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public void blogDefaultInsert(BlogVo vo) {
		sqlSession.insert("blog.blogDefaultInsert",vo);
	}

	public BlogVo findById(String id) {
		return sqlSession.selectOne("blog.findById",id);
	}
}