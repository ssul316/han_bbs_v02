<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Step 1</title>
</head>
<body>
	<h1>Step 1</h1>
	
	<!-- 글쓰기 버튼 -->
	<button type="button"><a href="cboard">글쓰기</a></button>
	
	<!-- 게시글 목록 -->
	<c:forEach items="${list}" var="vo">
		<li>[글번호:${vo.bno}] <a href="read?bno=${vo.bno}">[제목:${vo.title}]</a> [작성자:${vo.userid}] [게시일:${vo.regdate}] [조회수:${vo.vcount}]</li>
	</c:forEach>
	<br>
	
	<!-- 이전버튼 생성조건식 -->
	<c:if test="${pageMaker.hasPrev}">
		<a href="javascript:_goPage(${pageMaker.first}-1)">[이전]</a>&nbsp;
	</c:if>
	
	<!-- 게시물 생성 -->
	<c:if test="${pageMaker.cnt == 0 }">
		<p>검색된 게시물이 없습니다.</p>
		<a href="list">[메인페이지로]</a>
	</c:if>
	<c:forEach begin="${pageMaker.first }" end="${pageMaker.last }" var="idx">
	 	<a href="javascript:_goPage(${idx})">[${idx}]</a>
	</c:forEach>
	
	<%-- <c:if test="${${pageMaker.perPage}*${pageMaker.last}+1 == ${pageMaker.getRowNum()}"> --%>
	
	<!-- 다음버튼 생성조건식 -->
	<c:if test="${pageMaker.hasNext}">
		&nbsp;<a href="javascript:_goPage(${pageMaker.last}+1)">[다음]</a>
	</c:if>

<!-- 	<form method='get' name='pageForm'>
		<input type='hidden' name='page'>
	</form> -->
	
	<!-- 페이징 및 검색조건 전달 -->
	<form name='searchForm'>
	  <input type='hidden' name='bno'>
	  <input type='hidden' name='page' value='${pageMaker.page }'>
	  <input type='text' name='keyword' value='${pageMaker.keyword }'>
	  <input type='checkbox' name="types" value="t" ${pageMaker.checked("t") }>제목
	  <input type='checkbox' name="types" value="w" ${pageMaker.checked("w") }>작성자
	  <input type='checkbox' name="types" value="c" ${pageMaker.checked("c") }>본문
	  <button onclick="javascript:_goPage(1);">Search</button>
	</form>
	<!-- ${pageMaker.checked("t") }부분은 페이지가 넘어가더라도 검색구분 체크를 계속 물고있어야하기 때문에 체크박스 체크 부분을 계속 표시해두는것 -->
	
	<br>
	${pageMaker}
	
	<script>
		/* 검색조건 전달문 */
		function _goPage(num){
			document.searchForm.page.value = num;
			document.searchForm.submit();
		}
		/* function _goNextLine(num){
			document.pageForm.page.value = num;
			document.pageForm.submit();
		} */
	</script>
</body>
</html>