<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
@import url(https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300);
*, *:before, *:after {
  margin:0;
  padding:0;
  -webkit-box-sizing:border-box;
  -moz-box-sizing:border-box;
  box-sizing:border-box;
}
body {

  -webkit-transform:translateZ(0);
}
html, body {
  width:100%;
  height:100%;
}
/*
----------------------------------------------
Wrapper 
----------------------------------------------
*/
#loader {
  position:absolute;
  top:0;
  right:0;
  bottom:0;
  left:0;
  width:100%;
  height:100%;
  display:flex;
  flex-direction:column;
  justify-content:center;
  align-items:center;
  background:#E9E8E3;
  z-index:99;
  -webkit-animation:offscreen .5s 15s forwards;
  animation:offscreen .5s 15s forwards;
}
#refresher {
  position:absolute;
  top:0;
  right:0;
  bottom:0;
  left:0;
  width:100%;
  height:100%;
  display:flex;
  flex-direction:column;
  justify-content:center;
  align-items:center;
  z-index:1;
  opacity:0;
  -webkit-animation:show .15s 16s forwards;
  animation:show .15s 16s forwards;
}
.refresh {
  color:#E9E8E3;
  font-family:'Open Sans Condensed', sans-serif;
  font-size:2rem;
  text-transform:uppercase;
  letter-spacing:.1rem;
}
.face {
  width:270px;
  height:270px;
}
/*
----------------------------------------------
SVG 
----------------------------------------------
*/
.head {
  transform-origin: 135px 135px;
  transform:scale(0);
  -webkit-animation:popup .6s 1s cubic-bezier(0.95, 0.05, 0.795, 0.035) forwards;
  animation:popup .6s 1s cubic-bezier(0.95, 0.05, 0.795, 0.035) forwards;
}
#eyeLeft, #eyeRight {
  transform:scale(0);
}
#eyeLeft {
  transform-origin:66px 127px;
  -webkit-animation:popup .5s 1.2s cubic-bezier(0.95, 0.05, 0.795, 0.035) forwards;
  animation:popup .5s 1.2s cubic-bezier(0.95, 0.05, 0.795, 0.035) forwards;
}
#eyeRight {
  transform-origin:203px 127px;
  -webkit-animation:popup .5s 1.25s cubic-bezier(0.95, 0.05, 0.795, 0.035) forwards;
  animation:popup .5s 1.25s cubic-bezier(0.95, 0.05, 0.795, 0.035) forwards;
}
.eyeLeft {
  transform-origin:66px 127px;
  -webkit-animation:downup 12.1s 1.6s cubic-bezier(.44,0,.01,.99) forwards;
  animation:downup 12.1s 1.6s cubic-bezier(.44,0,.01,.99) forwards;
}
.eyeRight {
  transform-origin:203px 127px;
  -webkit-animation:downup 12.1s 1.6s cubic-bezier(.44,0,.01,.99) forwards;
  animation:downup 12.1s 1.6s cubic-bezier(.44,0,.01,.99) forwards;
}
.eyeLeft > .pupil {
  transform-origin:66px 127px;
  -webkit-animation:follow 12.1s 1.6s forwards;
  animation:follow 12.1s 1.6s forwards;
}
.eyeRight > .pupil {
  transform-origin:203px 127px;
  -webkit-animation:follow 12.1s 1.6s forwards;
  animation:follow 12.1s 1.6s forwards;
}
.closedLeft {
  transform-origin:66px 127px;
  -webkit-animation:blink 12.1s 1.6s cubic-bezier(.44,0,.01,.99) forwards;
  animation:blink 12.1s 1.6s cubic-bezier(.44,0,.01,.99) forwards;
} 
.closedRight {
  transform-origin:203px 127px;
  -webkit-animation:blink 12.1s 1.6s cubic-bezier(.44,0,.01,.99) forwards;
  animation:blink 12.1s 1.6s cubic-bezier(.44,0,.01,.99) forwards;
}
.mouth {
  transform-origin:135px 233px;
  -webkit-animation:mouth 12.1s 1.6s cubic-bezier(.44,0,.01,.99) forwards;
  animation:mouth 12.1s 1.6s cubic-bezier(.44,0,.01,.99) forwards;
}
.bar {
  transform-origin:135px 233px;
  transform:scale(0);
  -webkit-animation:patient 12.8s 1s ease-in forwards;
  animation:patient 12.8s 1s ease-in forwards;
}
.smile {
  transform-origin:135px 233px;
  transform:scale(0);
  -webkit-animation:happy 12.9s 1s ease-in forwards;
  animation:happy 12.9s 1s ease-in forwards;
}
/*
----------------------------------------------
LOADING BAR 
----------------------------------------------
*/
.loading-bar {
  position:relative;
  margin-top:4rem;
  width:250px;
  background:#C1C0BC; 
}
.loading-bar:before {
  content:'';
  position:absolute;
  top:0;
  left:0;
  width:0;
  background:#58585A;
  -webkit-animation:load 12.4s 1s forwards;
  animation:load 12.4s 1s forwards;
}
.loading-bar, .loading-bar:before {
  height:4px;
  border-radius:5px;
}
/*
----------------------------------------------
ANIMATIONS
----------------------------------------------
*/
@keyframes popup {
  0% {transform:scale(0);}
  80% {transform:scale(1);}
  90% {transform:scale(0.75);}
  100% {transform:scale(1);}
}
@keyframes downup {
  0% {transform: translateY(0);}
  15% {transform: translateY(40px);}
  50% {opacity:1;}
  50.7% {opacity:0;}
  51.4% {opacity:1;}
  98% {transform: translateY(40px);}
  100% {transform: translateY(0);}
}
@keyframes mouth {
  0% {transform: translateY(-20px);}
  15% {transform: translateY(0px);}
  100% {transform: translateY(0px);}
}
@keyframes blink {
  0% {transform: translateY(0);}
  15% {transform: translateY(40px);}
  50% {opacity:1;}
  98% {transform: translateY(40px);}
  100% {transform: translateY(0);}
}
@keyframes follow {
     0% {transform: translate(0, 0);}
     15% {transform: translate(-4px, 8px);}
     95.5% {transform: translate(-2px, 10px);}
     98% {transform: translate(8px, 10px);}
     100% {transform: translate(0, 0);}
}
@keyframes patient {
     0% {transform:scale(0);}
     3% {transform:scale(0);}
     3.5% {transform:scale(1);}
     4% {transform:scale(0.75);}
     4.5% {transform:scale(1);}
     99% {transform:scale(1);}
     100% {transform:scale(0);}
}
@keyframes happy {
  0% {transform:scale(0);}
  98% {transform:scale(0);}
  100% {transform:scale(1);}
}
@keyframes load {
  0% {width:0;}
  97% {width:50px;}
  100% {width:250px;}
}
@keyframes offscreen {
  0% {transform:translateY(0);}
  100% {transform:translateY(-200%);}
}
@keyframes show {
  0% {opacity:0;}
  100% {opacity:1;}
}


</style>

<script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>
<script>
function load(){
	   $('box1').hide();
	   $('#loader').show();
	}
</script>


</head>
<body>
<div class="box2">
   <form class="box" id="test" action="findUserRe" method='post'>
      <input type="text" name="nickname" id="nickname">
      <input type="submit" onclick="load()" value="찾기">
      
   </form>
</div>

<div id="loader" style="display:none">
  <div class="face">
  <?xml version="1.0" encoding="utf-8" standalone="yes"?>
  <svg version="1.1"
      xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
      x="0px" y="0px" width="270px" height="270px" viewBox="0.032 0 270 270"
      overflow="visible" enable-background="new 0.032 0 270 270" xml:space="preserve">
  <defs>
  </defs>
  <g id="supervisior">
    <g class="head">
       <path fill="#58585A" d="M135,10c33.389,0,64.778,13.002,88.388,36.612S260,101.611,260,135s-13.003,64.779-36.612,88.388
      S168.389,260,135,260s-64.778-13.002-88.388-36.612C23.002,199.779,10,168.389,10,135s13.002-64.779,36.612-88.388
      C70.222,23.002,101.611,10,135,10 M135,0C60.442,0,0,60.441,0,135s60.442,135,135,135s135-60.441,135-135S209.558,0,135,0L135,0z"
        />
    </g>
    <g id="eyeLeft">
      <g class="eyeLeft">
        <circle class="eye" fill="none" stroke="#58585A" stroke-width="4" stroke-linecap="round" stroke-miterlimit="10" cx="66.063" cy="127.51" r="18.849"/>
        <circle class="pupil" fill="#58585A" cx="66.063" cy="127.511" r="8.949"/>
      </g>
      <path class="closedLeft" fill="none" stroke="#58585A" stroke-width="4" stroke-linecap="round" stroke-miterlimit="10" d="
   M47.238,127.972c0.247,10.194,8.57,18.387,18.824,18.387s18.579-8.193,18.826-18.387"/>
    </g>
    <g id="eyeRight">
      <g class="eyeRight">
        <circle class="eye" fill="none" stroke="#58585A" stroke-width="4" stroke-linecap="round" stroke-miterlimit="10" cx="203.936" cy="127.51" r="18.849"/>
        <circle class="pupil" fill="#58585A" cx="203.935" cy="127.511" r="8.949"/>
      </g>
      <path class="closedRight" fill="none" stroke="#58585A" stroke-width="4" stroke-linecap="round" stroke-miterlimit="10" d="
   M185.11,127.972c0.247,10.194,8.571,18.387,18.824,18.387c10.255,0,18.579-8.193,18.826-18.387"/>
    </g>
    <g class="mouth">
      <polyline class="bar" fill="none" stroke="#58585A" stroke-width="4" stroke-linecap="round" stroke-miterlimit="10" points="
   111.532,232.832 135.032,232.832 158.532,232.832 "/>
      <path class="smile" fill="none" stroke="#58585A" stroke-width="4" stroke-linecap="round" stroke-miterlimit="10" d="
   M62.485,168.615c0.951,35.593,33.016,64.198,72.511,64.198c39.503,0,71.566-28.605,72.519-64.198"/>
    </g>
  </g>
  </svg>
  </div>
<div class="loading-bar"></div>
</div>




</body>
</html>