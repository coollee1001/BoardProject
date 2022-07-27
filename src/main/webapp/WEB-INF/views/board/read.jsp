<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<div>
		<label for="board_date">작성날짜</label><br/>
		<input type="text" id="board_date" name="board_date" value="${readContentDTO.board_date }" disabled="disabled"/>
		<br/><br/>
		<label for="board_subject">제목</label><br/>
		<input type="text" id="board_subject" name="board_subject" value="${readContentDTO.board_subject }" disabled="disabled"/>
		<br/><br/>
		<label for="board_content">내용</label><br/>
		<textarea id="board_content" name="board_content" style="resize:none" disabled="disabled">${readContentDTO.board_content}</textarea>
		<br/><br/>
		<c:if test="${readContentDTO.board_file != null }">
			<label for="board_file">첨부 이미지</label><br/>
			<img src="${root }resources/upload/${readContentDTO.board_file}"/>
			<br/><br/>						
		</c:if>
		<label for="board_file">첨부 엑셀 리스트</label><br/><a href="file/exceldownload?fileName=${readContentDTO.board_excelFile}">다운로드</a>
			<table>
				<tbody>
				<tr>
					<th>이름</th>
					<th>전화번호</th>
					<th>주소</th>
				</tr>
					<c:forEach var='obj' items="${readExcelContentDTO}">
						<tr>
							<td>${obj.excel_name }</td>
							<td>${obj.excel_phone}</td>
							<td>${obj.excel_address }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		<br/><br/>
		<br/>
			<a href="${root }board/list?page=${page}">목록보기</a>
			<a href="${root }board/modify?board_index=${board_index}&page=${page}">수정하기</a>
			<a href="${root }board/delete?board_index=${board_index}&page=${page}">삭제하기</a>
	</div>
</body>
</html>