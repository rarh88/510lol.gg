<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
   function update(str){
      var nick = str;
         
      $.ajax({
         url:"user_update?nick="+nick, type:"GET", success: function(data){
            
            $('#nick').text(data.nick);
            $('#rank').text(data.rank);
            $('#tier').text(data.tier);
            $('#score').text(data.socre)
            $('#total').text(data.total);
            $('#winrate').text(data.winrate);
            $('#most1').text(data.most1);
            $('#most2').text(data.most2);
            $('#most3').text(data.most3);
            $('#most4').text(data.most4);
            $('#most5').text(data.most5);
            $('#img').attr("src", "http://ddragon.leagueoflegends.com/cdn/9.16.1/img/profileicon/"+data.img+".png");
            $('#m1total').text(data.m1total);
            $('#m1winrate').text(data.m1winrate);
            $('#m1kda').text(data.m1kda);
            $('#m2total').text(data.m2total);
            $('#m2winrate').text(data.m2winrate);
            $('#m2kda').text(data.m2kda);
            $('#m3total').text(data.m3total);
            $('#m3winrate').text(data.m3winrate);
            $('#m3kda').text(data.m3kda);
            $('#m4total').text(data.m4total);
            $('#m4winrate').text(data.m4winrate);
            $('#m4kda').text(data.m4kda);
            $('#m5total').text(data.m5total);
            $('#m5winrate').text(data.m5winrate);
            $('#m5kda').text(data.m5kda);
            
            alert("업데이트 성공");
            
         },
         error:function(){
            alert("문제가 발생")
         }
      });
   }
   
   setInterval(  function init(){
      var nick = "${param.nickname}";
      console.log("11111111111111");
      $.ajax({
         url:"chkButton?nick="+nick, type:"GET", success: function(data){
         
            if(data.btt == "yes"){
               document.getElementById("btt").disabled=false;
            }else{
               document.getElementById("btt").disabled=true;
            }
         },
         error:function(){
            alert("문제가 발생")
         }
      });
      
   }, 1000);   
   
   
   function Sendmsg(user, mem){
      var user = user;
      var mem = mem;
      var lolsoption = "width=380, height=210, resizable=no, scrollbars=no, status=no, left:50%, top:50%";
       window.open("sendMsg?user="+user+"&mem="+mem,"",lolsoption);
   }
</script>

<style>
body{
background-color:#1a1d21;
}

.Re1{

	background-color:#3e444d;
	  width:700px;
      height:1000px;
      margin:0 auto;
       border: 3px solid #121211;
       border-radius:30px;
       color:white;
       
      
}





.Re1Hed1{
	color:black;
	font-size:50px;
	width:14%;
	height:100px;
	background-color:yellow;

	float:left;


}

.Re1Title {
	height:100px;
	width:86%;

	color:black;
	font-size:34px;
	float:right;
}

.Re1Info{
	width:100%;
	height:80px;
	color:white; 
	font-size:20px;
	float:left;
}

.Re1Text{
	float:left;
	width:100%;
	height:700px;

	text-align:center;

}
.Re1Text img{
	width:80px; height:80px;
}



</style>






</head>
<body onload="init()">
<jsp:include page="css/header2.jsp"/>
<c:set var="sdto" value="${leagueInfo }"/>
   <c:set var="fdto" value="${finduser }"/>
   <c:set var="mdto" value="${memberuser }"/>
   <c:set var="mo1kda" value="${mo1kda }"/>
   <c:set var="msg" value="${msg }"/>
   <c:set var="snick" value="${sessionScope.snick }"/>
<c:set var="mo1dto" value="${smo1kda }"/>
      <c:set var="mo2dto" value="${smo2kda }"/>
      <c:set var="mo3dto" value="${smo3kda }"/>
      <c:set var="mo4dto" value="${smo4kda }"/>
      <c:set var="mo5dto" value="${smo5kda }"/>
 <c:set var="fmo1" value="${m1dto }"/>
      <c:set var="fmo2" value="${m2dto }"/>
      <c:set var="fmo3" value="${m3dto }"/>
      <c:set var="fmo4" value="${m4dto }"/>
      <c:set var="fmo5" value="${m5dto }"/>
<div class="Re1">







   
   <c:choose>
    
      <c:when test="${sdto != null }">
    <div class="Re1Hed1">	
	<img id="img" src="${imgURL }" height="100px" width="100px"/>  
	 </div>
	 
	<div class="Re1Title">
     ${sdto.nick } &nbsp;<c:if test="${snick != null }">
						            <c:if test="${msg != null and snick!=sdto.nick}">
						               <input type="button" id="msg" onclick="Sendmsg(${snick}','${sdto.nick }')" value="쪽지보내기">
						            </c:if>
						        </c:if>    
						        <br>
     <input type="button" id="btt" onclick="update('${sdto.nick}')" value="전적갱신">
     </div>  
     <div class="Re1Info">
      <label id="queuetype">${sdto.queuetype }</label><br>
      ${sdto.tier } ${sdto.rank } ${sdto.score }점<br>
            총판수:${sdto.total } / 승률:${sdto.winrate }
     </div> 
      <div class="Re1Text">
      ============== 50경기 Most 5 ==============<br>
     <img src="resources/img/${sdto.most1  }.png"> 게임 판수 : <label id="m1total">${mo1dto.total }</label> 승률 : <label id="m1winrate">${mo1dto.winrate }</label> KDA : <label id="m1kda">${mo1dto.kda }</label><br>
      <img src="resources/img/${sdto.most2  }.png"> 게임 판수 : <label id="m2total">${mo2dto.total }</label> 승률 : <label id="m1winrate">${mo2dto.winrate }</label> KDA : <label id="m2kda">${mo2dto.kda }</label><br>
      <img src="resources/img/${sdto.most3  }.png"> 게임 판수 : <label id="m3total">${mo3dto.total }</label> 승률 : <label id="m1winrate">${mo3dto.winrate }</label> KDA : <label id="m3kda">${mo3dto.kda }</label><br>
      <img src="resources/img/${sdto.most4  }.png"> 게임 판수 : <label id="m4total">${mo4dto.total }</label> 승률 : <label id="m1winrate">${mo4dto.winrate }</label> KDA : <label id="m4kda">${mo4dto.kda }</label><br>
      <img src="resources/img/${sdto.most5  }.png"> 게임 판수 : <label id="m5total">${mo5dto.total }</label> 승률 : <label id="m1winrate">${mo5dto.winrate }</label> KDA : <label id="m5kda">${mo5dto.kda }</label><br>
    	</div>     
           
     
     </c:when>
    
      <c:otherwise>
     <div class="Re1Hed1">	
     	<img id="img" src="${imgURL }" height="100px" width="100px"/>   
	  	   </div>
     <div class="Re1Title">	
  	  ${fdto.nick }&nbsp;     <c:if test="${snick != null }">
							            <c:if test="${msg != null and snick != fdto.nick}">
							               <input type="button" id="msg" onclick="Sendmsg('${snick}','${fdto.nick }')" value="쪽지보내기">
							            </c:if>
							           </c:if>  
							           <br>
  	  <input type="button" id="btt" onclick="update('${fdto.nick}')" value="전적갱신">
  	  </div>  
  	  
  	  <div class="Re1Info">
       <label id="queuetype">${fdto.queuetype }</label><br>
      ${fdto.tier } ${fdto.rank } ${fdto.score }점<br>
      	총판수:${fdto.total } / 승률:${fdto.winrate }
      
      </div>
           <div class="Re1Text">
      ============== 50경기 Most 5 ==============<br>
      <img src="resources/img/${fdto.most1 }.png"> 게임 판수 : <label id="m1total">${fmo1.total }</label> 승률 : <label id="m1winrate">${fmo1.winrate }</label>  KDA : <label id="m1kda">${fmo1.kda }</label><br>
      <img src="resources/img/${fdto.most2 }.png"> 게임 판수 : <label id="m2total">${fmo2.total }</label> 승률 : <label id="m2winrate">${fmo2.winrate }</label>  KDA : <label id="m2kda">${fmo2.kda }</label><br>
      <img src="resources/img/${fdto.most3 }.png"> 게임 판수 : <label id="m3total">${fmo3.total }</label> 승률 : <label id="m3winrate">${fmo3.winrate }</label>  KDA : <label id="m3kda">${fmo3.kda }</label><br>
      <img src="resources/img/${fdto.most4 }.png"> 게임 판수 : <label id="m4total">${fmo4.total }</label> 승률 : <label id="m4winrate">${fmo4.winrate }</label>  KDA : <label id="m4kda">${fmo4.kda }</label><br>
      <img src="resources/img/${fdto.most5 }.png"> 게임 판수 : <label id="m5total">${fmo5.total }</label> 승률 : <label id="m5winrate">${fmo5.winrate }</label>  KDA : <label id="m5kda">${fmo5.kda }</label><br>
         </div>
            
               
      </c:otherwise>
   </c:choose>
    

      
</div>   
</body>
</html>