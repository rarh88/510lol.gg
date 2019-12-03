<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
   function Readmsg(num){
	   var mnumber= num;
  	  location.href="readMsg?mnumber="+mnumber;
   }
 
</script>
</head>


<body >
   
   
	<c:choose>
		<c:when test="${totalmsg != 0 }">
	<table border="1" align="center">
      <c:forEach var="msg" items="${myMsg }">
         <tr>
            <td><label id="${msg.mnumber }s">${msg.msender }</label></td>
            <td><label id="${msg.mnumber }c" onclick="Readmsg(${msg.mnumber})">${msg.mcontent }</label></td>
            <td><label id="${msg.mnumber }d">${msg.mdate }</label></td>
        </tr>
     </c:forEach>
     
      <tr>
         <td colspan="4" align="right">
         <c:choose>
            <c:when test="${param.start == null }">
               <c:set var="start" value="1"/>
            </c:when>
            <c:otherwise>
               <c:set var="start" value="${param.start }"/>
            </c:otherwise>
         </c:choose>
         <c:choose>
            <c:when test="${start > 1}">
               <button type="button" onclick = "location.href='chkMsg?mreceiver=${param.mreceiver }&stnum=${start-1 }'">
               이전
               </button>
            </c:when>
            <c:otherwise>
               <button type="button" disabled="disabled">
               이전
               </button>
            </c:otherwise>
         </c:choose>
         <c:set var="totalPage" value="${mytotalMsg }"/>
         <c:forEach begin="1" end="${totalPage}" step="1" var="cnt">
            <a href="chkMsg?mreceiver=${param.mreceiver }&stnum=${cnt }">[${cnt}]</a>
         </c:forEach>
         <c:choose>
            <c:when test="${start < totalPage}">
               <button type="button" onclick = "location.href='chkMsg?mreceiver=${param.mreceiver }&stnum=${start+1 }'">
               다음
               </button>
            </c:when>
            <c:otherwise>
               <button type="button" disabled="disabled">
               다음
               </button>
            </c:otherwise>
         </c:choose>
         ${start } / ${totalPage }
            </td>
      </tr>
   
		</c:when>
		<c:otherwise>
			쪽지가 없습니다!
		</c:otherwise>
	</c:choose>
   
   
</body>
</html>