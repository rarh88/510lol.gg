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
<c:when test="${a == 1 }">
	<script>
		alert("로그인이 되었습니다.");
		location.href="login";
		
	</script>
</c:when>
<c:otherwise>
	<script>
		alert("아이디또는 비밀번호가 틀립니다.");
		location.href="login";
	</script>

</c:otherwise>
</c:choose>


</body>
</html>