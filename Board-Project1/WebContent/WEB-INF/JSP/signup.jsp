<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp"%>

<form action="/Board-Project1/member.do" method="post">
	<table border="1" class="table">
		<caption>
			<h3>회원가입</h3>
		</caption>
		<input type="hidden" name="action" value="doSignup">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="ID" placeholder="아이디를 입력하세요."
				value="${ID}" required>${IDc}</td>
		</tr>
		<tr>
			<th rowspan="2">비밀번호</th>
			<td><input type="password" name="PW" placeholder="비밀번호를 입력하세요."
				required></td>
		</tr>
		<tr>
			<td><input type="password" name="PW2"
				placeholder="비밀번호를 재입력하세요." required>${PWc}</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td><input type="text" name="nickname" placeholder="닉네임을 입력하세요."
				value="${nickname}" required>${NNc}</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="회원가입">
			</td>
		</tr>
	</table>
</form>

<%@ include file="/layout/footer.jsp"%>