<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 추가</title>
</head>
<body>

<h1>게시물 작성</h1>
<a href="/web-exam1/Con?action=doInsert&title=aaaa&body=bbbb"></a>
<form action="/web-exam1/Con">
	제목 : <input type="text" name="title"><br>
	내용 : <input type="text" name="body"><br>
	<input type="hidden" name="action" value="doInsert"><br>
	<input type="submit">
</form>

</body>
</html>