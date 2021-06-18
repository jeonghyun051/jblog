<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
</head>
<body>
<div class="center-content">
	<c:import url="/WEB-INF/views/includes/header.jsp" />
		<form:form modelAttribute="userVo" class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath }/user/join" onsubmit="return valid()">
			<label class="block-label" for="name">이름</label>
			<!-- <input id="name"name="name" type="text" value="">  -->
			<form:input path="name" />
			<p style="color:#f00; text-align:left; padding-left:0">
				<spring:hasBindErrors name="userVo">
				   <c:if test="${errors.hasFieldErrors('name') }">
				        <spring:message code="${errors.getFieldError('name').codes[0] }" />
				   </c:if>
				</spring:hasBindErrors>
			</p>   
			
			<label class="block-label" for="blog-id">아이디</label>
			<!-- <input id="blog-id" name="id" type="text"> -->
			<form:input id="blog-id" path="id" /> 
			<input id="btn-checkemail" type="button" onClick="idCheck()" value="id 중복체크">
			<p style="color:#f00; text-align:left; padding-left:0">
				<spring:hasBindErrors name="userVo">
				   <c:if test="${errors.hasFieldErrors('id') }">
				        <spring:message code="${errors.getFieldError('id').codes[0] }" />
				   </c:if>
				</spring:hasBindErrors>
			</p> 

			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<form:input path="password" />
			<p style="color:#f00; text-align:left; padding-left:0">
				<spring:hasBindErrors name="userVo">
				   <c:if test="${errors.hasFieldErrors('password') }">
				        <spring:message code="${errors.getFieldError('password').codes[0] }" />
				   </c:if>
				</spring:hasBindErrors>
			</p> 
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
<script>
 	var isChecking = false;
 	function valid(){
 		if(isChecking == false){
 			alert("아이디 중복체크를 해주세요");
 		}

 		return isChecking;
 	}
	
	function idCheck(){
		var id = $("#blog-id").val();
		console.log(id);
		if(id == "" || id == null){
			alert("아이디를 입력해주세요");
			isChecking = false;
			return;
		}
		
		$.ajax({
			url: "/jblog05/user/checkId?id="+id,
			type: "get",
			dataType: "json",
			error: function(xhr, status, e){
				console.error(status, e);
			},
			success: function(response){
				console.log(response);
				
			if(response.result != "success"){
				console.error(response.message);
				return;
			}
			
			if(response.data){
				alert("존재하는 아이디입니다. 다른 아이디를 사용하세요.");
				$("#blog-id").val("");
				$("#blog-id").focus();
				isChecking = false;
				return;
			}
				
			isChecking = true;
			$("#blog-id").attr("readonly","readonly");
			alert("해당 아이디를 사용할 수 있습니다.")
			}
		});
	}
</script>
</html>
