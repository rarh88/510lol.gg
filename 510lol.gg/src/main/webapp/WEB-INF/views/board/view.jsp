<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type ="text/javascript" src="resources/jquery-3.2.1.min.js"></script>

<script type="text/javascript">

	
	function filedelete(a,b){
		var form = {num:a,idgroup:b}
	$.ajax({
		url : "filedelete",
		type : "GET",
		data : form,
		success : function(data) {
			 $('#img').attr("src", "");
		},
		error : function() {
			alert("문제가 발생했습니다.")
		}
	})
	}
	
	function replywrite() {
		if (document.getElementById("replywrite").style.display == 'none') {
			document.getElementById("replywrite").style.display = 'block';
		} else {
			document.getElementById("replywrite").style.display = 'none';
		}
	}

	function reply2write(a){
		if(document.getElementById("re2text"+a).style.display == 'none'){
			document.getElementById("re2text"+a).style.display = 'block';
		}else{
			document.getElementById("re2text"+a).style.display='none';
		}
	}
	
	function reply2(a,b,c,d,e,f,g) {
			var re2content = document.getElementById("re2content").value;
			var fileurl = document.getElementById("fileurl").value;
			var form = {id:a,title:b,boardname:c,idgroup:d,step:e,indent:f,content:re2content}
			location.href="reply2?id="+a+"&title="+b+"&boardname="+c+"&idgroup="+
					d+"&step="+e+"&indent="+f+"&content="+re2content+"&num="+g+"&fileurl="+fileurl
					
	}
	
	function replydel(a){
		var form = {replynum:a}
	$.ajax({
		url : "replydel",
		type : "GET",
		data : form,
		success : function(data) {
			if(data == '1'){
				$('#reply'+a).remove();
			}else{ alert(data)}
		},
		error : function() {
			alert("문제가 발생했습니다.")
		}
	})
	}
	
	
	function replyhit2up(a,b,c){
		var form = {num:a,id:b,replynum:c}
	$.ajax({
		url : "replyhit2up",
		type : "GET",
		data : form,
		success : function(data) {

			$('#replyhit2update'+c).text(data);
			console.log(data);
			console.log("replyhit2update"+c);	
		},
		error : function() {
			alert("문제가 발생했습니다.")
		}
	})
	}
	function replyrewrite(a,b){
		
		
		$("#rewrite").html("<input type='text' id='recontent' value="+a+">")
		$("#rewritebtn").html("<input type='button' value='저장' onclick='replyupdate(${view.num},${view.idgroup},"+b+")'>")
	}
	
	function replyupdate(a,b,c){
		var content = document.getElementById("recontent").value
		location.href="replyupdate?content="+content+"&num="+a+"&idgroup="+b+"&replynum="+c
	}

</script>
</head>
<body>
<jsp:include page="../css/header2.jsp"/>
	<a href="totalboard">total이동</a>
	
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
				<td>글 번호</td>
				<td>${view.num }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${view.hit }</td>
			</tr>
			<tr>
				<td>추천수</td>
				<td>${view.hit2}</td>
			</tr>

			<tr>
				<td>작성자</td>
				<td>${view.id }</td>
			</tr>
		<tr>
			<td>제 목</td>
			<td><input disabled type="text" value="${view.title }"></td>
		</tr>
		<tr>
			<td>내 용</td>
			<td><textarea disabled rows="6" cols="50">${view.content }</textarea></td>
		</tr>
			<tr>
		<c:if test="${view.id eq sessionScope.id && img.imgname !=null}">
				<td><input type="button" value="파일삭제"
					onclick="filedelete('${view.num}','${view.idgroup}')"></td>
		</c:if>
				<td><img id="img" src="png/${img.imgname}"
					onclick="location.href='filedown?num=${view.num}&idgroup=${view.idgroup}'"
					style="height: 80px; width: 80px;"></td>
			</tr>
	</table>
		<input type="hidden"  name="num" value="${view.num }"> 
		<input type="hidden"  name="idgroup" value="${view.idgroup }">
	<c:if test="${view.id eq sessionScope.id }">
		<input type="button" value="삭제"
			onclick="location.href='delete?num=${view.num}&idgroup=${view.idgroup}'">
		<input type="button" value="수정"
			onclick="location.href='updateview?num=${view.num}'">
		<input type="button" value="댓글작성"
			onclick="replywrite()">
	</c:if>
	
		<table border="1">
		<tr>
			<td>작성자</td>
			<td>내용</td>
			<td>추천수</td>
			<td>step</td>
			<td>indent</td>
			<td></td>
			<td></td>
		</tr>
		<c:forEach var="replylist" items="${replyview }">
			<tr id="reply${replylist.replynum}">
				<td>${replylist.id }</td>
				<td id="rewrite"><a href="#" onclick="reply2write('${replylist.replynum}')">${replylist.content }</a></td>
				<td>
				<img src="resources/img/hit2up.png" 
				onclick="replyhit2up('${replylist.num}','${sessionScope.id }','${replylist.replynum}')"
						style="height: 20px; width: 20px;">
				<label id="replyhit2update${replylist.replynum}">${replylist.hit2}</label></td>	
				<td>${replylist.step }</td>
				<td>${replylist.indent }</td>
				<td>
					<c:if test="${sessionScope.id eq replylist.id }">
						<img src="resources/img/x.jpg" onclick="replydel(${replylist.replynum },${replylist.step},${replylist.indent},${replylist.idgroup} )"
							style="height:20px; width: 20px;">
					</c:if>
				</td>
				<td id="rewritebtn">
					<c:if test="${sessionScope.id eq replylist.id }">
						<input type="button" value="수정"
						onclick="replyrewrite('${replylist.content}',${replylist.replynum })">
					</c:if>
				</td>
			</tr>
			<tr id="re2text${replylist.replynum}" style="display: none">
				<td>
					<input type="text" id="re2content">
					<input type="button" value="작성" 
					onclick="reply2('${sessionScope.id}','${replylist.title}',
					'${replylist.boardname}','${replylist.idgroup }',
					'${replylist.step}','${replylist.indent }','${view.num}')">
					<input type="button" value="접기" onclick="reply2wright(${replylist.step})">
				</td>
			</tr>
		
		</c:forEach>
	</table>

	<form id ="replywrite" action="reply" style="display:none">
		<input type="hidden" id="fileurl" name="fileurl" value="${fileurl }"> 
		<input type="hidden"  name="num" value="${view.num }"> 
		<input type="hidden" name="id" value="${sessionScope.id }"> 
		<input type="hidden" name="title" value="${view.title }"> 
		<input type="hidden" name="boardname" value="${view.boardname }"> 
		<input type="hidden" name="idgroup" value="${view.idgroup }"> 
		<input type="hidden" name="step" value="${view.step }"> 
		<input type="hidden"name="indent" value="${view.indent }"> 
		<input type="text" name="content" placeholder="댓글작성">
		<input type="submit" value="작성">
	</form>
	
	<c:choose>
			<c:when test="${start > 1}">
				<button type="button"
					onclick="location.href='view?stnum=${start-1 }&num=${view.num}&idgroup=${view.idgroup}'">이전
				</button>
			</c:when>
			<c:otherwise>
				<button type="button" disabled="disabled">이전</button>
			</c:otherwise>
		</c:choose>

		<c:forEach begin="1" end="${totalpage}" step="1" var="cnt">
			<a href="view?stnum=${cnt}&num=${view.num}&idgroup=${view.idgroup}">[${cnt}]</a>
		</c:forEach>
		
		<c:choose>
			<c:when test="${start < totalpage}">
				<button type="button"
					onclick="location.href='view?stnum=${start+1 }&num=${view.num}&idgroup=${view.idgroup}'">다음
				</button>
			</c:when>
			<c:otherwise>
				<button type="button" disabled="disabled">다음</button>
			</c:otherwise>
		</c:choose>
		${start } / ${totalpage } <br>

	<c:if test="${view.boardname eq '자유게시판' }">
		<input type="button" value="뒤로가기" onclick="location.href='free'">
	</c:if>
	<c:if test="${view.boardname eq '공지사항' }">
		<input type="button" value="뒤로가기" onclick="location.href='0g'">
	</c:if>

</body>
</html>