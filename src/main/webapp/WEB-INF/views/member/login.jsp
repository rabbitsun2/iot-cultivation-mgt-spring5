<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<link href="<c:url value='/resources/dist/fonts/NanumGothic/fonts.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/member_layout.css' />" rel="stylesheet">
</head>
<body>

<form action="<c:url value='/member/loginCheck' />" method="post">
<input type="hidden" name="csrf_token" value="abcd"> 
<!-- 메인 -->
<table class="member_login_tbl">
	<tr>
		<td valign="top">
			<div style="text-align:center">
				<img src="<c:url value='/resources/images/logo.png'/>">
			</div>
			<table class="member_login_field_tbl">
				<tr>
					<td>
						<span class="member_login_lbl">아이디</span>
					</td>
					<td>
						<input type="text" name="email">
					</td>
				</tr>
				
				<tr>
					<td>
						<span class="member_login_lbl">비밀번호</span>
					</td>
					<td>
						<input type="password" name="passwd">
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input class="member_login_btn" type="submit" value="로그인">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</form>

</body>
</html>
