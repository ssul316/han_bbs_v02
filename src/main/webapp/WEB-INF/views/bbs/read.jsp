<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${vo.title }</title>
</head>
<body>
<h2><strong>${vo.title }</strong></h2>
작성자:${vo.userid }
작성일:${vo.regdate }
<br>
<br>
${vo.cont }

<br>
<button><a href="uboard?bno=${vo.bno }">글수정</a></button>
<button><a href="delete?bno=${vo.bno }">글삭제</a></button>
</body>
</html>