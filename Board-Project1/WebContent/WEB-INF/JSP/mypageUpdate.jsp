<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp" />

<form action="/Board-Project1/member.do" method="post">
	<table border="1" class="table">
		<caption>
			<h2>마이 페이지 수정?</h2>
		</caption>
		<input type="hidden" name="action" value="doMypageUpdate">
		<input type="hidden" name="ID" value="${m.ID}">
		<tr>
			<th>아이디</th>
			<td>${m.ID}</td>
		</tr>
		<tr>
			<th rowspan="2">비밀번호</th>
			<td><input type="password" name="PW1" value="${m.PW}"></td>
		</tr>
		<tr>
			<td><input type="password" name="PW2" value="${m.PW}">${PWc}</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td><input type="text" name="nickname" value="${m.nickname}">${NNc}
			</td>
		</tr>
		<tr>
			<th>등급</th>
			<td>${m.mng ? "관리자" : "일반회원"}</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="수정">
			</td>
		</tr>
	</table>
</form>
<jsp:include page="/layout/footer.jsp" />