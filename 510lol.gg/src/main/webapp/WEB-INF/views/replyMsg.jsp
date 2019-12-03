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
   function send(user, mem, number){
      var sender = user;
      var receiver = mem;
      var mnumber = number;
      console.log(mnumber);
      var content = document.getElementById("msg").value;
      
      if(content != ''){
          $.ajax({
               url:"replyMsgg?sender="+sender+"&receiver="+receiver+"&content="+content+"&mnumber="+mnumber, type:"POST", success: function(data){
                  var chk = data.chk;
                  if(chk == "true"){
                     alert("답장 성공");
                     location.href="chkMsg?mreceiver=${param.user }";
                  }else{
                     alert("DB 문제");
                  }
                 
                  
               },
               error:function(){
                  alert("문제가 발생");
               }
          });
      
      }else{
         alert("쪽지를 입력해주세요!!");
      }
   }
   
  
</script>
</head>
<body>
   <c:set var="snick" value="${sessionScope.snick}"/>
   <c:choose>
      <c:when test="${snick!=null }">
            <table>
            <tr>
               <td colspan="2">to. ${param.mem }</td>
               
            </tr>
            
            <tr>
               <td>content</td>
               <td><textarea rows="5" cols="30" id="msg"></textarea></td>
            </tr>
               <tr>
               <td colspan="2" align="right">from. ${param.user }</td>
            </tr>
            <tr>
               <td colspan="2" align="right">
               <input type="button" value="보내기" onclick="send('${param.user }','${param.mem }','${param.mnumber }' )">
               <input type="button" value="취소" onclick="history.go(-1)">
               </td>
            </tr>
         
         </table>
      </c:when>
      <c:otherwise>
         <script type="text/javascript">
            location.href="login";
         </script>
      </c:otherwise>
   </c:choose>
   
</body>
</html>