<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>
<script>
	function chk(){
		var id = $('#id').val();
		var form ={id : id}
		
		$.ajax({
			type:"GET",
			url:"ajax_chk",
			data : form ,
			dataType : "json",
			contentType : 'application/json; charset=UTF-8',
			success: function(json){
				var a = json.a;
				
				if(a == 0){
					alert("사용가능한아이디입니다.")
					document.getElementById("btn").style.display="none";
					
				}else{
					alert("중복된아이디입니다.")
				}
				
			},
			error:function(){
				alert("에러입니다.")
			}
		});
		
	}
	
	
	
	
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

<script>
function pwchk(){
	pw1 = document.getElementById("pw").value;
	pw2 = document.getElementById("pw2").value;
	if(pw1 == pw2){
		document.getElementById("label").innerHTML="확인";
	}else{
		alert("비밀번호가일치하지않습니다!")
		document.getElementById("label").innerHTML="비밀번호가 일치하지 않습니다.";
		document.getElementById("pw").value="";
		document.getElementById("pw2").value="";
	}
}
function chkValidate(){
	if(document.getElementById('id').value == ""){
		alert('아이디는 필수 입력 사항입니다.');
		return;
	}else if(document.getElementById('pw').value == ""){
		alert('비밀번호는 필수 입력 사항입니다.');
		return;
	}else if(document.getElementById('phonenum').value==""){
		alert("핸드폰번호를 입력해주세요.");
		return;
	}
	else{ 
		document.form.submit();
	 }
}

function back(){
	location.href="login";
	
}

</script>

<style>
		.mem1{width:1500px; margin:0 auto; text-align:center; font-family:sans-serif; background-color:white;}
		
		.box{width:500px;  position:absolute;
				top:600px; left:50%; transform: translate(-50%,-50%);
				bakcground:#191919; text-align:center;
	}
	
	
	
	.box input[type = "text"],.box input[type = "password"]{
	border:0;
	background:none;
	margin:5px auto;
	text-align:center;
	border: 2px solid #3498db;
	padding: 14px 10px;
	width:200px;
	outline:none;
	color:white;
	border-radius:24px;
	transition:0.25s;
	}
	
	.box input[type="text"]:focus,.box input[type = "password"]:focus{
		width:280px;
		border-color:#2ecc71;
	}
	.box input[type="submit"]{
		border:0;
		background:none;
		
		margin:20px auto;
		text-align:center;
		border:2px solid #2ecc71;
		padding:14px 40px;
		outline: none;
		color:white;
		border-radius:24px;
		transition: 0.25s;
		cursor:pointer;
	}
	.box input[type = "submit"]:hover{
		background:#2ecc71;
	}
	
	.box input[type = "button"]{
		border:0;
		background:none;
		
		margin:10px auto;
		text-align:center;
		border:2px solid #b59a61;
		padding:14px 40px;
		outline: none;
		color:white;
		border-radius:24px;
		transition: 0.25s;
		cursor:pointer;
	}
	.box input[type="button"]:hover{
		background:#b59a61;
	}
		

	</style>

<style>
body{background-color:#1a1d21;}

.regi1{
		top:50%;
		background-color:#3e444d;
	 	width:500px;
		height:900px;
		margin:0 auto;
	 	border: 3px solid #121211;
	 	border-radius:30px;
	 	color:white;
	 	align:center;
	
	
	
	}
.regi1logo{
	width:100%;
	height:300px;
	background-image:url("resources/logo/opgg8.png");
	background-repeat:no-repeat; background-position : center;
	margin:0 auto;
	

}	

	
	
img{width:200px; height:200px; margin:0 auto;}

</style>

</head>

<body>

<div class="regi1">
	<div class="regi1logo">
	</div>


	<table class="box" border="0">
	
	<form class="box" name="form" action="regichk" method='post'>
	<tr>
		<td><input type="text" id="id" name="id" placeholder="아이디"></td>
		<td><input type="button" onclick="chk()" value="중복확인" id="btn"></td>
	</tr>
	<tr>	
		<td><input type="password" id="pw" name="pw" placeholder="비밀번호"></td>
	</tr>	
	<tr>
		<td><input type="password" id="pw2" onchange="pwchk();" placeholder="비밀번호 확인"></td>
		<td><label id="label">(*필수 체크)</label></td>
	</tr>
	<tr>
		<td><input type="text" id="id" name="name" placeholder="이름"></td>
	</tr>	
	<tr>	
		<td><input type="text" id="phonenum" name="phonenum" placeholder="핸드폰번호 '-'제외"></td>
	</tr>
	<tr>	

		<td colspan="2"><input type="button" onclick="lols()" value="롤닉네임검색" id="nicks"></td>
	</tr>
	<tr>
		<td><input type="button" value="회원가입" onclick="chkValidate()"></td>
		<td><input type="button" value="뒤로가기" onclick="back()"></td>
	</tr>
	
	</form>
	
	</table>
</div>	
</body>
</html>