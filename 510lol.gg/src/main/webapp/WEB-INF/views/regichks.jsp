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
		alert("회원가입이 완료되었습니다.");
		location.href="login";
	</script>
</c:when>
<c:when test="${a == 2 }">
	<c:choose>
		<c:when test="${access_Token == null}">
			<script>
				alert("롤닉네임 인증이 필요합니다.");
				history.back();
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("롤닉네임 인증이 필요합니다.");
				history.back();
			</script>
		</c:otherwise>
	</c:choose>
	
		
</c:when>

<c:otherwise>
	<script>
		alert("등록된 아이디입니다.");
		location.href="naverlogin";
	</script>

</c:otherwise>
</c:choose>


</body>
</html>