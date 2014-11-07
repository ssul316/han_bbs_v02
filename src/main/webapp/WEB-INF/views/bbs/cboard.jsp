<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 등록</title>
</head>
<body>

<!-- 글 등록화면 -->
<form action="create" accept-charset="utf-8" id="ins" method="post">
    <h1>게시글을 등록해주세요</h1>
    <br>
    <p>글 제목<input type="text" name="title"></p>
    <p>글쓴이<input type="text" name="userid"></p>
    <p>내용<textarea name="cont" rows="3" cols="50"></textarea></p>
    <input type="submit">
</form>
</body>
</html>