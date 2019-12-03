<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
   function send(user, mem){
      var sender = user;
      var receiver = mem;
      var content = document.getElementById("msg").value;
      
      if(content != ''){
          $.ajax({
               url:"goMsg?sender="+sender+"&receiver="+receiver+"&content="+content, type:"POST", success: function(data){
                  var chk = data.chk;
                  if(chk == "true"){
                     alert("쪽지 전송 성공");
                     window.close();
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
   
   function canncel(){
      window.close();
   }
</script>
</head>
<body>
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
         <input type="button" value="보내기" onclick="send('${param.user }','${param.mem }' )">
         <input type="button" value="취소" onclick="cancel()">
         </td>
      </tr>
   
   </table>
</body>
</html>
