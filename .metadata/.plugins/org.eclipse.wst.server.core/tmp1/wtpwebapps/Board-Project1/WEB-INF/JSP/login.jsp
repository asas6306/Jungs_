<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp"%>


<script>aaaa</script>
<form action="/Board-Project1/member.do" method="post">
	<table border="1" class="table">
		<input type="hidden" name="action" value="doLogin" method="post">
		<caption>
			<h3>로그인 페이지</h3>
		</caption>
		<tr>
			<th>아이디</th>
			<td><input type="text" name="ID" placeholder="아이디" value="${ID}"
				required><br></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="PW" placeholder="비밀번호" required><br>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="로그인">
				<input type="button" value="ID/PW찾기"
				onclick="location.href='/Board-Project1/member.do?action=find'">
				<input type="button" value="회원가입"
				onclick="location.href='/Board-Project1/member.do?action=signup'">
			</td>
		</tr>
	</table>
</form>
<div align="center">
	<h5>${failMsg}</h5>
</div>


<%@ include file="/layout/footer.jsp"%>