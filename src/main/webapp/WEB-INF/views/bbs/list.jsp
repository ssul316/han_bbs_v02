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
	<button type="button"><a href="cboard">글쓰기</a></button>
	<c:forEach items="${list}" var="vo">
		<li>${vo}</li>
	</c:forEach>
	<br>

	<c:if test="${pageMaker.hasPrev}">
		<a href="javascript:_goPage(${pageMaker.first}-1)">[이전]</a>&nbsp;
	</c:if>
	
	<c:forEach begin="${pageMaker.first }" end="${pageMaker.last }" var="idx">
	 	<a href="javascript:_goPage(${idx})">[${idx}]</a>
	</c:forEach>
	
	<%-- <c:if test="${${pageMaker.perPage}*${pageMaker.last}+1 == ${pageMaker.getRowNum()}"> --%>
	
	<c:if test="${pageMaker.hasNext}">
		&nbsp;<a href="javascript:_goPage(${pageMaker.last}+1)">[다음]</a>
	</c:if>

<!-- 	<form method='get' name='pageForm'>
		<input type='hidden' name='page'>
	</form> -->
	
	<form name='searchForm'>
	  <input type='hidden' name='bno'>
	  <input type='hidden' name='page' value='${cri.page }'>
	  <input type='text' name='keyword' value='${cri.keyword }'>
	  <input type='checkbox' name="types" value="t" ${cri.checked("t") }>제목
	  <input type='checkbox' name="types" value="w" ${cri.checked("w") }>작성자
	  <input type='checkbox' name="types" value="c" ${cri.checked("c") }>본문
	  <button onclick="javascript:goSearch();">Search</button>
	</form>
	<br>
	${pageMaker}
	
	
	<script>
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