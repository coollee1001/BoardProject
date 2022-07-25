<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url var="root" value="/"/>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<meta charset="UTF-8">
<title>게시판 등록 프로그램</title>
</head>
<body>
	<form:form action='${root }board/write_pro' method='post' modelAttribute="writeContentDTO" enctype="multipart/form-data">

		<form:label path="board_subject">제목</form:label><br/>
		<form:input path="board_subject"/>
		<form:errors path='board_subject' style='color:red' />
		<br/><br/>
		
		<form:label path="board_content">내용</form:label><br/>
		<form:textarea path="board_content" rows="10" style="resize:none"/>
		<form:errors path='board_content' style='color:red'/>
		<br/><br/>
		
		<form:label path="upload_file">첨부 이미지</form:label><br/>
		<form:input type='file' path='upload_file' accept="image/*"/>
		<br/><br/>
		
		<form:button>작성하기</form:button>		
		<a href="${root }board/list">취소</a>
	</form:form>
	
</body>
</html>