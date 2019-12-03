<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>



<style type="text/css">
	.hd1{background-image:url("resources/logo/opgg1-2.png");
	background-repeat:no-repeat; background-position : center;
	 width:100%; height:250px; margin: 0 auto; position:static;}
	
	.title{background-color:yellow; width:1500px; height:58px; margin: 0 auto; text-align:cetner; position:static; } 
	
	.heada { text-decoration:none }
</style>

<style>


body {
   


}

</style>

<style>
	.hedbox{width:100%; height:50px; align:center; text-align:center; background:#1a1d21; }
	
	.hedbox input[type="button"]{
	      background:none;
	      text-align:center;
	      border:2px solid #b59a61;
	     
	      outline: none;
	      color:white;
	      border-radius:24px;
	      transition: 0.25s;
	      cursor:pointer;
			width:60px;
			height:40px;
			margin:0 0 0 0;
		align:center;
	}
	.hedbox input[type="button"]:hover{
		background:#b59a61;
	}


</style>



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
				<div class="hedbox">
					<input type="button" onclick="home()" value="Home">
					<input type="button" onclick="login()" value="Login">
					<input type="button" onclick="ranking()" value="Ranking">
					<input type="button" onclick="mypage()" value="Mypage">
					<input type="button" onclick="party()" value="Party">
					<input type="button" onclick="board()" value="Board">
				</div>
		    </c:when>
		    <c:otherwise>
		    	<div class="hedbox">
					<input type="button" onclick="home()" value="Home">
					<input type="button" onclick="logout()" value="Logout">
					<input type="button" onclick="ranking()" value="Ranking">
					<input type="button" onclick="mypage()" value="Mypage">
					<input type="button" onclick="party()" value="Party">
					<input type="button" onclick="board()" value="Board">				
				
				
				
				
				</div>
		    </c:otherwise>
		    </c:choose>
	<div class="hd1">

	</div>
	
		
		  
		






</body>
</html>