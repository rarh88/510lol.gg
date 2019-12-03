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
	${userId }<br>
	${access_Token }<br>
	${nickname }
	
	<c:choose>
		<c:when test="${sid != null }">
			<script>
				alert("환영합니다!");
				location.href="login";
				
			</script>
		
		</c:when>
		
		
	
	</c:choose>
	




</body>
</html>