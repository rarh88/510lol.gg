<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function lols(){
	
	var hp; 
    var vp; 
    hp = ( screen.width - 640 ) / 2 + 1; 
    vp = ( screen.height - 800 ) / 2 + 1; 
	
	
	var lolsurl = "lols";
	var lolsoption = "width=800, height=800,resizable=no, scrollbars=no, status=no,  left="+hp+", top="+vp+"";

		window.open(lolsurl,"",lolsoption);
}
</script>

<style>
	.naverlogin{width:80%; margin:0 auto; text-align:center;}
	.boxnaver{width:100% }
	
	.boxnaver input[type = "text"]{
	
		border:0;
		background:none;
		display:block;
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
	
	.boxnaver input[type="text"]:focus,.box4 input[type = "password"]:focus{
		width:280px;
		border-color:#2ecc71;
	}
	
	.boxnaver input[type="submit"]{
		left:70%;
		background:none;
		align:center; float:center;
		text-align:center;
		border:2px solid #2ecc71;
		width:100px; height:50px;
		color:black;
		border-radius:24px;
		transition: 0.25s;
		cursor:pointer;
	}
	
	.boxnaver input[type = "submit"]:hover{
		background:#2ecc71;
	}
	
	.boxnaver input[type="button"]{
		left:70%;
		background:none;
		align:center; float:center;
		text-align:center;
		border:2px solid #2ecc71;
		width:100px; height:50px;
		color:black;
		border-radius:24px;
		transition: 0.25s;
		cursor:pointer;
	
	}
	
	
	.boxnaver input[type="button"]:hover{
		background:#2ecc71;
	}
	
	
	
	
	
</style>








</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="naverlogin">
	<c:choose>
		<c:when test="${a == 1}">
			<script>
				alert("로그인되었습니다!");
				location.href="login";
			</script>
			
		</c:when>
		
		<c:otherwise>
			<h1>${sname}님 환영합니다<br></h1>
		
			<form class="boxnaver" action="regichks">
				<input type="text" name="phonenum" placeholder="핸드폰번호"><br>
				<input type="submit" value="회원가입">
				<input type="button" onclick="lols()" value="롤닉네임검색" id="nicks">
			</form>
		</c:otherwise>
	
	
	</c:choose>
</div>	
	
	
	

	


</body>
</html>