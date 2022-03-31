<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<jsp:include page="../include/layout/ct/html_header.jsp">
	<jsp:param name="title" value="재배 / 모니터링 / 일일 평균 환경 그래프 출력" />
</jsp:include>

<link href="<c:url value='/resources/css/ct_framework.css'/>" rel="stylesheet">

<!-- Load d3.css -->
<link href="<c:url value='/resources/dist/c3-0.7.20/c3.css'/>" rel="stylesheet">

<!-- Load d3.js and c3.js -->
<script src="<c:url value='/resources/dist/c3-0.7.20/docs/js/d3-5.8.2.min.js'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/dist/c3-0.7.20/c3.min.js'/>"></script>
<h3>재배 / 모니터링 / 일일 평균 환경 그래프 출력</h3>
<form action="">
<!-- 상세 조회 -->
<table class="ct_detail_cause_tbl">
	<tr>
		<td>
			<span>기간:</span>
		</td>
		<td>
			<input type="date" name="startdate">
			<input type="submit" class="ct_queries_btn" value="조회" />
		</td>
	</tr>
</table>
<div id="chart"></div>

</form>

<!-- 본문 -->
<script>
	var chart = c3.generate({
	    bindto: '#chart',
	    data: {
	      x: '시간',
	      columns: [
	    	  ['시간',
	    		 <c:forEach var="row" items="${todayDHT}">
	    				${row.hh},
	    		 </c:forEach>
	    	  ],
	          ['온도',
	        	  <c:forEach var="row" items="${todayDHT}">
				       ${row.avg_temperature},
		 		  </c:forEach>
	          ],
	          ['습도',
	        	  <c:forEach var="row" items="${todayDHT}">
				       ${row.avg_humidity},
		 		  </c:forEach>
	          ]
	      ]
	    }
	});

</script>

<jsp:include page="../include/layout/ct/html_footer.jsp"></jsp:include>