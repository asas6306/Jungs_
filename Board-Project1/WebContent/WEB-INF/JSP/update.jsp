<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp"%>

<form action="/Board-Project1/article.do">
	<table border="1" class="table">
		<input type="hidden" name="action" value="doUpdate">
		<caption>
			<h4>${aid}번게시물 수정</h4>
		</caption>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" required></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="22" name="body" required></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="hidden"
				name="articleid" value="${aid}"> <input type="submit"
				value="수정하기"> <input type="button" value="뒤로가기"
				onclick="history.back()"></td>
		</tr>
	</table>
</form>

<%@ include file="/layout/footer.jsp"%>