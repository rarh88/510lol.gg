<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/*top으로 위치조절*/
	.box4{width:800px;  position:relative;
		top:150px; left:50%; transform: translate(-50%,-50%);
		background:#1a1d21; text-align:center;
		color:white;
	}
	a{ text-decoration:none }
	
	.pg{ width:800px; height:100px; left:50%; text-align:center;}
	.pg li{left:20%; width:50px;}
	
	.rank1{width:800px; top:100px; text-align:center; 
	position:relative; left:25%;
	font-family:sans-serif; background:#1a1d21; color:white;}
	
	body{background:#1a1d21; color:white;}	
	
	table{ text-align:center; width:800px;}
	
	option{color:#b59a61;}
	
	
	.box4 input[type = "text"],.box4 input[type = "password"]{
	border:0;
	background:none;
	display:block;
	margin:20px auto;
	text-align:center;
	border: 2px solid #3498db;
	padding: 14px 10px;
	width:200px;
	outline:none;
	color:white;
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
		display:block;
		margin:20px auto;
		text-align:center;
		border:2px solid #b59a61;
		padding:14px 40px;
		outline: none;
		color:white;
		border-radius:24px;
		transition: 0.25s;
		cursor:pointer;
	}
	.box4 input[type = "submit"]:hover{
		background:#b59a61;
	}
	
	.box4 input[type = "button"]{
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
	.box4 input[type="button"]:hover{
		background:#2ecc71;
	}
	.box4 select{
		border:0;
		background:none;
		font-size:25px;
		margin:30px auto;
		text-align:center;
		border:2px solid #2eaccc;
		padding:14px 40px;
		outline: none;
		color:black;
		border-radius:24px;
		transition: 0.25s;
		cursor:pointer;

	}
	.box4 option{
		font-size:20px;
	}
</style>


<style>
.pg nav ul {
    list-style-type: none;
   	margin: 30px auto;
}
.pg nav ul li {
	float:left;
	/* 클릭바 색깔 */
    --c: skyblue;
    color: var(--c);
    font-size: 16px;
    border: 0.3em solid var(--c);
    border-radius: 0.5em;
    width: 3em;
    height: 2em;
    text-transform: uppercase;
    font-weight: bold;
    font-family: sans-serif;
    letter-spacing: 0.1em;
    text-align: center;
    line-height: 2em;
    position: relative;
    overflow: hidden;
    z-index: 1;
    transition: 0.5s;
    margin:0 1em 0 5em;
   
}

.pg nav ul li span {
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: var(--c);
    transform: translateY(150%);
    border-radius: 50%;
    left: calc((var(--n) - 1) * 25%);
    transition: 0.5s;
    transition-delay: calc((var(--n) - 1) * 0.1s);
    z-index: -1;

}

.pg nav ul li:hover {
    color: black;
}

.pg nav ul li:hover span {
    transform: translateY(0) scale(2);
}

.pg nav ul li span:nth-child(1) {
    --n: 1;
}

.pg nav ul li span:nth-child(2) {
    --n: 2;
}

nav ul li span:nth-child(3) {
    --n: 3;
}

nav ul li span:nth-child(4) {
    --n: 4;
}

</style>

<style>
.hd1{
	background-image:url("resources/logo/opgg6.png");
	background-repeat:no-repeat; background-position : center;

}



</style>



<script>
	function divisions(e){
		var masters = ["I"];
		var dias = ["I","II","III","IV"];
		var target = document.getElementById("division");
		
		if(e.value == "CHALLENGER"){ var d = masters;}
		else if(e.value == "GRANDMASTER"){ var d = masters;}
		else if(e.value == "MASTER"){ var d = masters;}
		else{var d = dias;}
		 
		  target.options.length = 0;

		  for (x in d) {
		    var opt = document.createElement("option");
		    opt.value = d[x];
		    opt.innerHTML = d[x];
		    target.appendChild(opt);
		  } 
	}

</script>
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="css/header3.jsp"/>
<div class="box4">
	<form class="box4" action="league-exp-re">
		<select name="queue">
			<option>--Queue--</option>
			<option value="RANKED_SOLO_5x5" >솔로</option>
			<option value="RANKED_TFT">롤토체스</option>
			<option value="RANKED_FLEX_SR">5:5팀랭</option> 
			<option value="RANKED_FLEX_TT">3:3팀랭</option> 
		</select>
		<select name="tier" onchange="divisions(this)">
			<option>--Tier--</option>
			<option value="CHALLENGER" >챌린저</option>
			<option value="GRANDMASTER">그마</option>
			<option value="MASTER">마스터</option> 
			<option value="DIAMOND">다이아</option>
			<option value="PLATINUM">플레</option>
			<option value="GOLD">골드</option> 
			<option value="SILVER">실버</option> 
			<option value="BRONZE">브론즈</option>
			<option value="IRON">아이언</option> 
		</select>
			<select name="division" id="division">
			<option>--Division--</option>
		</select>
		<input type="hidden" value="1" name="page">
		<input type="submit" value="Search">
	</form>
</div>

<div class="rank1">
	<table>
			<tr>
				<th>소환사명</th><th colspan="2">티어</th><th>점수</th><th>승</th><th>패</th>
			</tr>
	<c:forEach var="dto" items="${leagueexp }">
		<tr>
			<td>${dto.summonerName }</td>
			<td>${dto.tier }</td>
			<td>${dto.rank }</td>
			<td>${dto.leaguePoints }</td>
			<td>${dto.wins }</td>
			<td>${dto.losses }</td>
		</tr>
	</c:forEach>
	</table>

<hr>

	<div class="pg">
	<nav>
	 <ul>
		<li>
		<a href="League-exp?page=1">1</a>
		<span></span><span></span><span></span><span></span>		    
		</li>
		<li>
		<a href="League-exp?page=2">2</a>
		<span></span><span></span><span></span><span></span>	
		</li>
		</ul>
		</nav>
	</div>
</div>









</body>
</html>