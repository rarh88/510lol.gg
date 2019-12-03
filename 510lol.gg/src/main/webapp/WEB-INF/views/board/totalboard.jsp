<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>

<style>
body{
		background-color:#1a1d21;
	}

</style>

<script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

	function search() {
		var searchtitle = document.getElementById("searchtitle").value
		var searchvalue = document.getElementById("searchvalue").value
		location.href = "search?searchtitle=" + searchtitle
				+ "&searchvalue=" + searchvalue
	}

	function hit2up(a, b) {
		var form = {
			num : a,
			id : b
		}
		$.ajax({
			url : "hit2up",
			type : "GET",
			data : form,
			success : function(data) {
				$('#hit2update' + a).text(data);
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
	
	<c:choose>
		<c:when test="${start == null }">
			<c:set var="start" value="1" />
		</c:when>
		<c:otherwise>
			<c:set var="start" value="${start }" />
		</c:otherwise>
	</c:choose>


	<!-- 사용자 세션id 들어갈자리 -->
	<c:set var="id" value="${snick }" scope="session" />
	<!-- ================== -->

	<a href="0g">공지사항</a>
	<a href="champ">챔프정보</a>
	<a href="bug">버그제보</a>
	<a href="free">자유게시판</a>

	<h1>total</h1>
	<div>

		<table>

			<tr>
				<td>번호		<hr>	</td>
				<td>게시판	<hr>	</td>
				<td>제목		<hr>	</td>			
				<td>글쓴이	<hr>	</td>
				<td>등록일	<hr>	</td>
				<td>조회		<hr>	</td>
				<td colspan="2">추천수
					<hr>
				</td>
			</tr>

			<c:forEach var="bdto" items="${total}">
				<tr>
					<td>${bdto.num}</td>
					<td>${bdto.boardname}</td>
					<td><a href="view?num=${bdto.num}&idgroup=${bdto.idgroup}">${bdto.title}</a></td>
					<td>${bdto.id}</td>
					<td>${bdto.savedate}</td>
					<td>${bdto.hit}</td>
					<td colspan="2"><img src="resources/img/hit2up.png"
						onclick="hit2up('${bdto.num}','${sessionScope.id}')"
						style="height: 20px; width: 20px;"> <label
						id="hit2update${bdto.num}">${bdto.hit2}</label></td>
				</tr>
			</c:forEach>
		</table>

		<c:choose>
			<c:when test="${start > 1}">
				<button type="button"
					onclick="location.href='totalboard?stnum=${start-1 }'">이전
				</button>
			</c:when>
			<c:otherwise>
				<button type="button" disabled="disabled">이전</button>
			</c:otherwise>
		</c:choose>


		<c:forEach begin="1" end="${totalpage}" step="1" var="cnt">
			<a href="totalboard?stnum=${cnt}">[${cnt}]</a>
		</c:forEach>
		<c:choose>
			<c:when test="${start < totalpage}">
				<button type="button"
					onclick="location.href='totalboard?stnum=${start+1 }'">다음
				</button>
			</c:when>
			<c:otherwise>
				<button type="button" disabled="disabled">다음</button>
			</c:otherwise>
		</c:choose>
		${start } / ${totalpage } <br> 
		
		<input type="button" value="글쓰기"
			onclick="location.href='writeboard?boardname=total'"> 
			<input type="text" id="searchvalue"> 
			<input type="button" value="검색"	onclick="search()"> 
		<select id="searchtitle">
			<option value="자유게시판" selected="selected">자유게시판</option>
			<option value="공지사항">공지사항</option>
			<option value="버그제보">버그제보</option>
		</select>

	</div>


</body>
</html>