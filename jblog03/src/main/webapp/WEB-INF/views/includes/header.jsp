<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h1 class="logo"
	style="background-image: url(${pageContext.request.contextPath}/assets/images/logo.jpg)">JBlog</h1>
<ul class="menu">
	<c:choose>
		<c:when test="${empty authUser }"> <!-- authUser를 null로 비교하면 안됨 -->
			<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
			<li><a href="${pageContext.request.contextPath }/user/join">회원가입</a></li>
			<li><a href="${pageContext.request.contextPath }/user/login">내블로그</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath }/blog/${authUser.id }/">내블로그</a></li>
		</c:otherwise>
	</c:choose>

</ul>