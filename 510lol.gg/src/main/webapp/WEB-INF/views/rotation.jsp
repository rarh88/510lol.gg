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
   <c:forEach var="dto" items="${NewPlayerrotation }">
      <img src="resources/img/${dto}.png">
   </c:forEach><br>
   <c:forEach var="dto" items="${weeklyrotation }">
      <img src="resources/img/${dto}.png">
   </c:forEach>
</body>
</html>