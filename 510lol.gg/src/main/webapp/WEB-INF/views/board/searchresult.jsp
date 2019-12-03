<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>
<script>

function hit2up(a,b){
	var form = {num:a,id:b}
$.ajax({
	url : "hit2up",
	type : "GET",
	data : form,
	success : function(data) {

		$('#hit2update'+a).text(data);
	},
	error : function() {
		alert("문제가 발생했습니다.")
	}
})
}

</script>
</head>
<body>
<jsp:include page="../css/header2.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<h1>${boardname} 검색결과</h1>
	<a href="totalboard">totalboard이동</a>
	<div>
	
	<c:choose>
		<c:when test="${start == null }">
			<c:set var="start" value="1" />
		</c:when>
		<c:otherwise>
			<c:set var="start" value="${start }" />
		</c:otherwise>
	</c:choose>
	
		<table border="1">
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>게시판</td>
				<td>글쓴이</td>
				<td>등록일</td>
				<td>조회</td>
				<td>추천수</td>
			</tr>
			
			<c:forEach var="bdto" items="${searchresult }">
				<tr>
					<td>${bdto.num}</td>
					<td><a href="view?num=${bdto.num}&idgroup=${bdto.idgroup}">${bdto.title}</a></td>
					<td>${bdto.boardname }</td>
					<td>${bdto.id}</td>
					<td>${bdto.savedate}</td>
					<td>${bdto.hit}</td>
					<td>
						<img src="resources/img/hit2up.png" onclick="hit2up('${bdto.num}','${sessionScope.id}')" 
							style="height: 20px; width: 20px;">
						<label id="hit2update${bdto.num}">${bdto.hit2}</label>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<c:choose>
			<c:when test="${start > 1}">
				<button type="button"
					onclick="location.href='search?stnum=${start-1 }&searchvalue=${searchvalue }&searchtitle=${searchtitle }&boardname=${boardname }'">이전
				</button>
			</c:when>
			<c:otherwise>
				<button type="button" disabled="disabled">이전</button>
			</c:otherwise>
		</c:choose>

		<c:forEach begin="1" end="${totalpage}" step="1" var="cnt">
			<a href="search?stnum=${cnt}&searchvalue=${searchvalue }&searchtitle=${searchtitle }&boardname=${boardname }">[${cnt}]</a>
		</c:forEach>
		<c:choose>
			<c:when test="${start < totalpage}">
				<button type="button"
					onclick="location.href='search?stnum=${start+1 }&searchvalue=${searchvalue }&searchtitle=${searchtitle }&boardname=${boardname }'">다음
				</button>
			</c:when>
			<c:otherwise>
				<button type="button" disabled="disabled">다음</button>
			</c:otherwise>
		</c:choose>
		${start } / ${totalpage } <br> 
		
		<input type="button" value="뒤로" onclick="location.href='totalboard'">
	</div>
</body>
</html>