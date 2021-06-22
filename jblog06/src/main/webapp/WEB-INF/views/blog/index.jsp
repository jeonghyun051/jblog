<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<style>
.delete { margin-left: 93%; }
</style>
</head>
<body>
	<div id="container">
	<c:import url="/WEB-INF/views/blog/admin/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${map.postVo.title }</h4>
					<p>
						${map.postVo.contents }>
					<p>
					<c:if test="${authUser.id eq blogVo.id }">
						<div>
						<form action="${pageContext.request.contextPath }/blog/${blogVo.id}/admin/delete/${map.postVo.no}" method="get">
							<input id="deleted" class="delete" type="button" value="삭제하기" onclick="button_event()">
						</form>
						</div>
					</c:if>
				</div>
				<br/>
				<ul class="blog-list">
					<c:forEach var="post" items="${map.postList }">
						<li><a href="${pageContext.request.contextPath }/blog/${blogVo.id }/${post.categoryNo }/${post.no }">${post.title }</a> <span>${post.regDate }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach var="categoryList" items="${categoryList }">
				<li><a href="${pageContext.request.contextPath }/blog/${blogVo.id }/${categoryList.no }">${categoryList.name }</a></li>
			</c:forEach>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/blog/admin/includes/footer.jsp" />
	</div>
</body>
<script>
	function button_event(){
		if (confirm("정말 삭제하시겠습니까?") == true){    //확인
			$("#deleted").attr("type","submit");	
		}else{   //취소
		    return;
		}
	}
</script>
</html>