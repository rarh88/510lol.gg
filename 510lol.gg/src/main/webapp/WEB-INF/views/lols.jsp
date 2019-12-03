<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.lols1{
				width:500px;  position:absolute;
				top:300px; left:50%; transform: translate(-50%,-50%);
				bakcground:#191919; text-align:center;

	}
	.lols1 input[type="text"]{
		border:0;
		background:none;
		margin:5px auto;
		text-align:center;
		border: 2px solid #3498db;
		padding: 14px 10px;
		width:200px;
		outline:none;
		color:black;
		border-radius:24px;
		transition:0.25s;
	
	}

	.lols1 input[type="submit"]{
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
	.lols1 input[type = "submit"]:hover{
		background:#2ecc71;
	}



</style>



</head>
<body>

<div class="lols1">
	<form action="findUserRe1">
		<input type="text" name="nickname" placeholder="닉네임">
		<input type="submit" value="찾기">
	</form>
</div>
</body>
</html>