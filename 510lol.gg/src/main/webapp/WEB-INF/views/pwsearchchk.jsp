<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${pw == null }">
		<script>
			alert("가입된ID가 아니거나 회원정보가 틀립니다.")
			location.href="pwsearch";
		</script>
	</c:when>
	<c:otherwise>
	 	찾으시는PW는  ${pw } 입니다.
		<a href="login">로그인하러가기</a>
	</c:otherwise>
	



</c:choose>

</body>
</html>