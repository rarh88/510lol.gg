<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
	<c:forEach var="dto" items="${leagueexp }">
		<tr>
			<td>${dto.queueType }</td>
			<td>${dto.summonerName }</td>
			<td>${dto.tier }</td>
			<td>${dto.rank }</td>
			<td>${dto.leaguePoints }</td>
			<td>${dto.veteran }</td>
			<td>${dto.wins }</td>
			<td>${dto.losses }</td>
		</tr>
	
	</c:forEach>
		
</table>

</body>
</html>