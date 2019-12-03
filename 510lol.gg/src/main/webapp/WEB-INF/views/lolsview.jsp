<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   function ok(){
      var best1 = arr[0];
      var best2 = arr[1];
      var best3 = arr[2];
      


      location.href="ok?best1="+best1+"&best2="+best2+"&best3="+best3;   
   }
   
   function no(){
      location.href="lols";
   }
</script>

<script>
   var arr=[];
   var counting = 0;
    function display(name){
       
       
         list();
     
         arr.push(name);
         rp2 = document.getElementById("rp2")
         var arr_length = arr.length;
       
         if(arr_length < 4){
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
            arr = arr.splice(arr_length-3,arr_length+1);
            
            console.log(arr.length);
            
            for(i=0;i<3;i++){
            
              
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
       ls.innerHTML="???"
       ls.style.background="green"
    }
</script>

<style>
   img{width:70px; height:70px;}

</style>




</head>
<body>
lolsview.jsp<br>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
   <c:when test="${c == 4 }">
      <script>
         alert("이미가입된 회원입니다.")
         location.href="lols";
      </script>
   </c:when>
   <c:otherwise>
   <c:choose>
      <c:when test="${llid == null }">
         등록된 소환사가 아닙니다.<br>
      </c:when>
      <c:otherwise>
         <c:choose>
            <c:when test="${tier == null }">
            닉네임 : ${nick }님<br>
            <h1>솔로랭크 기록이 없습니다!</h1>
            <button onclick="no()">뒤로</button>
         </c:when>
         <c:otherwise>
            닉네임 : ${nick }님<br>
            랭크 :${tier }${rank }<br>
            점수 : ${score }<br>
            총판수 : ${total } 승:${wins}<br>
            승률 : ${winrate }<br>
            <hr>
            
         <div class="list">
            <h3 class="list_title" id="ls" align="center">목록이<br>없습니다</h3>
            <div class="rp1" id="rp2" align="center"></div>
         </div>   
         <table border="1" align="center">
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
         
            <button onclick="ok()">저장</button>
            <button onclick="no()">뒤로</button>
      </c:otherwise>
         </c:choose>
      </c:otherwise>
      
      
   
   
   
   </c:choose>
   </c:otherwise>
</c:choose>









</body>
</html>