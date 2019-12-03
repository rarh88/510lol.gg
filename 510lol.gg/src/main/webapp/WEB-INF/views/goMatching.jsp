<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="champ1" value="${selectChamp1 }"/>
<c:set var="champ2" value="${selectChamp2 }"/>
<c:set var="champ3" value="${selectChamp3 }"/>
<h1>최근 10판 기준</h1>
	<table>
		<tr>
			<th>챔피언</th>
		   	<th>승   률</th>
			<th>KDA</th>
		</tr>
		<tr>
		<c:choose>
			<c:when test="${champ1!=null }">
				<td><img style="width:100px; height:100px;" src="resources/img/${champ1.championId}.png"></td>
				<td>${champ1.winrate}%</td>
				<td> ${champ1.kda }</td>
			</c:when>
			<c:otherwise>
				<td></td>
				<td>전적없음</td>
				<td></td>
			</c:otherwise>
		</c:choose>
		</tr>
		<tr>
		<c:choose>
			<c:when test="${champ2!=null }">
				<td><img style="width:100px; height:100px;" src="resources/img/${champ2.championId}.png"></td>
				<td>${champ2.winrate}%</td>
				<td> ${champ2.kda }</td>
			</c:when>
			<c:otherwise>
				<td></td>
				<td>전적없음</td>
				<td></td>
			</c:otherwise>
		</c:choose>
		</tr>
		<tr>
			<c:choose>
			<c:when test="${champ3!=null }">
				<td><img style="width:100px; height:100px;" src="resources/img/${champ3.championId}.png"></td>
				<td>${champ3.winrate}%</td>
				<td> ${champ3.kda }</td>
			</c:when>
			<c:otherwise>
				<td></td>
				<td>전적없음</td>
				<td></td>
			</c:otherwise>
		</c:choose>
		</tr>
			
	
	</table>
	
	
	
</body>
</html>