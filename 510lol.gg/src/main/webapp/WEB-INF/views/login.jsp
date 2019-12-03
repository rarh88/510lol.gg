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
         if (confirm('123로그아웃 하시겠습니까?')) {
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
            location.href = "main";
         }
         
      }
    
    function kakaolog(){
       if (confirm('444로그아웃 하시겠습니까?')){
          location.href="kakaologout";
       }else{
          location.href="main";
       }
    }
    
    function mypage(){
       location.href="mypage";
    }
    
    function regis(){
       location.href="regimem";
    }
    
    function chkMsg(){
       var lolsoption = "width=800, height=800, resizable=no, scrollbars=no, status=no, left:50%, top:50%";
         window.open("chkMsg?mreceiver=${snick}&stnum=1&endnum=5","",lolsoption);
    }
    
</script>   
<style>
   .log3{
      top:36%;
      background-color:#3e444d;
       width:500px;
      height:600px;
      margin:0 auto;
       border: 3px solid #121211;
       border-radius:30px;
       
       align:center;
   
   
   
   }
   
   .box3{width:100%;  position:absolute;
      top:690px; left:50%; transform: translate(-50%,-50%);
      bakcground:#191919; text-align:center;
   }
   
   .box3 input[type = "text"],.box3 input[type = "password"]{
   
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
   
   .box3 input[type="text"]:focus,.box3 input[type = "password"]:focus{
      width:280px;
      border-color:#2ecc71;
   }
   .box3 input[type="submit"]{
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
   .box3 input[type = "submit"]:hover{
      background:#2ecc71;
   }
   
   .box3 input[type = "button"]{
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
   .box3 input[type="button"]:hover{
      background:#2ecc71;
   }
   
   a{text-decoration:none}

</style>

<style>
body{
background-color:#1a1d21;

}

.register {
    



</style>





</head>
<body>
<jsp:include page="css/header2.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br><br><br><br><br>
<div class="log3">
<br><br><br><br><br><br><br><br><br>

<c:choose>
   <c:when test="${sid == null }">
         
         <form class="box3" action="loginchk">
            <input type="text" name="id" id="id" placeholder="ID"><br>
            <input type="password" id="pw" name="pw" placeholder="Password"><br>
            <a href="idsearch">아이디 찾기</a>&nbsp; | &nbsp;<a href="pwsearch">비밀번호 찾기</a>
            <br>
            <input type="button" onclick="regis()" value="회원가입">
            <input type="submit" value="로그인"><br>   
            <a href="${url}"><img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/></a><br>
            <a href="https://kauth.kakao.com/oauth/authorize?client_id=dab51a4dfebbddccbe7e1f2a9b97f6cd&redirect_uri=http://localhost:8082/controller/kakaologin&response_type=code">
                  <img src="resources/logo/kakao.png">
            </a>
         </form> 
      
   </c:when>
   <c:otherwise>
      ${sid }님
      ${snick }닉네임네임네임
      <c:choose>
         <c:when test="${access_Token != null }">
            <form class="box3">
            카카오
               <input type="button" value="마이페이지" onclick="mypage();">
               <input type="button" value="로그아웃" onclick="kakaolog();">
            </form>
         </c:when>
         <c:otherwise>
         
            <form class="box3">
               <input type="button" value="마이페이지" onclick="mypage();">
               <input type="button" value="쪽지함" onclick="chkMsg()">
               <input type="button" value="로그아웃" onclick="click_logout();">
            </form>
         </c:otherwise>
      </c:choose>
      
      
   </c:otherwise>
</c:choose>


</div>


</body>
</html>