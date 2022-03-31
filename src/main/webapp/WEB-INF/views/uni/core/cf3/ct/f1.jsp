<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<jsp:include page="../include/layout/ct/html_header.jsp">
	<jsp:param name="title" value="재배 / 기능 / 냉난방 개폐 처리" />
</jsp:include>

<link href="<c:url value='/resources/css/ct_framework.css'/>" rel="stylesheet">
<h3>재배 / 기능 / 냉난방 개폐 처리</h3>
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

<!-- 냉난방 / 개폐기능 -->
<table class="ct_cool_heat_tbl">
  <tr>
    <td style="width:15%;">냉방</td>
    <td style="width:5%;text-align:left">
    	<form class="ct_form" action="<c:url value='/uni/core/cf3/f1_ok'/>" method="POST">
		<input type="submit" name="cool_state" class="ct_queries_btn" value="열기" />
		</form>
	</td>
	<td style="text-align:left">
    	<form class="ct_form" action="<c:url value='/uni/core/cf3/f1_ok'/>" method="POST">
		<input type="submit" name="cool_state" class="ct_queries_btn" value="닫기" />
		</form>
	</td>
  </tr>
  <tr>
    <td>난방</td>
    <td style="text-align:left">
    	<form class="ct_form" action="<c:url value='/uni/core/cf3/f1_ok'/>" method="POST">
    	<input type="submit" name="heat_state" class="ct_queries_btn" value="열기" />
    	</form>
    </td>
    <td style="text-align:left">
    	<form class="ct_form" action="<c:url value='/uni/core/cf3/f1_ok'/>" method="POST">
		<input type="submit" name="heat_state" class="ct_queries_btn" value="닫기" />
		</form>
    </td>
  </tr>
</table>

<h5>냉/난방 조회</h5>

<!-- 본문 -->
<table class="ct_detail_list_tbl">
	<tr>
		<th style="width:10%;height:40px;">번호</th>
		<th style="width:25%;">측정일자</th>
		<th style="width:15%;">설치장소</th>
		<th style="width:10%;">냉방</th>
		<th style="width:10%;">난방</th>
		<th>비고</th>
	</tr>
	
	<c:forEach var="row" items="${coolHeatList }">
	<tr>
		<td>${row.id }</td>
	  	<td>
				<fmt:formatDate value="${row.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
		</td>
	  	<td>${row.location }</td>
	  	<td>${row.cool_state }</td>
	  	<td>${row.heat_state }</td>
	  	<td></td>
	</tr>
	</c:forEach>
</table>


<!-- 페이징 영역 -->
<div style="text-align:center">
<jsp:include page="../../../../include/paging.jsp">
	<jsp:param name="customURL" value="${coolHeatPagingUrl}" />
    <jsp:param name="firstPageNo" value="${coolHeatPaging.firstPageNo}" />
    <jsp:param name="prevPageNo" value="${coolHeatPaging.prevPageNo}" />
    <jsp:param name="startPageNo" value="${coolHeatPaging.startPageNo}" />
    <jsp:param name="pageNo" value="${coolHeatPaging.pageNo}" />
    <jsp:param name="endPageNo" value="${coolHeatPaging.endPageNo}" />
    <jsp:param name="nextPageNo" value="${coolHeatPaging.nextPageNo}" />
    <jsp:param name="finalPageNo" value="${coolHeatPaging.finalPageNo}" />
</jsp:include>
</div>

<jsp:include page="../include/layout/ct/html_footer.jsp"></jsp:include>