<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <table border="1">
      <caption>신고 게시판</caption>
      <tr>
         <th>글번호</th>
         <th>제목</th>
         <th>작성일</th>
         <th>처리여부</th>
      </tr>
      
      
      <tr>
         <td colspan="4" align="right">
            <input type="button" value="글쓰기" onclick="location.href='write_report'">
            <input type="button" value="내가 쓴 글">
         </td>
      </tr>
   
   </table>
</body>
</html>