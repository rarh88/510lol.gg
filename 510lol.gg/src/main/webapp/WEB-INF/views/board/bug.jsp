<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>

<script>

function search(a) {
	var searchtitle = document.getElementById("searchtitle").value
	var searchvalue = document.getElementById("searchvalue").value
	location.href = "search?searchtitle=" + searchtitle
			+ "&searchvalue=" + searchvalue+"&boardname="+a
}

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
	<h1>버그제보</h1>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div>
	
	<c:choose>
		<c:when test="${searchvalue == null }">
			<c:set var="searchvalue" value="버그제보" />
		</c:when>
		<c:otherwise>
			<c:set var="searchvalue" value="${searchvalue}" />
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${start == null }">
			<c:set var="start" value="1" />
		</c:when>
		<c:otherwise>
			<c:set var="start" value="${start }" />
		</c:otherwise>
	</c:choose>
	
	
	<a href="totalboard">totalboard이동</a>
		<table border="1">
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>글쓴이</td>
				<td>등록일</td>
				<td>조회</td>
				<td colspan="2">추천수</td>
			</tr>

			<c:forEach var="bdto" items="${buglist }">
				<c:if test="${bdto.step == 0 }">
					<tr>
						<td>${bdto.num}</td>
						<td><a style="text-decoration:none" href="view?num=${bdto.num}&idgroup=${bdto.idgroup}">${bdto.title}</a></td>
						<td>${bdto.id}</td>
						<td>${bdto.savedate}</td>
						<td>${bdto.hit}</td>
						<td colspan="2">
							<img src="resources/img/hit2up.png" onclick="hit2up('${bdto.num}','${sessionScope.id}')" 
							style="height: 20px; width: 20px;">
						<label id="hit2update${bdto.num}">${bdto.hit2}</label></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		
		<c:choose>
			<c:when test="${start > 1}">
				<button type="button"
					onclick="location.href='0g?stnum=${start-1 }'">이전
				</button>
			</c:when>
			<c:otherwise>
				<button type="button" disabled="disabled">이전</button>
			</c:otherwise>
		</c:choose>

		<c:forEach begin="1" end="${totalpage}" step="1" var="cnt">
			<a href="0g?stnum=${cnt}">[${cnt}]</a>
		</c:forEach>
		<c:choose>
			<c:when test="${start < totalpage}">
				<button type="button"
					onclick="location.href='0g?stnum=${start+1 }'">다음
				</button>
			</c:when>
			<c:otherwise>
				<button type="button" disabled="disabled">다음</button>
			</c:otherwise>
		</c:choose>
		${start } / ${totalpage } <br>
		
		
		<form action="search">
		<input type="button" value="글쓰기"
			onclick="location.href='writeboard?boardname=버그제보'"> 
		<input type="hidden" name="boardname" value="버그제보">
		<input type="text" name="searchvalue"> 
		<input type="submit" value="검색"	>
		<select name="searchtitle">
			<option value="title" selected="selected">제목</option>
			<option value="id">글쓴이</option>
		</select>
		</form>

	</div>
</body>
</html>