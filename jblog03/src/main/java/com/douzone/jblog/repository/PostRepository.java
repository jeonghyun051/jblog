package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(PostVo post) {
			sqlSession.insert("post.insert",post);
	}

	public PostVo defaultSelect(Long categoryNo, String id) {
		Map<String,Object> map = new HashMap<>();
		map.put("categoryNo", categoryNo);
		map.put("id",id);
		return sqlSession.selectOne("post.defaultSelect",map);
	}

	public List<PostVo> select(Long categoryNo, Long postNo, String id) {
		Map<String,Object> map = new HashMap<>();
		map.put("categoryNo", categoryNo);
		map.put("postNo", postNo);
		map.put("id",id);
		return sqlSession.selectList("post.findAll",map);
	}

	public PostVo findById(Long no) {
		return sqlSession.selectOne("post.findById",no);
	}

	public List<PostVo> findAll(Long postNo, Long categoryNo) {
		Map<String,Long> map = new HashMap<>();
		map.put("postNo", postNo);
		map.put("categoryNo", categoryNo);	
		return sqlSession.selectList("post.findAll",map);
	}
}