<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<% request.setCharacterEncoding("UTF-8"); %>
	<% response.setCharacterEncoding("UTF-8"); %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<fmt:requestEncoding value="utf-8"/>
<html>
<head>

<script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>
<script>
//	function updownck(){
//		var id = document.getElementById("id").value
//		var title = document.getElementById("title").value
//		var boardname = document.getElementById("boardname").value
//		var content = document.getElementById("content").value
		
//		location.href="saveboard?id="+id+"&title="+title+"&boardname="+boardname+"&content="+content
		
//	}

	var sel_file;
	   
	   $(document).ready(function(){
	      $('#report_img').on("change", selectImg);   
	   });
	   
	   function selectImg(e){
	      var files = e.target.files;
	      var filesArr = Array.prototype.slice.call(files);
	      
	      filesArr.forEach(function(f){
	         if(!f.type.match("image.*")){
	            alert("확장자는 이미지 확장자만 가능");
	            return;
	         }
	         sel_file=f;
	      
	         var reader = new FileReader();
	         reader.onload = function(e){
	            $('#img_report').attr("src", e.target.result);
	         }
	         reader.readAsDataURL(f);
	      });
	   }
	   
</script>

</head>
<body>
	<a href="totalboard">totalboard이동</a>
	<form name="fimeForm" method="post" action="updownck"
		enctype="multipart/form-data" accept-charset="UTF-8">
		<table border="1">
			<c:if test="${boardname != 'total'}">
				<input type="hidden" id="boardname" name="boardname" value="${boardname}">
			</c:if>

			<c:if test="${boardname eq 'total'}">
				<tr>
					<td>
					<select id="boardname" name="boardname">
							<option value="자유게시판" selected="selected">자유게시판</option>
							<option value="공지사항">공지사항</option>
							<option value="버그제보">버그제보</option>
					</select>
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td>작성자</td>
				<td><input type="text" name="id"
					value="${sessionScope.id}"  disabled>
					<input type="hidden" name="id" value="${sessionScope.id}">
					</td>
			</tr>
			<tr>
				<td>제 목</td>
				<td><c:choose>
						<c:when test="${title == null}">
							<input type="text" name="title" id="title">
						</c:when>
						<c:otherwise>
							<input type="text" name="title" id="title" value="${title}">
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td colspan="2"><c:choose>
					<c:when test="${content == null }">
						<textarea rows="6" cols="50" name="content" id="content"></textarea>
					 </c:when>
					 	
					 <c:otherwise>
						<textarea rows="6" cols="50" name="content" id="content">${content }</textarea>
					</c:otherwise>
					</c:choose>
					</td>
			</tr>
			<tr>
				<td><input type="file" id="report_img" name="file" /></td>
				<td rowspan="2"><c:choose>
						<c:when test="${fileurl == null}">
							<img id="img_report" style="height: 80px; width: 80px;">
						</c:when>
						<c:otherwise>
							<img src="png/${fileurl}" style="height: 80px; width: 80px;">
						</c:otherwise>
					</c:choose></td>

			</tr>
		</table>
			<input type="submit" value="완료">
	</form>
</body>
</html>