<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script>


</script>

</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<form action="update">
		<table border="1">
			<tr>
				<td>글 번호</td>
				<td>${bdto.num }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${bdto.hit }</td>
			</tr>
			<tr>
				<td>추천수</td>
				<td>${bdto.hit2}</td>
			</tr>

			<tr>
				<td>작성자</td>
				<td>${bdto.id }</td>
			</tr>
			<tr>
				<td>제 목</td>
				<td><input type="text" name="title" value="${bdto.title }"></td>
			</tr>
			<tr>
				<td>내 용</td>
				<td><textarea rows="6" name="content" cols="50">${bdto.content }</textarea></td>
			</tr>

		</table>
		<input type="hidden" value="${bdto.num }" name="num"> 
		<input type="hidden" value="${bdto.boardname }" name="boardname"> 
		<input type="submit" value="확인">

		<c:choose>
			<c:when test="${bdto.boardname eq '자유게시판' }">
				<input type="button" value="취소" onclick="location.href='free'">
			</c:when>
			<c:when test="${bdto.boardname eq '공지사항' }">
				<input type="button" value="취소" onclick="location.href='0g'">
			</c:when>
			<c:otherwise>
				<input type="button" value="취소" onclick="location.href='totalboard'">
			</c:otherwise>
		</c:choose>
	</form>
</body>
</html>