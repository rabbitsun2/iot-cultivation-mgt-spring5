<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<jsp:include page="../include/layout/ct/html_header.jsp">
	<jsp:param name="title" value="재배 / 인공지능 / 챗봇" />
</jsp:include>

<link href="<c:url value='/resources/css/ct_framework.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/ct_chatbot.css'/>" rel="stylesheet">
<script src="<c:url value='/resources/dist/jquery/jquery-3.6.0.min.js'/>"></script>
<h3>재배 / 인공지능 / 챗봇</h3>

<!-- 본문 -->
<div id="chResult"></div>

<!-- 입력 기능 -->
<table class="ct_chatbox_tbl">
	<tr>
		<td style="width:95%">
			<input type="text" name="sendMessage" value="" style="width:100%;">
		</td>
		<td style="text-align:left;">
			<input id="ct_send_btn" type="submit" class="ct_queries_btn" value="전송">
		</td>
	</tr>
</table>

<script>
$(document).ready(function(){
	
	$('#ct_send_btn').click(function(){   //submit 버튼을 클릭하였을 때
	
		let sendData = "sendMessage=" + $('input[name=sendMessage]').val();   //폼의 이름 값을 변수 안에 담아줌
		//alert('비동기전송 - 시작' + sendData);
		
		$.ajax({
			type:'post',   // post 방식으로 전송
			url:'<c:url value='/uni/core/cf3/a1_ok'/>',   // 데이터를 주고받을 파일 주소
			data:sendData,   // 위의 변수에 담긴 데이터를 전송해준다.
			dataType:'html',   // html 파일 형식으로 값을 담아온다.
			success : function(data){   //파일 주고받기가 성공했을 경우. data 변수 안에 값을 담아온다.
				
				//$('#message').html(data);  //현재 화면 위 id="message" 영역 안에 data안에 담긴 html 코드를 넣어준다.
				
				addMessage( $('input[name=sendMessage]').val(), data);
				
			}, error: function() { 
				//alert("에러 발생");
				var error_msg = "예기치 못한 문제가 발생하였습니다.";
				var ret_item = getReplyMessage(error_msg);
				
				$('#chResult').append( ret_item );
				
			}
		});
		
	});
	
	var hello = "채소 재배 관리시스템 인공지능 챗봇입니다.";
	hello += "<br>채팅창으로 원격 시스템을 사용할 수 있습니다.";
	// 인공지능 챗봇 - 초기상태
	$('#chResult').append( initFrame(hello) );
	
});

function initFrame(message){

	var items = "<table class='ct_chatbox_tbl'>";
		items += "<tr>";
		items += "<td style='width:15%'>챗봇</td>";
		items += "<td style='text-align:left;'>";
		items += "<a href='#' class='ct_reply_bot'>" + message + "</a>";
		items += "</td>";
		items += "</tr>";
		items += "</table>";
		
		return items;
		
}

function addMessage(usrMessage, chboxMessage){
	
	var items = getFieldMessage(usrMessage, chboxMessage);
	$('#chResult').append( items );
}

function getFieldMessage(usrMessage, chboxMessage){
	
	var items = "<table class='ct_chatbox_tbl'>";
		items += "	<tr>";
		items += "		<td style='width:15%'>사용자</td>";
		items += "		<td style='text-align:right;'>";
		items += "	<a href='#' class='ct_sender'>" + usrMessage + "</a>";
		items += "</td>";
		items += "</tr>";
		items += "<tr>";
		items += "<td>챗봇</td>";
		items += "<td style='text-align:left;'>";
		items += "<a href='#' class='ct_reply_bot'>" + chboxMessage + "</a>";
		items += "</td>";
		items += "</tr>";
		items += "</table>";
		
		//alert(items);
		
		return items;
	
}

function getReplyMessage(message){
	
	var items = "<table class='ct_chatbox_tbl'>";
		items += "<tr>";
		items += "<td style='width:15%'>챗봇</td>";
		items += "<td style='text-align:right;'>";
		items += "<a href='#' class='ct_reply_bot'>" + message + "</a>";
		items += "</td>";
		items += "</tr>";
		items += "</table>";
		
		//alert(items);
		
		return items;
	
}

</script>

<jsp:include page="../include/layout/ct/html_footer.jsp"></jsp:include>