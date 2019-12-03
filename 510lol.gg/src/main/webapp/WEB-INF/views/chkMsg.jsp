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

 <style>
    .table {
      border-collapse: collapse;
      border-top: 3px solid #168;
    }  
    .table th {
      color: #168;
      background: #f0f6f9;
      text-align: center;
    }
    .table th, .table td {
      padding: 10px;
      border: 1px solid #ddd;
    }
    .table th:first-child, .table td:first-child {
      border-left: 0;
    }
    .table th:last-child, .table td:last-child {
      border-right: 0;
    }
    .table tr td:first-child{
      text-align: center;
    }
    .table caption{caption-side: bottom; display: none;}
    
   

	button{
	      background:none;
	      text-align:center;
	      border:2px solid #b59a61;
	     
	      outline: none;
	      color:BLACK;
	      border-radius:24px;
	      transition: 0.25s;
	      cursor:pointer;
			width:60px;
			height:40px;
			margin:0 0 0 0;
		align:center;
	}
	button:hover{
		background:#b59a61;
	}
    
  </style>

</head>


<body >
   <h1 align="center">쪽지함</h1>
   
	<c:choose>
		<c:when test="${totalmsg != 0 }">
	<table class="table" align="center">
	<tr>
		<th>Sender</th>
		<th align="center" width="300px">Content</th>
		<th>Time</th>
	</tr>
      <c:forEach var="msg" items="${myMsg }">
         <tr>
            <td><label id="${msg.mnumber }s">${msg.msender }</label></td>
            <td><label id="${msg.mnumber }c" onclick="Readmsg(${msg.mnumber})"><marquee behavior="slide" scrolldelay="200">${msg.mcontent }</marquee></label></td>
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
            <a href="chkMsg?mreceiver=${param.mreceiver }&stnum=${cnt }"> ${cnt}</a>
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
        
            </td>
      </tr>
   
		</c:when>
		<c:otherwise>
			쪽지가 없습니다!
		</c:otherwise>
	</c:choose>
   
   
</body>
</html>