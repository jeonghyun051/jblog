<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="admin-menu">
	<li class="selected"><a href="${pageContext.request.contextPath }/blog/${blogVo.id }/admin/basic">기본설정</a></li>
	<li>카테고리</li>
	<li><a href="">글작성</a></li>
</ul>