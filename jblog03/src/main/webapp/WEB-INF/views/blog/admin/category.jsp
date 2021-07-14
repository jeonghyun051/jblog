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
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js" type="text/javascript"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js" type="text/javascript"></script>
<script>
var listItemEJS = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/listitem-template.ejs"
});

$(() => {
	$("#add-form").submit(function(event){
		event.preventDefault();
		
		vo = {}
		
		vo.name = $("#name").val();
		vo.descs = $("#descs").val();
		vo.blogId = $("#blog-id").val();
		
		console.log("1",vo.name);
		console.log("2",vo.descs);
		console.log("3",vo.blogId);
		
		// 데이터 등록
		$.ajax({
			url: "${pageContext.request.contextPath }/blog/category/api/add",
			dataType: "json",
			type: "post",
			contentType: "application/json",   
			data: JSON.stringify(vo),
			success: function(response){
				var html = listItemEJS.render(response.data);
				$("#admin-cat").append(html);	 
			}
		});
	});
});

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/blog/admin/includes/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/blog/admin/includes/navigation.jsp" />
		      	<table id= "admin-cat" class="admin-cat">
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
							<td><a href="${pageContext.request.contextPath }/blog/${blogVo.id}/admin/category/delete/${category.no}"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>
						</tr>  
		      		</c:forEach>				  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form id="add-form" action="${pageContext.request.contextPath }/blog/${blogVo.id}/admin/category/add" method="POST">
      			<input id="blog-id" type="hidden" name="blogId" value="${blogVo.id }">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input id="name" type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input id="descs" type="text" name="descs"></td>
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