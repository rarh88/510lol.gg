<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
<head>

<style>

	a:link { color: red; text-decoration: none;}
 	a:visited { color: black; text-decoration: none;}
 	a:hover { color: gold; text-decoration: underline;}


body{
		background-color:#F6F6F6;
        line-height:2em;        
}

    ul, li{ 
        list-style:none;
        text-align:center;
        padding:0;
        margin:0;
}
	
	#boardlist{
        height: 50px;
        width:800px;
        margin: 0 auto; /*가운데 정렬*/
    }
    
    #boardTable{
        text-align: center;
        font-size:14pt;
        height:40px;
        vertical-align:middle;
        line-height:30px;
        background-color:#FAECC5;
        font-weight:bold;
        text-align:center;
	}
    
    #mainWrapper{
        width: 800px;
        margin: 0 auto; /*가운데 정렬*/
    }

    #mainWrapper > ul > li:first-child {
        text-align: center;
        font-size:20pt;
        height:40px;
        vertical-align:middle;
        line-height:30px;
}

    #ulTable {margin-top:10px;}
    

    #ulTable > li:first-child > ul > li {
        background-color:#c9c9c9;
        font-weight:bold;
        text-align:center;
}

    #ulTable > li > ul {
        clear:both;
        padding:0px auto;
        position:relative;
        min-width:40px;
}
    #ulTable > li > ul > li { 
        float:left;
        font-size:10pt;
        border-bottom:1px solid silver;
        vertical-align:baseline;
}    

	#boardTable > li > ul > li          {width:100%;} /*게시판 목록크기*/
	
	
    #ulTable > li > ul > li:first-child               {width:5%;} /*No 열 크기*/
    #ulTable > li > ul > li:first-child +li           {width:15%;} /*게시판이름 크기*/
    #ulTable > li > ul > li:first-child +li+li        {width:35%;} /*제목크기*/
    #ulTable > li > ul > li:first-child +li+li+li     {width:20%;} /*작성자  크기*/
    #ulTable > li > ul > li:first-child +li+li+li+li	{width:15%;} /*등록일 열 크기*/
    #ulTable > li > ul > li:first-child +li+li+li+li+li{width:5%;} /*조회수 열 크기*/
    #ulTable > li > ul > li:first-child +li+li+li+li+li+li{width:5%;} /*조회수 열 크기*/
	
    
    #divPaging {
          clear:both; 
        margin:0 auto; 
        width:220px; 
        height:50px;
}

    #divPaging > div {
        float:left;
        width: 30px;
        margin:0 auto;
        text-align:center;
}

    #liSearchOption {clear:both;}
    #liSearchOption > div {
        margin:0 auto; 
        margin-top: 30px; 
        width:auto; 
        height:100px;

}

    .left {
        text-align : left;
}


</style>

<script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

	function search() {
		var searchtitle = document.getElementById("searchtitle").value
		var searchvalue = document.getElementById("searchvalue").value
		location.href = "search?searchtitle=" + searchtitle
				+ "&searchvalue=" + searchvalue
	}

	function hit2up(a, b) {
		var form = {
			num : a,
			id : b
		}
		$.ajax({
			url : "hit2up",
			type : "GET",
			data : form,
			success : function(data) {
				$('#hit2update' + a).text(data);
			},
			error : function() {
				alert("문제가 발생했습니다.")
			}
		})
	}
</script>

</head>
<body>
<jsp:include page="../css/header2.jsp"/>


	<c:choose>
		<c:when test="${start == null }">
			<c:set var="start" value="1" />
		</c:when>
		<c:otherwise>
			<c:set var="start" value="${start }" />
		</c:otherwise>
	</c:choose>
<c:set var="id" value="${snick }"/>

	<div id="boardlist">
	<ul id ="boardTable">
                    <li>
                        <ul>
                            <li><a href="0g">공지사항</a>　　　
                          	<a href="champ">챔프정보</a>　　　
                            <a href="bug">버그제보</a>　　　
                            <a href="free">자유게시판</a></li>
                        </ul>
                    </li>
                </ul>
	</div>
	<br><br><br>

    <div id="mainWrapper">
    
        <ul>
            <!-- 게시판 제목 -->
            <li><h1>게시판 total </h1></li>

            <!-- 게시판 목록  -->
            <li>
                <ul id ="ulTable">
                    <li>
                        <ul>
                            <li>번호</li>
                            <li>게시판이름</li>
                            <li>제목</li>
                            <li>글쓴이</li>
                            <li>등록일</li>
                            <li>조회</li>
                            <li>추천수</li>
                        </ul>
                    </li>
                    <!-- 게시물이 출력될 영역 -->
                    <c:forEach var="bdto" items="${total}">
                    <li>
                        <ul>
                            <li>${bdto.num}</li>
                            <li>${bdto.boardname}</li>
                            <li class="left"><a href="view?num=${bdto.num}&idgroup=${bdto.idgroup}">${bdto.title}</a></li>
                            <li>${bdto.id}</li>
                            <li>${bdto.savedate}</li>
                            <li>${bdto.hit}</li>
                            <li>
                            <img src="resources/img/hit2up.png"
								onclick="hit2up('${bdto.num}','${sessionScope.id}')"
									style="height: 20px; width: 20px;"> 
							<label id="hit2update${bdto.num}">${bdto.hit2}</label>
							</li>
                        </ul>
                    </li>
					</c:forEach>
                </ul>
            </li>

			<!-- 게시판 페이징 영역 -->
			<li>
				<div id="divPaging">
				
					
				
					<div>
						<c:choose>
							<c:when test="${start > 1}">
								<button type="button"
									onclick="location.href='totalboard?stnum=${start-1 }'">◀
								</button>
							</c:when>
							<c:otherwise>
								<button type="button" disabled="disabled">◀</button>
							</c:otherwise>
						</c:choose>
					</div>

					<div>
						<c:forEach begin="1" end="${totalpage}" step="1" var="cnt">
							<a href="totalboard?stnum=${cnt}">[${cnt}]</a>
						</c:forEach>
					</div>
					<div style="float: right;">
						<input type="button" value="글쓰기"
							onclick="location.href='writeboard?boardname=total'">
					</div>
					
					<div>
						<c:choose>
							<c:when test="${start < totalpage}">
								<button type="button"
									onclick="location.href='totalboard?stnum=${start+1 }'">▶
								</button>
							</c:when>
							<c:otherwise>
								<button type="button" disabled="disabled">▶</button>
							</c:otherwise>

						</c:choose>

					</div>
				</div>
			</li>

			<!-- 검색 폼 영역 -->
            <li id='liSearchOption'>
                <div>
					<select id="searchtitle">
						<option value="자유게시판" selected="selected">자유게시판</option>
						<option value="공지사항">공지사항</option>
						<option value="버그제보">버그제보</option>
					</select> 
					<input type="text" id="searchvalue"> 
					<input type="button" value="검색"	onclick="search()"> 
                </div>
                </li>

        </ul>
    </div>
</body>
</html>
