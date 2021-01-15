<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자세히보기</title>
</head>
<body>
<h1>${article.id}번 게시물</h1>

제목 : ${article.title}<br>
작성자 : ${article.nickname}<br>
작성날짜 : ${article.date}<br>
좋아요 : ${article.like}<br>
조회수 : ${article.hit}<br>
내용 : ${article.body}<br>

<a href="/web-exam1/Con?action=update&articleid=${article.id}">수정하기</a>
<a href="/web-exam1/Con?action=delete&articleid=${article.id}">삭제하기</a>
<a href="/web-exam1/Con?action=list">목록으로</a>

</body>
</html>