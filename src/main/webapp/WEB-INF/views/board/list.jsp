<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="root" value="/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>게시판 등록 프로그램</title>
</head>
<body>
	<table class="w3-table-all w3-hoverable">
		<thead>
			<tr >
				<th>글번호</th>
				<th>제목</th>
				<th>작성날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var='obj' items="${contentList}">
				<tr>
					<td>${obj.board_index }</td>
					<td><a href='${root }board/read?board_index=${obj.board_index}'>${obj.board_subject}</a></td>
					<td>${obj.board_date }</td>
				</tr>
		</c:forEach>
		</tbody>
	</table>
	<div align="right">
		<a href="${root }board/write">글쓰기</a>
	</div>
</body>
</html>