   <%@ page language="java" contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>
   <!DOCTYPE html>
   <html>
   <head>
   <meta charset="UTF-8">
   <title>Insert title here</title>
   <script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>
   
   <script>
   function click_logout() {
        if (confirm('로그아웃 하시겠습니까?')) {
           $.ajax({
              type : "POST",
              dataType : 'text',
              url : "http://nid.naver.com/nidlogin.logout",
              crossDomain : true,
              xhrFields : {
                 withCredentials : true
              }
           }).done(function(data) {
   
              $('#logout').submit();
           }).fail(function(xhr, textStatus, errorThrown) {
   
              $('#logout').submit();
           });
           location.href = "logout";
        } else{
           location.href = "login";
        }
     }
   function changeChamp(num, best){
	   var hp; 
       var vp; 
       hp = ( screen.width - 640 ) / 2 + 1; 
       vp = ( screen.height - 800 ) / 2 + 1; 
	   
	   
      
      var lolsoption = "width=800, height=800, resizable=no, scrollbars=no, status=no, location = no, channelmode=yes,  left="+hp+", top="+vp+"";
         window.open("changeBest?champ="+num+"&best="+best,"",lolsoption);
   }
   
   </script>   
   <script type="text/javascript">
   function click_kakaologout(){
      location.href="kakaologout";
   }
   
   function mypagechks(){
      document.mychks.submit();
   }
   
   </script>
   
   
   <style>
      .mypage1{width:80%; margin:0 auto; text-align:center; color:white;}
      table{margin:0 auto; text-align:center;}
   
   </style>
   
   <style>
      .box4{width:500px; padding:100px; position:absolute;
         top:1100px; left:50%; transform: translate(-50%,-50%);
         bakcground:#191919; text-align:center;
      }
      
      .box4 input[type = "text"],.box3 input[type = "password"]{
      border:0;
      background:none;
      
      margin:20px auto;
      text-align:center;
      border: 2px solid #3498db;
      padding: 14px 10px;
      width:200px;
      outline:none;
      color:black;
      border-radius:24px;
      transition:0.25s;
      }
      
      .box4 input[type="text"]:focus,.box4 input[type = "password"]:focus{
         width:280px;
         border-color:#2ecc71;
      }
      .box4 input[type="submit"]{
         border:0;
         background:none;
        
         margin:20px auto;
         text-align:center;
         border:2px solid #2ecc71;
         padding:14px 40px;
         outline: none;
         color:black;
         border-radius:24px;
         transition: 0.25s;
         cursor:pointer;
      }
      .box4 input[type = "submit"]:hover{
         background:#2ecc71;
      }
      
      .box4 input[type = "button"]{
         
         background:none;

         text-align:center;
         border:2px solid #2ecc71;
         
         outline: none;
         color:black;
         border-radius:24px;
         transition: 0.25s;
         cursor:pointer;
         width:80px;
         height:40px;
      }
      .box4 input[type="button"]:hover{
         background:#2ecc71;
      }
         
   
   
   </style>
   
   <style>
   	body{
		background-color:#1a1d21;
   	}
   
   
   
   </style>
   
   
   
   
   
   
   
   
   
<style>
   	.modalDialog3 {
	position: fixed;
	font-family: Arial, Helvetica, sans-serif;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	background: rgba(0,0,0,0.8);
	z-index: 99999;
	opacity:0;
	-webkit-transition: opacity 400ms ease-in;
	-moz-transition: opacity 400ms ease-in;
	transition: opacity 400ms ease-in;
	pointer-events: none;
	
	
}
.modalDialog3:target {
	opacity:1;
	pointer-events: auto;
}

.modalDialog3 > div {
	width: 800px;
	height:700px;
	position: relative;
	margin: 10% auto;
	padding: 5px 20px 13px 20px;
	border-radius: 10px;
	background: #fff;
	background: -moz-linear-gradient(#fff, #999);
	background: -webkit-linear-gradient(#fff, #999);
	background: -o-linear-gradient(#fff, #999);
	text-align:center;
	
}

.close {
	background: #606061;
	color: #FFFFFF;
	line-height: 25px;
	position: absolute;
	right: -12px;
	text-align: center;
	top: -10px;
	width: 24px;
	text-decoration: none;
	font-weight: bold;
	-webkit-border-radius: 12px;
	-moz-border-radius: 12px;
	border-radius: 12px;
	-moz-box-shadow: 1px 1px 3px #000;
	-webkit-box-shadow: 1px 1px 3px #000;
	box-shadow: 1px 1px 3px #000;
}

.close:hover { background: #00d9ff; }

-webkit-transition: opacity 400ms ease-in;
-moz-transition: opacity 400ms ease-in;
transition: opacity 400ms ease-in;
   	
  .modalDialog3 div{
  width:600px; height:450px; overflow:auto; }
  
  
	test3{
	width:600px; height:450px;
	}
	
	.test3 tbdiv3{
		width:500px; height:450px;
		overflow:auto;
	}
	
	.tbdiv3{
		width:600px; height:450px;
		margin:0 auto;
		align:center; text-align:center;	
		overflow:auto;
	}
	
	
	
	}
	.tbdiv3 table{
		width:400px; height:250px; align:center; text-align:center;
				overflow:auto;
		
	}	
	
	.tbdiv3 img{width:70px; height:70px;}

   </style>
   
   <style>
   	.modalDialog {
	position: fixed;
	font-family: Arial, Helvetica, sans-serif;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	background: rgba(0,0,0,0.8);
	z-index: 99999;
	opacity:0;
	-webkit-transition: opacity 400ms ease-in;
	-moz-transition: opacity 400ms ease-in;
	transition: opacity 400ms ease-in;
	pointer-events: none;
	
	
}
.modalDialog:target {
	opacity:1;
	pointer-events: auto;
}

.modalDialog > div {
	width: 800px;
	height:800px;
	position: relative;
	margin: 10% auto;
	padding: 5px 20px 13px 20px;
	border-radius: 10px;
	background: #fff;
	background: -moz-linear-gradient(#fff, #999);
	background: -webkit-linear-gradient(#fff, #999);
	background: -o-linear-gradient(#fff, #999);
	
}

.close {
	background: #606061;
	color: #FFFFFF;
	line-height: 25px;
	position: absolute;
	right: -12px;
	text-align: center;
	top: -10px;
	width: 24px;
	text-decoration: none;
	font-weight: bold;
	-webkit-border-radius: 12px;
	-moz-border-radius: 12px;
	border-radius: 12px;
	-moz-box-shadow: 1px 1px 3px #000;
	-webkit-box-shadow: 1px 1px 3px #000;
	box-shadow: 1px 1px 3px #000;
}

.close:hover { background: #00d9ff; }

-webkit-transition: opacity 400ms ease-in;
-moz-transition: opacity 400ms ease-in;
transition: opacity 400ms ease-in;
   	
  .modalDialog div{
  width:600px; height:450px; overflow:auto;}
  
  
	test1{
	width:600px; height:450px; margin: 0 auto; text-align:center;
	}
	
	.bt1{
		align:center;
		background:none;
	   margin:0 auto;
	   text-align:center;
	   border: 2px solid #3498db;
	   left:50%;
	   width:100px;
	   height:50px;
	   outline:none;
	   color:black;
	   border-radius:24px;
	   transition:0.25s;
	}
	.bt1:hover{
      background:#2ecc71;
   }
	
	
	
	
	.test1 tbdiv{
		width:500px; height:450px;
		overflow:auto;
	}
	
	.tbdiv{
		width:600px; height:450px;
		margin:0 auto;
		align:center; text-align:center;	
		overflow:auto;
	}
	
	
	
	}
	.tbdiv table{
		width:400px; height:250px; align:center; text-align:center;
				overflow:auto;
		
		
		
		
		
		
		
		
		
	}	
	
	.tbdiv img{width:70px; height:70px;}


	


   </style>
   
<style>
::-webkit-scrollbar-track
{
	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
	border-radius: 10px;
	background-color: #F5F5F5;
}

::-webkit-scrollbar
{
	width: 12px;
	background-color: #F5F5F5;
}

::-webkit-scrollbar-thumb
{
	border-radius: 10px;
	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
	background-color: #b59a61;
}


</style>
   
   
   
   
   <script>
   	function testchamp(num, best){
   		this.best = best;
   		location.href="#openModal3";
   	
   		
   		
   		
   		
   		
   	}
   
   </script>
   
   
   
<script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>
<script>
var arr=[];
var counting = 0;
   function ok3(){
		var test = best;
         var champ = arr[0];
        
         var form = {champ : champ, test : test}
         
         
         $.ajax({
        	type:"GET",
        	url:"ajax_mypage1",
        	data : form ,

			success: function(data){
				alert("변경되었습니다.")
				location.href="mypage";
				
			},
        	error:function(){
        		alert("안댐")
        	}
        	 
        	 
  
         });
         
         
 

      }
     
   
  
    function display(name){
       
       
         list();
     
         arr.push(name);
         rp2 = document.getElementById("rp2")
         var arr_length = arr.length;
       
         if(arr_length < 1){
           var img=document.createElement('img');
           img.onclick=function(){   
              var del = arr.indexOf(name);
             arr.splice(del, 1)
              document.getElementById("rp2").removeChild(this);
           }
           img.src="resources/img/"+name+".png";
           rp2.appendChild(img)
           
         
         }else{
            rp2.innerHTML = ""
            arr = arr.splice(arr_length-1,arr_length+1);
            
            console.log(arr.length);
            
            for(i=0;i<1;i++){
            
              
               var img=document.createElement('img');
               img.onclick=function(){
                 var del = arr.indexOf(name);
                 arr.splice(del, 1)
                  document.getElementById("rp2").removeChild(this);
               }
               img.src="resources/img/"+ arr[i] +".png";
               document.getElementById("rp2").appendChild(img);
              
            }
         }
    }   
    function list(){
       ls = document.getElementById("ls")
       ls.innerHTML="선택한챔피언"
       ls.style.background="green"
    }
</script>
   
   
   
   
   
   
   
   
   
   
   
   </head>
<body>
<jsp:include page="css/header2.jsp"/>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <c:choose>
      <c:when test="${sid == null }">
         <script>
            alert("로그인이 필요합니다!");
            location.href="login"
         </script>
      </c:when>
      <c:otherwise>

<br><br>
      <div class="mypage1">
      <h1>My Page</h1>
         <table border="1">
            <tr><td>id</td><td>${lists.id }</td></tr>
            <form name="mychks"  action ="mypagechk" method="post">
               <tr><td>pw</td><td><input type="text" id="pwc" name="pwc" value="${lists.pw }" style="text-align:center;"></td></tr>
               <tr><td>이름</td><td>${lists.name }</td></tr>
               <tr><td>핸드폰번호</td><td><input type="text" id="phonenumc" name="phonenumc" value="${lists.phonenum }" style="text-align:center;"></td></tr>
            

            <tr><td>롤닉네임</td><td>${lists.nick }</td></tr>
            <tr><td>티어</td><td>${lists.tier } ${lists.rank }</td></tr>
            <tr><td>점수</td><td>${lists.score }점</td></tr>
            <tr><td>총판수</td><td>${lists.total }</td></tr>
            <tr><td>승리</td><td>${lists.wins }</td></tr>
            <tr><td>승률</td><td>${lists.winrate }%</td></tr>
        	<tr><td colspan="2">챔피언클릭시 변경가능합니다</td></tr>
            <tr>        	
            	<td><img id="best1" src="resources/img/${lists.best1}.png" onclick="testchamp(${lists.best1}, 'best1')"></td>
    			
    				<c:choose>
    					<c:when test="${lists.best1kda == 'null' }">
    						<td>데이터가없습니다. <br>party를 누르면 전적이갱신됩니다.</td>
    					</c:when>
    					<c:otherwise>
    						<c:choose>
    							<c:when test="${lists.best1kda == '데이터없음' }">
    								<td>최근에 챔피언을 플레이한 기록이 없습니다.</td>
    							</c:when>
    							<c:otherwise>
    								<td>KDA:${lists.best1kda }<br> 총판수:${lists.best1total }<br> 승률:${lists.best1winrate }%</td>
    							</c:otherwise>
    						</c:choose>
            			</c:otherwise>
           			</c:choose>
           			
            </tr>
            <tr>
            	
            	<td><img id="best2" src="resources/img/${lists.best2}.png" onclick="testchamp(${lists.best2}, 'best2')" ></td>
            	
            	<c:choose>
    					<c:when test="${lists.best2kda == 'null' }">
    						<td>데이터가없습니다. <br>party를 누르면 전적이갱신됩니다.</td>
    					</c:when>
    					<c:otherwise>
    						<c:choose>
    							<c:when test="${lists.best2kda == '데이터없음' }">
    								<td>최근에 챔피언을 플레이한 기록이 없습니다.</td>
    							</c:when>
    							<c:otherwise>
    								<td>KDA:${lists.best2kda }<br> 총판수:${lists.best2total }<br> 승률:${lists.best2winrate }%</td>
    							</c:otherwise>
    						</c:choose>
            			</c:otherwise>
           			</c:choose>
            	
           	</tr>
            <tr>	
            	<td><img id="best3" src="resources/img/${lists.best3}.png" onclick="testchamp(${lists.best3}, 'best3')"></td>
            	
            	<c:choose>
    					<c:when test="${lists.best3kda == 'null' }">
    						<td>데이터가없습니다. <br>party를 누르면 전적이갱신됩니다.</td>
    					</c:when>
    					<c:otherwise>
    						<c:choose>
    							<c:when test="${lists.best3kda == '데이터없음' }">
    								<td>최근에 챔피언을 플레이한 기록이 없습니다.</td>
    							</c:when>
    							<c:otherwise>
    								<td>KDA:${lists.best3kda }<br> 총판수:${lists.best3total }<br> 승률:${lists.best3winrate }%</td>
    							</c:otherwise>
    						</c:choose>
            			</c:otherwise>
           			</c:choose>
            	

            </tr>
            	

            </table>
            <div class="box4" >
         <c:choose>
            <c:when test="${access_Token != null }">
               <input type="button" value="변경" onclick="mypagechks()">
               <input type="button" value="로그아웃" onclick="click_kakaologout();">
               
            </c:when>
            <c:otherwise>
               <input type="button" value="변경" onclick="mypagechks()">
               <input type="button" value="로그아웃" onclick="click_logout();">
   
            </c:otherwise>
          
         </c:choose>
            
            </div>
            
         </div>
          
      
      
      
      </c:otherwise>
      
   </c:choose>
    </form>
   
   
   <div id="openModal3" class="modalDialog3">
   		<div class="test1">
		<a href="#close" title="Close" class="close">X</a>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

         <div class="list">
            <h3 class="list_title" id="ls" align="center">챔피언을 골라주세요</h3>
            <div class="rp1" id="rp2" align="center"></div>
         </div>   
         <div class="tbdiv">
	         <table border="0" align="center">
	            <tr>
	               <td><img onclick="display('86')" src="resources/img/86.png"></td>
	               <td><img onclick="display(3)"src="resources/img/3.png"></td>
	               <td><img onclick="display(41)"src="resources/img/41.png"></td>
	               <td><img onclick="display(79)"src="resources/img/79.png"></td>
	               <td><img onclick="display(104)"src="resources/img/104.png"></td>
	               <td><img onclick="display(150)"src="resources/img/150.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(267)"src="resources/img/267.png"></td>
	               <td><img onclick="display(75)"src="resources/img/75.png"></td>
	               <td><img onclick="display(111)"src="resources/img/111.png"></td>
	               <td><img onclick="display(56)"src="resources/img/56.png"></td>
	               <td><img onclick="display(20)"src="resources/img/20.png"></td>
	               <td><img onclick="display(76)"src="resources/img/76.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(518)"src="resources/img/518.png"></td>
	               <td><img onclick="display(122)"src="resources/img/122.png"></td>
	               <td><img onclick="display(131)"src="resources/img/131.png"></td>
	               <td><img onclick="display(119)"src="resources/img/119.png"></td>
	               <td><img onclick="display(13)"src="resources/img/13.png"></td>
	               <td><img onclick="display(497)"src="resources/img/497.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(33)"src="resources/img/33.png"></td>
	               <td><img onclick="display(99)"src="resources/img/99.png"></td>
	               <td><img onclick="display(68)"src="resources/img/68.png"></td>
	               <td><img onclick="display(58)"src="resources/img/58.png"></td>
	               <td><img onclick="display(89)"src="resources/img/89.png"></td>
	               <td><img onclick="display(421)"src="resources/img/421.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(107)"src="resources/img/107.png"></td>
	               <td><img onclick="display(236)"src="resources/img/236.png"></td>
	               <td><img onclick="display(117)"src="resources/img/117.png"></td>
	               <td><img onclick="display(7)"src="resources/img/7.png"></td>
	               <td><img onclick="display(92)"src="resources/img/92.png"></td>
	               <td><img onclick="display(127)"src="resources/img/127.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(64)"src="resources/img/64.png"></td>
	               <td><img onclick="display(11)"src="resources/img/11.png"></td>
	               <td><img onclick="display(57)"src="resources/img/57.png"></td>
	               <td><img onclick="display(90)"src="resources/img/90.png"></td>
	               <td><img onclick="display(54)"src="resources/img/54.png"></td>
	               <td><img onclick="display(82)"src="resources/img/82.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(25)"src="resources/img/25.png"></td>
	               <td><img onclick="display(36)"src="resources/img/36.png"></td>
	               <td><img onclick="display(21)"src="resources/img/21.png"></td>
	               <td><img onclick="display(432)"src="resources/img/432.png"></td>
	               <td><img onclick="display(110)"src="resources/img/110.png"></td>
	               <td><img onclick="display(254)"src="resources/img/254.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(45)"src="resources/img/45.png"></td>
	               <td><img onclick="display(67)"src="resources/img/67.png"></td>
	               <td><img onclick="display(161)"src="resources/img/161.png"></td>
	               <td><img onclick="display(106)"src="resources/img/106.png"></td>
	               <td><img onclick="display(201)"src="resources/img/201.png"></td>
	               <td><img onclick="display(63)"src="resources/img/63.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(8)"src="resources/img/8.png"></td>
	               <td><img onclick="display(53)"src="resources/img/53.png"></td>
	               <td><img onclick="display(112)"src="resources/img/112.png"></td>
	               <td><img onclick="display(78)"src="resources/img/78.png"></td>
	               <td><img onclick="display(14)"src="resources/img/14.png"></td>
	               <td><img onclick="display(517)"src="resources/img/517.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(35)"src="resources/img/35.png"></td>
	               <td><img onclick="display(235)"src="resources/img/235.png"></td>
	               <td><img onclick="display(113)"src="resources/img/113.png"></td>
	               <td><img onclick="display(37)"src="resources/img/37.png"></td>
	               <td><img onclick="display(16)"src="resources/img/16.png"></td>
	               <td><img onclick="display(98)"src="resources/img/98.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(102)"src="resources/img/102.png"></td>
	               <td><img onclick="display(50)"src="resources/img/50.png"></td>
	               <td><img onclick="display(72)"src="resources/img/72.png"></td>
	               <td><img onclick="display(15)"src="resources/img/15.png"></td>
	               <td><img onclick="display(134)"src="resources/img/134.png"></td>
	               <td><img onclick="display(27)"src="resources/img/27.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(5)"src="resources/img/5.png"></td>
	               <td><img onclick="display(412)"src="resources/img/412.png"></td>
	               <td><img onclick="display(103)"src="resources/img/103.png"></td>
	               <td><img onclick="display(32)"src="resources/img/32.png"></td>
	               <td><img onclick="display(136)"src="resources/img/136.png"></td>
	               <td><img onclick="display(427)"src="resources/img/427.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(268)"src="resources/img/268.png"></td>
	               <td><img onclick="display(84)"src="resources/img/84.png"></td>
	               <td><img onclick="display(266)"src="resources/img/266.png"></td>
	               <td><img onclick="display(12)"src="resources/img/12.png"></td>
	               <td><img onclick="display(1)"src="resources/img/1.png"></td>
	               <td><img onclick="display(34)"src="resources/img/34.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(22)"src="resources/img/22.png"></td>
	               <td><img onclick="display(157)"src="resources/img/157.png"></td>
	               <td><img onclick="display(245)"src="resources/img/245.png"></td>
	               <td><img onclick="display(60)"src="resources/img/60.png"></td>
	               <td><img onclick="display(62)"src="resources/img/62.png"></td>
	               <td><img onclick="display(516)"src="resources/img/516.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(61)"src="resources/img/61.png"></td>
	               <td><img onclick="display(2)"src="resources/img/2.png"></td>
	               <td><img onclick="display(83)"src="resources/img/83.png"></td>
	               <td><img onclick="display(77)"src="resources/img/77.png"></td>
	               <td><img onclick="display(6)"src="resources/img/6.png"></td>
	               <td><img onclick="display(19)"src="resources/img/19.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(350)"src="resources/img/350.png"></td>
	               <td><img onclick="display(39)"src="resources/img/39.png"></td>
	               <td><img onclick="display(28)"src="resources/img/28.png"></td>
	               <td><img onclick="display(81)"src="resources/img/81.png"></td>
	               <td><img onclick="display(420)"src="resources/img/420.png"></td>
	               <td><img onclick="display(59)"src="resources/img/59.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(498)"src="resources/img/498.png"></td>
	               <td><img onclick="display(143)"src="resources/img/143.png"></td>
	               <td><img onclick="display(154)"src="resources/img/154.png"></td>
	               <td><img onclick="display(40)"src="resources/img/40.png"></td>
	               <td><img onclick="display(24)"src="resources/img/24.png"></td>
	               <td><img onclick="display(238)"src="resources/img/238.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(101)"src="resources/img/101.png"></td>
	               <td><img onclick="display(126)"src="resources/img/126.png"></td>
	               <td><img onclick="display(142)"src="resources/img/142.png"></td>
	               <td><img onclick="display(115)"src="resources/img/115.png"></td>
	               <td><img onclick="display(202)"src="resources/img/202.png"></td>
	               <td><img onclick="display(26)"src="resources/img/26.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(222)"src="resources/img/222.png"></td>
	               <td><img onclick="display(31)"src="resources/img/31.png"></td>
	               <td><img onclick="display(43)"src="resources/img/43.png"></td>
	               <td><img onclick="display(164)"src="resources/img/164.png"></td>
	               <td><img onclick="display(38)"src="resources/img/38.png"></td>
	               <td><img onclick="display(30)"src="resources/img/30.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(69)"src="resources/img/69.png"></td>
	               <td><img onclick="display(145)"src="resources/img/145.png"></td>
	               <td><img onclick="display(121)"src="resources/img/121.png"></td>
	               <td><img onclick="display(55)"src="resources/img/55.png"></td>
	               <td><img onclick="display(429)"src="resources/img/429.png"></td>
	               <td><img onclick="display(85)"src="resources/img/85.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(51)"src="resources/img/51.png"></td>
	               <td><img onclick="display(141)"src="resources/img/141.png"></td>
	               <td><img onclick="display(10)"src="resources/img/10.png"></td>
	               <td><img onclick="display(96)"src="resources/img/96.png"></td>
	               <td><img onclick="display(42)"src="resources/img/42.png"></td>
	               <td><img onclick="display(133)"src="resources/img/133.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(240)"src="resources/img/240.png"></td>
	               <td><img onclick="display(246)"src="resources/img/246.png"></td>
	               <td><img onclick="display(203)"src="resources/img/203.png"></td>
	               <td><img onclick="display(44)"src="resources/img/44.png"></td>
	               <td><img onclick="display(91)"src="resources/img/91.png"></td>
	               <td><img onclick="display(163)"src="resources/img/163.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(223)"src="resources/img/223.png"></td>
	               <td><img onclick="display(48)"src="resources/img/48.png"></td>
	               <td><img onclick="display(18)"src="resources/img/18.png"></td>
	               <td><img onclick="display(23)"src="resources/img/23.png"></td>
	               <td><img onclick="display(29)"src="resources/img/29.png"></td>
	               <td><img onclick="display(4)"src="resources/img/4.png"></td>
	            </tr>
	            <tr>
	               <td><img onclick="display(17)"src="resources/img/17.png"></td>
	               <td><img onclick="display(555)"src="resources/img/555.png"></td>
	               <td><img onclick="display(80)"src="resources/img/80.png"></td>
	               <td><img onclick="display(9)"src="resources/img/9.png"></td>
	               <td><img onclick="display(114)"src="resources/img/114.png"></td>
	               <td><img onclick="display(105)"src="resources/img/105.png"></td>
	            </tr>      
	            <tr>
	               <td><img onclick="display(74)"src="resources/img/74.png"></td>
	               <td colspan="5"><img onclick="display(120)"src="resources/img/120.png"></td>
	            </tr>
	         </table>
        </div>
        	<br>
            <button class="bt1" onclick="ok3()">변경</button>
	</div>
   
   </div>
   
   
   
   
   </body>
   </html>