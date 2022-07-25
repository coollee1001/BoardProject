<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url var="root" value="/"/>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta charset="UTF-8">
<title>게시판 등록 프로그램</title>
</head>
<body>
	<form:form action='${root }board/modify_pro' method='post' modelAttribute="modifyContentDTO" enctype="multipart/form-data">
		<form:hidden path="board_index"/>
			
		<form:label path="board_date">작성날짜</form:label><br/>
		<form:input path="board_date" readonly='true'/>
		<br/><br/>
		
		<form:label path="board_subject">제목</form:label><br/>
		<form:input path="board_subject"/>
		<form:errors path="board_subject" style='color:red'/>
		<br/><br/>
		
		<form:label path="board_content">내용</form:label><br/>
		<form:textarea path="board_content" rows="10" style="resize:none"/>
		<form:errors path="board_content" style='color:red'/>
		<br/><br/>
		
		<label for="board_file">첨부 이미지</label><br/>
		<c:if test="${modifyContentDTO.board_file != null }">
			<img src="${root }resources/upload/${modifyContentDTO.board_file}" width="100%"/>	
			<form:hidden path="board_file"/>
			<br/>
		</c:if>
		<form:input path="upload_file" type='file' accept="image/*"/>
		<br/><br/>

		<form:button>수정완료</form:button>
		<a href="${root }board/read?board_index=${board_index}">취소</a>
			
	</form:form>
</body>
</html>