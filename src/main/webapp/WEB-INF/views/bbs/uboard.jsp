<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정</title>
</head>
<body>

<!-- 글 수정화면 (작업중)-->
<form action="update" accept-charset="utf-8" id="ins" method="post">
    <h1>게시글을 수정해주세요</h1>
    <br>
    <p>글 제목<textarea name="title" rows="1" cols="50">${vo.title }</textarea></p>
    <p>내용<textarea name="cont" rows="3" cols="50">${vo.cont }</textarea></p>
    <input type="hidden" name="bno" value=${vo.bno }>
    <input type="submit">
    
</form>
</body>
</html>