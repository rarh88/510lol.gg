<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
   <script type="text/javascript">
   function challenger(){
      if(${stier == "CHALLENGER"}){
         location.href="challenger";
      }else{
         alert("챌린저만 참여 가능합니다.");
      }
   }function grandmaster(){
      if(${stier == "GRANDMASTER"}){
         location.href="grandmaster";
      }else{
         alert("그마만 참여 가능합니다.");
      }
   }function master(){
      if(${stier == "MASTER"}){
         location.href="master";
      }else{
         alert("마스터만 참여 가능합니다.");
      }
   }function diamond(){
      if(${stier == "DIAMOND"}){
         location.href="diamond";
      }else{
         alert("다이아만 참여 가능합니다.");
      }
   }function platinum(){
      if(${stier == "PLATINUM"}){
         location.href="platinum";
      }else{
         alert("플레만 참여 가능합니다.");
      }
   }
   function gold(){
      if(${stier == "GOLD"}){
         location.href="gold";
      }else{
         alert("골드만 참여 가능합니다.");
      }
   }
      function silver(){
         if(${stier == "SILVER"}){
            location.href="silver";
         }else{
            alert("실버만 참여 가능합니다.");
         }
      }
      function bronze(){
         if(${stier == "BRONZE"}){
            location.href="bronze";
         }else{
            alert("브론즈만 참여 가능합니다.");
         }
      }
      function iron(){
         if(${stier == "IRON"}){
            location.href="iron";
         }else{
            alert("NO person만 참여 가능합니다.");
         }
      }
   </script>

<title>Home</title>
</head>
<body>
<jsp:include page="css/header2.jsp"/>
   <c:set var="stier" value="${sessionScope.stier }"/>
   
      <input type="button" value="챌린저" onclick="challenger()"><br>
      <input type="button" value="그랜드마스터" onclick="grandmaster()"><br>
      <input type="button" value="마스터" onclick="master()"><br>
      <input type="button" value="다이아" onclick="diamond()"><br>
          <input type="button" value="플  레" onclick="platinum()"><br>
       <input type="button" value="골  드" onclick="gold()"><br>
       <input type="button" value="실  버" onclick="silver()"><br>
       <input type="button" value="브론즈" onclick="bronze()"><br>
       <input type="button" value="노사람" onclick="iron()"><br>
    
</body>
</html>