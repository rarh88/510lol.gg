<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
   var sel_file;
   
   $(document).ready(function(){
      $('#report_img').on("change", selectImg);   
   });
   
   function selectImg(e){
      var files = e.target.files;
      var filesArr = Array.prototype.slice.call(files);
      
      filesArr.forEach(function(f){
         if(!f.type.match("image.*")){
            alert("확장자는 이미지 확장자만 가능");
            return;
         }
         sel_file=f;
      
         var reader = new FileReader();
         reader.onload = function(e){
            $('#img_report').attr("src", e.target.result);
         }
         reader.readAsDataURL(f);
      });
      
   }
   

   

</script>


</head>
<body>

<form action="reporting">
   <table border="1">
      <tr>
         <td>제목</td>
         <td><select name="title">
               <option>--제목--</option>
               <option value="User_Report">유저 신고</option>
               <option value="Bug_Report">버그/오류</option>
            </select>
         </td>
      </tr>
      <tr>
         <td>내용</td>
         <td><textarea rows="20" cols="50" name="content"></textarea>
      </tr>
      <tr>
         <td colspan="2" align="right">
            <input type="file" id="report_img">
            <input type="submit" value="등록">
            <input type="button" value="뒤로가기" onclick="location.href='report'">
   </table>
   <img id="img_report">
</form>


</body>
</html>