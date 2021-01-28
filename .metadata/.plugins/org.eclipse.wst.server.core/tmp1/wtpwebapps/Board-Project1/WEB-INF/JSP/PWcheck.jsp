<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp" />

<form action="/Board-Project1/member.do" method="post">
	<table border="1" class="table">
		<caption>
			<h3>비밀번호 확인</h3>
		</caption>
		<tr>
			<th>비밀번호</th>
			<td><input type="hidden" name="action" value="mypageUpdate">
				<input type="hidden" name="page" value="${page}"> <input
				type="password" name="PWcheck" placeholder="비밀번호를 입력하세요."> <input
				type="hidden" name="PW" value="${m.PW}"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="확인">
				<input type="button" value="취소" onclick="history.back()"></td>
		</tr>
	</table>
	${msg}
</form>
<jsp:include page="/layout/footer.jsp" />