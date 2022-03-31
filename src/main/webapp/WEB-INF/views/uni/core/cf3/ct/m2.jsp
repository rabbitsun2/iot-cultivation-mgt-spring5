<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<jsp:include page="../include/layout/ct/html_header.jsp">
	<jsp:param name="title" value="재배 / 모니터링 / 주변 환경 상태 조회" />
</jsp:include>

<link href="<c:url value='/resources/css/ct_framework.css'/>" rel="stylesheet">
<h3>재배 / 모니터링 / 주변 환경 상태 조회</h3>
<form action="">
<!-- 상세 조회 -->
<table class="ct_detail_cause_tbl">
	<tr>
		<td>
			<span>기간:</span>
		</td>
		<td>
			<input type="date" name="ct_startdate"> ~ 
			<input type="date" name="ct_enddate">
			<input type="submit" class="ct_queries_btn" value="조회" />
		</td>
	</tr>
</table>
</form>

<!-- 본문 -->
<table class="ct_detail_list_tbl">
	<tr>
		<th style="width:10%;height:40px;">번호</th>
		<th style="width:25%;">측정 일자</th>
		<th style="width:15%;">설치 장소</th>
		<th style="width:10%;">온도</th>
		<th style="width:10%;">습도</th>
		<th>비고</th>
	</tr>
	
	<c:forEach var="row" items="${dhtList }">
	<tr>
		<td>${row.id }</td>
	  	<td>
				<fmt:formatDate value="${row.regidate}" pattern="yyyy-MM-dd HH:mm:ss" />
		</td>
	  	<td>${row.location }</td>
	  	<td>${row.temperature }</td>
	  	<td>${row.humidity }</td>
	  	<td></td>
	</tr>
	</c:forEach>
</table>


<!-- 페이징 영역 -->
<div style="text-align:center">
<jsp:include page="../../../../include/paging.jsp">
	<jsp:param name="customURL" value="${dhtPagingUrl}" />
    <jsp:param name="firstPageNo" value="${dhtPaging.firstPageNo}" />
    <jsp:param name="prevPageNo" value="${dhtPaging.prevPageNo}" />
    <jsp:param name="startPageNo" value="${dhtPaging.startPageNo}" />
    <jsp:param name="pageNo" value="${dhtPaging.pageNo}" />
    <jsp:param name="endPageNo" value="${dhtPaging.endPageNo}" />
    <jsp:param name="nextPageNo" value="${dhtPaging.nextPageNo}" />
    <jsp:param name="finalPageNo" value="${dhtPaging.finalPageNo}" />
</jsp:include>
</div>


<jsp:include page="../include/layout/ct/html_footer.jsp"></jsp:include>