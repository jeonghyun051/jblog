package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
		
	public void insert(PostVo post) {
		postRepository.insert(post);
	}

	public Map<String, Object> findAll(Long categoryNo, Long postNo, String id) {
		if(postNo == 0L) { // 카테고리
			PostVo postVo = postRepository.defaultSelect(categoryNo,id);
			if(postVo == null || postVo.equals(null)) {
				Map<String,Object> map = new HashMap<>();
				map.put("postVo", postVo);
				return map;
			}
			List<PostVo> postList = postRepository.select(categoryNo,postVo.getNo(),id);
			Map<String,Object> map = new HashMap<>();
			map.put("postVo", postVo);
			map.put("postList", postList);
			System.out.println("서비스 실행1" + postVo.getNo());
			System.out.println("서비스 실행1" + postVo);
			System.out.println("서비스 실행1" + postList);
			return map;
		} else {
			PostVo postVo = postRepository.findById(postNo);
			List<PostVo> postList = postRepository.findAll(postVo.getNo(), categoryNo);
			Map<String,Object> map = new HashMap<>();
			map.put("postVo", postVo);
			map.put("postList", postList);		
			System.out.println("서비스 실행2" + postVo);
			System.out.println("서비스 실행2" + postList);
			return map;
		}
	}
}