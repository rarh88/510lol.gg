<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>



<style type="text/css">
	.hd1{background-image:url("resources/logo/opgg6.png");
	background-repeat:no-repeat; background-position : center;
	width:100%; height:250px; margin: 0 auto;}
	
	.title{background-color:yellow; width:1500px; height:58px; margin: 0 auto; text-align:cetner; position:static; } 
	
	.heada { text-decoration:none }
</style>



<style>
	.hedbox2{width:100%; height:50px; align:center; background-color:#1a1d21;  }
	.hedbox3 {width:30%; height:50px; float:right; align:center; background-color:#1a1d21; text-align:center;}
	
	
	.hedbox2 input[type="button"]{
	      background:none;
	      text-align:center;
	      border:2px solid #b59a61;
	     align:left;
	      outline: none;
	      color:white;
	      border-radius:24px;
	      transition: 0.25s;
	      cursor:pointer;
			width:60px;
			height:40px;
			margin:0 0 0 0;
		
	}
	.hedbox2 input[type="button"]:hover{
		background:#b59a61;
	}
	
	
	.hedbox3 input[type="text"]{
	
      background:none;
      

      text-align:center;
      border: 2px solid #b59a61;
     height:40px;
      width:280px;
      outline:none;
      color:white;
      border-radius:24px;
      transition:0.25s;
      font-size:15px;

	}
	.hedbox3 input[type="text"]:focus{
      width:300px;

      border-color:#b59a61;
      font-size:15px;
   }
	
	
	.hedbox3 input[type="submit"]{
		width:60px;
      background:none;
      height:40px;
      text-align:center;
      border:2px solid #b59a61;
      outline: none;
      color:white;
      border-radius:24px;
      transition: 0.25s;
      cursor:pointer;
	
	}
	.hedbox3 input[type = "submit"]:hover{
      background:#b59a61;
   }

</style>









<script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>




<script>
	function home(){
		location.href="main"
	}
	function login(){
		location.href="login"
	}
	function logout(){
		location.href="login"
	}
	function ranking(){
		location.href="League-exp"
	}
	function mypage(){
		location.href="mypage"
	}
	function party(){
		location.href="updateparty"
	}
	function board(){
		location.href="totalboard"
	}

</script>


</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:choose>
			<c:when test="${sid == null }">
				<div class="hedbox2">
					<input type="button" onclick="home()" value="Home">
					<input type="button" onclick="login()" value="Login">
					<input type="button" onclick="ranking()" value="Ranking">
					<input type="button" onclick="mypage()" value="Mypage">
					<input type="button" onclick="party()" value="Party">
					<input type="button" onclick="board()" value="Board">
					<div class="hedbox3">
							<jsp:include page="../FindUser1.jsp"/>
					</div>
				</div>
				
		    </c:when>
		    <c:otherwise>
		    	<div class="hedbox2">
					<input type="button" onclick="home()" value="Home">
					<input type="button" onclick="logout()" value="Logout">
					<input type="button" onclick="ranking()" value="Ranking">
					<input type="button" onclick="mypage()" value="Mypage">
					<input type="button" onclick="party()" value="Party">
					<input type="button" onclick="board()" value="Board">				
					<div class="hedbox3">
							<jsp:include page="../FindUser1.jsp"/>
					</div>
				</div>
		    </c:otherwise>
		    </c:choose>
	<div class="hd1">

	</div>
	
		
		  
		






</body>
</html>