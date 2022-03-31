<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<jsp:include page="../include/layout/acc/html_header.jsp">
	<jsp:param name="title" value="계정 / 사용자 계정 / 계정 관리" />
</jsp:include>

<link href="<c:url value='/resources/css/acc_framework.css'/>" rel="stylesheet">
<script src="<c:url value='/resources/dist/jquery/jquery-3.6.0.min.js'/>"></script>

<h3>계정 / 사용자 계정 / 계정 관리</h3>
<hr>
<!-- 본문 -->
<div>
	<table class="acc_detail_list_tbl">
		<tr>
			<th style="width:10%;height:40px;">번호</th>
			<th style="width:25%;">가입일자</th>
			<th style="width:20%;">이메일</th>
			<th style="width:10%;">이름</th>
			<th style="width:15%;">생년월일</th>
			<th style="width:5%;">권한</th>
			<th style="width:5%;">잠김</th>
			<th>비고</th>
		</tr>
		
		<c:forEach var="row" items="${memberList }">
		<tr>
			<td>${row.id }</td>
		  	<td>
				<fmt:formatDate value="${row.regidate}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
		  	<td>${row.email }</td>
		  	<td>${row.usrname }</td>
		  	<td>
				<fmt:formatDate value="${row.birthdate }" pattern="yyyy-MM-dd" />
		  	</td>
		  	<td>${row.usrgrant }</td>
		  	<td>${row.locked}</td>
		  	<td>
		  		<a href="m3?id=${row.id }&email=${row.email}">수정</a>, 
		  		<a href="javascript:removeMember('${row.id }')">삭제</a>		  	
		  	</td>
		</tr>
		</c:forEach>
	</table>
</div>

<!-- 페이징 영역 -->
<div style="text-align:center">
<jsp:include page="../../../../include/paging.jsp">
	<jsp:param name="customURL" value="${memberPagingUrl}" />
    <jsp:param name="firstPageNo" value="${memberPaging.firstPageNo}" />
    <jsp:param name="prevPageNo" value="${memberPaging.prevPageNo}" />
    <jsp:param name="startPageNo" value="${memberPaging.startPageNo}" />
    <jsp:param name="pageNo" value="${memberPaging.pageNo}" />
    <jsp:param name="endPageNo" value="${memberPaging.endPageNo}" />
    <jsp:param name="nextPageNo" value="${memberPaging.nextPageNo}" />
    <jsp:param name="finalPageNo" value="${memberPaging.finalPageNo}" />
</jsp:include>
</div>

<script>

function removeMember(id){
	
	let confirmAction = confirm(id + "를 정말로 삭제하시겠습니까?");
    
	if (confirmAction) {
		ajaxRemove(id);
		
    	//alert("Action successfully executed");
    } else {
      	//alert("Action canceled");
    }
	
}

function ajaxRemove(id){
	
	let sendData = "id=" + id;
	
	alert('비동기전송 - 시작' + sendData);
	
	$.ajax({
		type:'post',   // post 방식으로 전송
		url:'<c:url value='/uni/core/cf3/acc/m2_remove'/>',   // 데이터를 주고받을 파일 주소
		data:sendData,   // 위의 변수에 담긴 데이터를 전송해준다.
		dataType:'html',   // html 파일 형식으로 값을 담아온다.
		success : function(data){   //파일 주고받기가 성공했을 경우. data 변수 안에 값을 담아온다.
			
			//$('#message').html(data);  //현재 화면 위 id="message" 영역 안에 data안에 담긴 html 코드를 넣어준다.
			//alert(data);
			var result = data.replace(" ", "");
			result = result.trim();
			
			// 성공 처리
			if ( result.indexOf('success') != -1){
				alert('삭제가 완료되었습니다.')
				location.href = '<c:url value='/uni/core/cf3/acc/m2'/>';
			}
			// 오류 전달
			else{
				alert('예기치 못한 문제가 발생하였습니다.');
			}
			
		}, error: function() { 
			//alert("에러 발생");
			var error_msg = "예기치 못한 문제가 발생하였습니다.";
			
			//$('#chResult').append( ret_item );
			alert(error_msg);
			
		}
	});
	
}

</script>

<jsp:include page="../include/layout/acc/html_footer.jsp"></jsp:include>