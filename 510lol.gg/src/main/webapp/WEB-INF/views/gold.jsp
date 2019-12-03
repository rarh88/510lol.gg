<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	var wsocket;
	
	function connect() {
		wsocket = new WebSocket("ws://192.168.0.46:8082/controller/chat/websocket");
		wsocket.onopen = onOpen;
		
		//서버로부터 메시지를 받으면 호출되는 함수 지정
		wsocket.onmessage = onMessage;
		wsocket.onclose = onClose;
		
		$('#message').attr('disabled',false);
	}
	
	function disconnect() {
		var msg = 'msg:[' + $('#nickname').val() + '퇴장!]';
		wsocket.send(msg);
	}
	
	function onOpen(evt) {
		var msg = 'msg:[' + $('#nickname').val() + '입장!]';
		wsocket.send(msg);
	}
	
	function onMessage(evt) {
		var data = evt.data;
		if(data.substring(0,4) == 'msg:') {
			appendMessage(data.substring(4));
			
				/* 닉네임 추출 == 자기 닉네임과 같은지 체크 */
			if(data.substring(5,data.lastIndexOf('퇴장!')) 
					== $('#nickname').val() && data.lastIndexOf('퇴장!') != -1) {
				wsocket.close();
			}
		}
	}
	
	function onClose(evt) {
		//퇴장 한 이후 부과적인 작업이 있을 경우 명시
		$('#nickname').val("");
		$('#message').attr('disabled',true);
	}
	
	function send() {
		var nickname = $('#nickname').val();
		var msg = $('#message').val();
		wsocket.send("msg:" + nickname + ':' + msg);
		$('#message').val('');
	}
	
	function appendMessage(msg) {
		$('#chatMessageArea').append(msg + '<br>');
		var chatAreaHeight = $('#chatArea').height();
		var maxScroll = $('#chatMessageArea').height() - chatAreaHeight;
		$('#chatArea').scrollTop(maxScroll);
	}
	function init(){
		if($('#nickname').val() == ''){
			alert('이름을 입력하세요!');
			$('#nickname').focus();
			return;
		}
		connect();
	}
	$(document).ready(function() {
		$('#message').keypress(function(event) {
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13') {
				send();
			}
			event.stopPropagation();
		});
		$('#sendBtn').click(function(){
			send();
		});
		$('#enterBtn').click(function(){
			if($('#nickname').val() == ''){
				alert('이름을 입력하세요!');
				$('#nickname').focus();
				return;
			}
			connect();
		});
		$('#exitBtn').click(function(){
			if($('#nickname').val() == ''){
				alert('이름을 입력하세요!');
				$('#nickname').focus();
				return;
			}
			disconnect();
			location.href="home";
		});
	});
	
	function memberview(){
		
	}
	
</script>
<style type="text/css">
#chatArea {
	margin: 0;
	width:300px;
	height:100px;
	overflow-y:auto;
	border:1px solid black;
}
</style>
<style type="text/css">
	#memberArea{
		margin: 0;
		width: 100px;
		height: 450px;
		overflow-y:auto;
		border: 1px solid black;
	}
</style>
</head>

<body onload="init()">
	<input type="hidden" id="nickname" value="${snick }">
	<input type="button" id="exitBtn" value="나가기">
	
		<a>참여자 목록</a>	
		<div id="memberArea">
		<div id="chatMemberArea"></div>	
		</div>
		
	<h1>골드방 입니다</h1>
	<div id="chatArea">
		<div id="chatMessageArea"></div>
	</div>
	<br>
	<input type="text" id="message" disabled>
	<input type="button" id="sendBtn" value="전송">
	<div>
	</div>
</body>
</html>