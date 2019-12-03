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
   <c:set var="snick" value="${sessionScope.snick }"/>
   <c:set var="readm" value="${readm }"/>
   
   
   <c:choose>
      <c:when test="${snick != null }">
         <table>
            <tr>
               <td colspan="2">${readm.mcontent    }</td>
            </tr>
            <tr>
               <td colspan="2" align="right">from.${readm.msender }</td>
            </tr>
            <tr>
               <td colspan="2" align="center">
                  <input type="button" value="답장" onclick="location.href='replyMsg?user=${readm.mreceiver}&mem=${readm.msender }&mnumber=${readm.mnumber }'">
                  <input type="button" value="삭제" onclick="location.href='delMsg?user=${readm.mreceiver}&mnumber=${readm.mnumber}'">
                  <input type="button" value="뒤로" onclick="location.href='chkMsg?mreceiver=${readm.mreceiver}'">
               </td>
            </tr>
               
         </table>
      </c:when>
      <c:otherwise>
         <script type="text/javascript">
            alert("로그인");
            openerlocation.href="main";
         
         </script>
      
      </c:otherwise>
   </c:choose>
</body>
</html>