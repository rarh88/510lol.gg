<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var maxChecked = 3;   
	var totalChecked = 0;
	var selectCount = 0;
	function selectLine(){
		if(selectCount > 0){
			document.getElementById("Top").style.display="none";
			document.getElementById("Jungle").style.display="none";
			document.getElementById("Mid").style.display="none";
			document.getElementById("Ad").style.display="none";
			document.getElementById("Sup").style.display="none";
		
			
		}
		var line = document.getElementById("line").value;
		document.getElementById(line).style.display="block";
		document.getElementById("btt").style.display="block";
		
		selectCount+=1;
	}
	
	function ChampCheck(field){
		if(field.checked){
			totalChecked += 1;
		}else{
			totalChecked -= 1;
		}
		
		if(totalChecked>maxChecked){
			alert("3개만 선택해주세요!!");
			field.checked = false;
		    totalChecked -= 1;
	
		}
	}
	function checking(){
		if(totalChecked == 0){
			alert("챔피언을 선택해주세요 !!!");
			
		}
	}
	

</script>
</head>
<body>
		<c:set var="sid" value="doo댕"/>
		<select id="line" onchange="selectLine()">
			<option>--Main Line--</option>
			<option value="Top" >Top</option>
			<option value="Jungle">Jungle</option>
			<option value="Mid">Mid</option>
			<option value="Ad">Ad</option> 
			<option value="Sup">Sup</option> 
		</select>
	
		<form action="goMatching">
		<input type="hidden" name="sid" value="${sid}">
	    <fieldset id="Top" style="display:none">
                <legend>Top</legend>
                	가렌<input type="checkbox" id="Top_champ" name="Top_champ" value="86" onclick="ChampCheck(this)"/> 
                	갱플랭크<input type="checkbox" id="Top_champ" name="Top_champ" value="41" onclick="ChampCheck(this)"/>  
                	나르<input type="checkbox" id="Top_champ" name="Top_champ" value="150" onclick="ChampCheck(this)"/>  
                	나서스<input type="checkbox" id="Top_champ" name="Top_champ" value="75" onclick="ChampCheck(this)"/>  
                	녹턴<input type="checkbox" id="Top_champ" name="Top_champ" value="56" onclick="ChampCheck(this)"/>  
            		다리우스<input type="checkbox" id="Top_champ" name="Top_champ" value="122" onclick="ChampCheck(this)"/>  
            		라이즈<input type="checkbox" id="Top_champ" name="Top_champ" value="13" onclick="ChampCheck(this)"/>  
            		럼블<input type="checkbox" id="Top_champ" name="Top_champ" value="68" onclick="ChampCheck(this)"/>  
            		레넥톤<input type="checkbox" id="Top_champ" name="Top_champ" value="58" onclick="ChampCheck(this)"/>  
            		렝가<input type="checkbox" id="Top_champ" name="Top_champ" value="107" onclick="ChampCheck(this)"/>  
            		리븐<input type="checkbox" id="Top_champ" name="Top_champ" value="92" onclick="ChampCheck(this)"/>  
            		마오카이<input type="checkbox" id="Top_champ" name="Top_champ" value="57" onclick="ChampCheck(this)"/>  
            		말자하<input type="checkbox" id="Top_champ" name="Top_champ" value="90" onclick="ChampCheck(this)"/>  
            		말파이트<input type="checkbox" id="Top_champ" name="Top_champ" value="54" onclick="ChampCheck(this)"/>  
            		모데카이저<input type="checkbox" id="Top_champ" name="Top_champ" value="82" onclick="ChampCheck(this)"/>  
            		문도박사<input type="checkbox" id="Top_champ" name="Top_champ" value="36" onclick="ChampCheck(this)"/>  
            		베인<input type="checkbox" id="Top_champ" name="Top_champ" value="67" onclick="ChampCheck(this)"/>  
            		볼리베어<input type="checkbox" id="Top_champ" name="Top_champ" value="106" onclick="ChampCheck(this)"/>  
            		블라디미르<input type="checkbox" id="Top_champ" name="Top_champ" value="8" onclick="ChampCheck(this)"/>
            		빅토르<input type="checkbox" id="Top_champ" name="Top_champ" value="112" onclick="ChampCheck(this)"/>
            		뽀삐<input type="checkbox" id="Top_champ" name="Top_champ" value="78" onclick="ChampCheck(this)"/>
               		사이온<input type="checkbox" id="Top_champ" name="Top_champ" value="14" onclick="ChampCheck(this)"/>
               		사일러스<input type="checkbox" id="Top_champ" name="Top_champ" value="517" onclick="ChampCheck(this)"/>
               		쉔<input type="checkbox" id="Top_champ" name="Top_champ" value="98" onclick="ChampCheck(this)"/>
               		신지드<input type="checkbox" id="Top_champ" name="Top_champ" value="27" onclick="ChampCheck(this)"/>
               		아칼리<input type="checkbox" id="Top_champ" name="Top_champ" value="84" onclick="ChampCheck(this)"/>
               		아트록스<input type="checkbox" id="Top_champ" name="Top_champ" value="266" onclick="ChampCheck(this)"/>
               		야스오<input type="checkbox" id="Top_champ" name="Top_champ" value="157" onclick="ChampCheck(this)"/>
               		오른<input type="checkbox" id="Top_champ" name="Top_champ" value="516" onclick="ChampCheck(this)"/>
               		올라프<input type="checkbox" id="Top_champ" name="Top_champ" value="2" onclick="ChampCheck(this)"/>
               		요릭<input type="checkbox" id="Top_champ" name="Top_champ" value="83" onclick="ChampCheck(this)"/>
               		우르곳<input type="checkbox" id="Top_champ" name="Top_champ" value="6" onclick="ChampCheck(this)"/>
               		워윅<input type="checkbox" id="Top_champ" name="Top_champ" value="19" onclick="ChampCheck(this)"/>
               		이렐리아<input type="checkbox" id="Top_champ" name="Top_champ" value="39" onclick="ChampCheck(this)"/>
               		일라오이<input type="checkbox" id="Top_champ" name="Top_champ" value="420" onclick="ChampCheck(this)"/>
               		잭스<input type="checkbox" id="Top_champ" name="Top_champ" value="24" onclick="ChampCheck(this)"/>
               		제이스<input type="checkbox" id="Top_champ" name="Top_champ" value="126" onclick="ChampCheck(this)"/>
               		초가스<input type="checkbox" id="Top_champ" name="Top_champ" value="31" onclick="ChampCheck(this)"/>
               		카르마<input type="checkbox" id="Top_champ" name="Top_champ" value="43" onclick="ChampCheck(this)"/>
               		카밀<input type="checkbox" id="Top_champ" name="Top_champ" value="164" onclick="ChampCheck(this)"/>
               		케넨<input type="checkbox" id="Top_champ" name="Top_champ" value="85" onclick="ChampCheck(this)"/>
               		케일<input type="checkbox" id="Top_champ" name="Top_champ" value="10" onclick="ChampCheck(this)"/>
               		퀸<input type="checkbox" id="Top_champ" name="Top_champ" value="133" onclick="ChampCheck(this)"/>
               		클레드<input type="checkbox" id="Top_champ" name="Top_champ" value="240" onclick="ChampCheck(this)"/>
               		트린다미어<input type="checkbox" id="Top_champ" name="Top_champ" value="23" onclick="ChampCheck(this)"/>
               		티모<input type="checkbox" id="Top_champ" name="Top_champ" value="17" onclick="ChampCheck(this)"/>
               		판테온<input type="checkbox" id="Top_champ" name="Top_champ" value="80" onclick="ChampCheck(this)"/>
               		피오라<input type="checkbox" id="Top_champ" name="Top_champ" value="114" onclick="ChampCheck(this)"/>
               		하이머딩거<input type="checkbox" id="Top_champ" name="Top_champ" value="74" onclick="ChampCheck(this)"/>
               		헤카림<input type="checkbox" id="Top_champ" name="Top_champ" value="120" onclick="ChampCheck(this)"/>
        </fieldset>
        
        	    <fieldset id="Jungle" style="display:none">
                <legend>Jungle</legend>
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="79" onclick="ChampCheck(this)"/>그라가스
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="104" onclick="ChampCheck(this)"/>그레이브즈
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="56" onclick="ChampCheck(this)"/>녹턴
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="20" onclick="ChampCheck(this)"/>누누와 윌럼프
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="76" onclick="ChampCheck(this)"/>니달리
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="33" onclick="ChampCheck(this)"/>람머스
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="421" onclick="ChampCheck(this)"/>렉사이
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="107" onclick="ChampCheck(this)"/>렝가
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="64" onclick="ChampCheck(this)"/>리신
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="11" onclick="ChampCheck(this)"/>마스터이
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="36" onclick="ChampCheck(this)"/>문도박사
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="254" onclick="ChampCheck(this)"/>바이
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="106" onclick="ChampCheck(this)"/>볼리베어
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="78" onclick="ChampCheck(this)"/>뽀삐
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="517" onclick="ChampCheck(this)"/>사일러스
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="35" onclick="ChampCheck(this)"/>샤코
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="113" onclick="ChampCheck(this)"/>세주아니
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="102" onclick="ChampCheck(this)"/>쉬바나
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="72" onclick="ChampCheck(this)"/>스카너
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="5" onclick="ChampCheck(this)"/>신짜오
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="32" onclick="ChampCheck(this)"/>아무무
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="427" onclick="ChampCheck(this)"/>아이번
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="245" onclick="ChampCheck(this)"/>에코
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="60" onclick="ChampCheck(this)"/>엘리스
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="62" onclick="ChampCheck(this)"/>오공
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="2" onclick="ChampCheck(this)"/>올라프
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="77" onclick="ChampCheck(this)"/>우디르
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="19" onclick="ChampCheck(this)"/>워윅
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="28" onclick="ChampCheck(this)"/>이블린
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="59" onclick="ChampCheck(this)"/>자르반4세
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="154" onclick="ChampCheck(this)"/>자크
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="24" onclick="ChampCheck(this)"/>잭스
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="164" onclick="ChampCheck(this)"/>카밀
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="30" onclick="ChampCheck(this)"/>카서스
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="121" onclick="ChampCheck(this)"/>카직스
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="141" onclick="ChampCheck(this)"/>케인
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="246" onclick="ChampCheck(this)"/>키아나
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="203" onclick="ChampCheck(this)"/>킨드레드
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="163" onclick="ChampCheck(this)"/>탈리야
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="80" onclick="ChampCheck(this)"/>판테온
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="9" onclick="ChampCheck(this)"/>피들스틱
                	<input type="checkbox" id="Jungle_champ" name="Jungle_champ" value="120" onclick="ChampCheck(this)"/>헤카림
                	
        </fieldset>
           <fieldset id="Mid" style="display:none">
                <legend>Mid</legend>
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="86" onclick="ChampCheck(this)"/>가렌
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="3" onclick="ChampCheck(this)"/>갈리오
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="56" onclick="ChampCheck(this)"/>녹턴
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="518" onclick="ChampCheck(this)"/>니코
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="131" onclick="ChampCheck(this)"/>다이애나
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="13" onclick="ChampCheck(this)"/>라이즈
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="99" onclick="ChampCheck(this)"/>럭스
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="68" onclick="ChampCheck(this)"/>럼블
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="58" onclick="ChampCheck(this)"/>레넥톤
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="236" onclick="ChampCheck(this)"/>루시안
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="7" onclick="ChampCheck(this)"/>르블랑
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="92" onclick="ChampCheck(this)"/>리븐
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="127" onclick="ChampCheck(this)"/>리산드라
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="90" onclick="ChampCheck(this)"/>말자하
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="54" onclick="ChampCheck(this)"/>말파이트
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="82" onclick="ChampCheck(this)"/>모데카이저
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="45" onclick="ChampCheck(this)"/>베이가
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="161" onclick="ChampCheck(this)"/>벨코즈
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="8" onclick="ChampCheck(this)"/>블라디미르
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="112" onclick="ChampCheck(this)"/>빅토르
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="517" onclick="ChampCheck(this)"/>사일러스
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="134" onclick="ChampCheck(this)"/>신드라
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="103" onclick="ChampCheck(this)"/>아리
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="136" onclick="ChampCheck(this)"/>아우렐리온 솔
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="268" onclick="ChampCheck(this)"/>아지르
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="84" onclick="ChampCheck(this)"/>아칼리
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="266" onclick="ChampCheck(this)"/>아트록스
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="1" onclick="ChampCheck(this)"/>애니
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="34" onclick="ChampCheck(this)"/>애니비아
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="157" onclick="ChampCheck(this)"/>야스오
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="245" onclick="ChampCheck(this)"/>에코
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="516" onclick="ChampCheck(this)"/>오른
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="61" onclick="ChampCheck(this)"/>오리아나
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="39" onclick="ChampCheck(this)"/>이렐리아
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="81" onclick="ChampCheck(this)"/>이즈리얼
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="238" onclick="ChampCheck(this)"/>제드
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="101" onclick="ChampCheck(this)"/>제라스
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="126" onclick="ChampCheck(this)"/>제이스
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="145" onclick="ChampCheck(this)"/>조이
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="115" onclick="ChampCheck(this)"/>직스
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="26" onclick="ChampCheck(this)"/>질리언
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="31" onclick="ChampCheck(this)"/>초가스
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="164" onclick="ChampCheck(this)"/>카밀
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="38" onclick="ChampCheck(this)"/>카사딘
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="69" onclick="ChampCheck(this)"/>카시오페아
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="55" onclick="ChampCheck(this)"/>카타리나
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="85" onclick="ChampCheck(this)"/>케넨
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="10" onclick="ChampCheck(this)"/>케일
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="42" onclick="ChampCheck(this)"/>코르키
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="133" onclick="ChampCheck(this)"/>퀸
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="240" onclick="ChampCheck(this)"/>클레드
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="246" onclick="ChampCheck(this)"/>키아나
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="91" onclick="ChampCheck(this)"/>탈론
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="163" onclick="ChampCheck(this)"/>탈리야
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="18" onclick="ChampCheck(this)"/>트리스타나
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="4" onclick="ChampCheck(this)"/>트위스티드페이트
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="80" onclick="ChampCheck(this)"/>판테온
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="105" onclick="ChampCheck(this)"/>피즈
                <input type="checkbox" id="Mid_champ" name="Mid_champ" value="74" onclick="ChampCheck(this)"/>하이머딩거
                
           </fieldset>
            <fieldset id="Ad" style="display:none">
            	<legend>Ad</legend>
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="119" onclick="ChampCheck(this)"/>드레이븐
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="236" onclick="ChampCheck(this)"/>루시안
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="21" onclick="ChampCheck(this)"/>미스포츈
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="110" onclick="ChampCheck(this)"/>바루스
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="67" onclick="ChampCheck(this)"/>베인
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="15" onclick="ChampCheck(this)"/>시비르
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="22" onclick="ChampCheck(this)"/>애쉬
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="157" onclick="ChampCheck(this)"/>야스오
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="81" onclick="ChampCheck(this)"/>이즈리얼
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="498" onclick="ChampCheck(this)"/>자야
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="202" onclick="ChampCheck(this)"/>진
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="222" onclick="ChampCheck(this)"/>징크스
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="145" onclick="ChampCheck(this)"/>카이사
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="429" onclick="ChampCheck(this)"/>칼리스타
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="51" onclick="ChampCheck(this)"/>케이틀린
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="96" onclick="ChampCheck(this)"/>코그모
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="18" onclick="ChampCheck(this)"/>트리스타나
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="29" onclick="ChampCheck(this)"/>트위치
            	<input type="checkbox" id="Ad_champ" name="Ad_champ" value="74" onclick="ChampCheck(this)"/>하이머딩거
            </fieldset>
            
            <fieldset id="Sup" style="display:none">
            	<legend>Sup</legend>
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="3" onclick="ChampCheck(this)"/>갈리오
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="79" onclick="ChampCheck(this)"/>그라가스
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="267" onclick="ChampCheck(this)"/>나미
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="111" onclick="ChampCheck(this)"/>노틸러스
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="518" onclick="ChampCheck(this)"/>니코
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="497" onclick="ChampCheck(this)"/>라칸
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="99" onclick="ChampCheck(this)"/>럭스
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="89" onclick="ChampCheck(this)"/>레오나
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="117" onclick="ChampCheck(this)"/>룰루
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="57" onclick="ChampCheck(this)"/>마오카이
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="54" onclick="ChampCheck(this)"/>말파이트
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="25" onclick="ChampCheck(this)"/>모르가나
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="432" onclick="ChampCheck(this)"/>바드
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="45" onclick="ChampCheck(this)"/>베이가
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="161" onclick="ChampCheck(this)"/>벨코즈
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="106" onclick="ChampCheck(this)"/>볼리베어
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="201" onclick="ChampCheck(this)"/>브라움
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="63" onclick="ChampCheck(this)"/>브랜드
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="53" onclick="ChampCheck(this)"/>블리츠크랭크
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="78" onclick="ChampCheck(this)"/>뽀삐
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="35" onclick="ChampCheck(this)"/>샤코
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="235" onclick="ChampCheck(this)"/>세나
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="37" onclick="ChampCheck(this)"/>소나
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="16" onclick="ChampCheck(this)"/>소라카
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="98" onclick="ChampCheck(this)"/>쉔
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="50" onclick="ChampCheck(this)"/>스웨인
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="412" onclick="ChampCheck(this)"/>쓰레쉬
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="12" onclick="ChampCheck(this)"/>알리스타
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="350" onclick="ChampCheck(this)"/>유미
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="143" onclick="ChampCheck(this)"/>자이라
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="40" onclick="ChampCheck(this)"/>잔나
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="101" onclick="ChampCheck(this)"/>제라스
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="26" onclick="ChampCheck(this)"/>질런
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="43" onclick="ChampCheck(this)"/>카르마
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="44" onclick="ChampCheck(this)"/>타릭
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="223" onclick="ChampCheck(this)"/>탐켄치
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="555" onclick="ChampCheck(this)"/>파이크
            	<input type="checkbox" id="Sup_champ" name="Sup_champ" value="9" onclick="ChampCheck(this)"/>피들스틱
            	
            </fieldset>
		
		
			<input type="submit" id="btt" style="display:none" value="Matching" onclick="checking()">
		</form>
	
	
</body>
</html>