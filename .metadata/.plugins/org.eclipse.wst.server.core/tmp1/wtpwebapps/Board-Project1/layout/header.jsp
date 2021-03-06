<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jung's Board Project_01</title>
<link rel="stylesheet" type="text/css"
	href="/Board-Project1/layout/layout.css">
</head>
<body>
	<div align="center">
		<div style="width: 82%" align="right">
			<c:choose>
				<c:when test="${m.state}">
					<a>${m.nickname}님 환영합니다!</a>&nbsp
					<a href="/Board-Project1/member.do?action=doLogout">로그아웃</a>&nbsp
					<a href="/Board-Project1/member.do?action=mypage">마이페이지</a>&nbsp
					<c:choose>
						<c:when test="${m.state}">
							<a href="/Board-Project1/management.do">관리페이지</a>&nbsp
						</c:when>
					</c:choose>
				</c:when>
				<c:when test="${!m.state}">
					<a href="/Board-Project1/member.do?action=login">로그인</a>&nbsp
					<a href="/Board-Project1/member.do?action=signup">회원가입</a>&nbsp
				</c:when>
			</c:choose>
		</div>
		<div class="header" align="center">
			<a href="/Board-Project1/main.do">MAIN</a> | <a
				href="/Board-Project1/main.do?action=introduce">INTRODUCE</a> | <a
				href="/Board-Project1/article.do?action=list">BOARD</a> | DOWNLOADS
		</div>
		<div class="main" align="center">
			<!-- haeder -->