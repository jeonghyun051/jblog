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
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/blog/admin/includes/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/blog/admin/includes/navigation.jsp" />
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:forEach var="category" items="${categoryList }" varStatus="status">
		      			<tr>
							<td>${status.count }</td>
							<td>${category.name }</td>
							<td>${category.count }</td>
							<td>${category.descs }</td>
							<td>
							<c:choose>
								<c:when test="${category.count == 0}">
									<a href="${pageContext.request.contextPath }/blog/${blogVo.id}/admin/category/delete/${category.no}">
									<img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a>
								</c:when>
								<c:otherwise>
									글이 존재합니다.
								</c:otherwise>
							</c:choose>
							</td>
						</tr>  
		      		</c:forEach>				  
				</table>   			
 
	   			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form action="${pageContext.request.contextPath }/blog/${blogVo.id}/admin/category/add" method="POST">
      			<input type="hidden" name="blogId" value="${blogVo.id }">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="descs"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table>
		      	</form> 
			</div>
		</div>
		<c:import url="/WEB-INF/views/blog/admin/includes/footer.jsp" />
	</div>
</body>
</html>