<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>${param.title }</title>
	<link href="<c:url value='/resources/dist/fonts/NanumGothic/fonts.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/ct_layout.css' />" rel="stylesheet">
</head>
<body>

<table class="ct_main_tbl">
	<tr>
		<td valign="top">
		
			<!-- 메인 프레임 -->
			<table class="ct_main_field_tbl">
				<tr>
					<td colspan="2" style="text-align:left">
						<img src="<c:url value='/resources/images/sub_logo.png'/>">
					</td>
				</tr>
				<tr>
					<td style="width:15%;">
						<span>상태:운영</span>
					</td>
					<td style="text-align:right">
						<span>${sessionVo.usrgrant } / ${sessionVo.usrname } 님 
						<a href="<c:url value='/member/logout'/>">로그아웃</a></span>
					</td>
				</tr>
				<tr>
					<!-- 상단 메뉴 -->
					<td colspan="2" class="ct_nav_menubar">
						<nav class="ct_nav">
					        <ul>
					            <li><a href="<c:url value='/uni/core/cf3/m1' />">재배</a></li>
					            <li><a href="<c:url value='/uni/core/cf3/acc/m1' />">계정</a></li>
					            <li><a href="#">설명서</a></li>
					        </ul>   
					    </nav>						
					</td>
				</tr>
				<tr>
					<td style="width:30%;height:660px;
									 border-right:1px solid #e2e2e2;
									 vertical-align: top;">
						<nav class="ct_nav_left">
							<ol>
								<li>
									<a href="#">계정</a>
									<ol>
										<!-- Sub Menu -->
										<li>
											사용자 계정
											<ul>
												<li>
													<a href="<c:url value='/uni/core/cf3/acc/m1' />">계정 추가</a>
												</li>
												<li>
													<a href="<c:url value='/uni/core/cf3/acc/m2' />">계정 관리</a>
												</li>
											</ul>
										</li>
									</ol>
								</li>
							</ol>
						</nav>
					</td>
					<!-- 본문 -->
					<td class="ct_td">
						
					