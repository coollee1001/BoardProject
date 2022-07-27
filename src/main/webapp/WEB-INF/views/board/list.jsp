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
<link rel="stylesheet" href="${root }resources/css/page.css">
<title>게시판 등록 프로그램</title>
</head>
<body>
	<table class="w3-table-all w3-hoverable">
		<c:choose>
			<c:when test="${empty contentList}">
				<label>
					작성된 글이 없습니다
				</label>
			</c:when>
			<c:otherwise>
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
							<td><a href='${root }board/read?page=${page}&board_index=${obj.board_index}'>${obj.board_subject}</a></td>
							<td>${obj.board_date }</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:otherwise>
		</c:choose>		
		
	</table>
	<div align="right">
		<a href="${root }board/write">글쓰기</a>
	</div>
	
	<!-- 페이징 -->
	<div class="w3-center">
		<div class="w3-bar">
			<ul>
				<c:choose>
					<c:when test="${pageDTO.prevPage <= 0 }">
							<a href="#" class="w3-bar-item w3-button">이전</a>
					</c:when>
					<c:otherwise>
							<a href="${root }board/list?page=${pageDTO.prevPage}" class="w3-bar-item w3-button">이전</a>
					</c:otherwise>
				</c:choose>
				<c:forEach var='idx' begin="${pageDTO.min }" end='${pageDTO.max }'>
					<c:choose>
						<c:when test="${idx == pageDTO.currentPage }">
								<a href="${root }board/list?page=${idx}" class="w3-bar-item w3-button">${idx }</a>
						</c:when>
					<c:otherwise>
							<a href="${root }board/list?page=${idx}" class="w3-bar-item w3-button">${idx }</a>
					</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageDTO.max >= pageDTO.pageCnt }">
							<a href="#" class="w3-bar-item w3-button">다음</a>
					</c:when>
					<c:otherwise>
							<a href="${root }board/list?page=${pageDTO.nextPage}" class="w3-bar-item w3-button">다음</a>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</body>
</html>