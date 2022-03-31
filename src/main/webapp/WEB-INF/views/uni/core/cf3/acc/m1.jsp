<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<jsp:include page="../include/layout/acc/html_header.jsp">
	<jsp:param name="title" value="계정 / 사용자 계정 / 계정 추가" />
</jsp:include>

<link href="<c:url value='/resources/css/acc_framework.css'/>" rel="stylesheet">
<script src="<c:url value='/resources/dist/jquery/jquery-3.6.0.min.js'/>"></script>

<h3>계정 / 사용자 계정 / 계정 추가</h3>

<!-- 본문 -->
<form action="<c:url value='/uni/core/cf3/acc/m1_ok'/>" method="POST">
<table class="acc_member_add_tbl">
	<tr>
		<td style="width:20%">아이디</td>
	  	<td>
			<input class="acc_input_box" type="text" name="email" style="width:65%">
			<a class="acc_input_chk_Btn" href="#" id="acc_email_chk_btn">중복확인</a>
		</td>
	</tr>
</table>
<div id="chkEmailResult"></div>
<table class="acc_member_add_tbl">
	<tr>
		<td style="width:20%">비밀번호</td>
	  	<td>
			<input class="acc_input_box" type="password" name="passwd1" style="width:80%">
		</td>
	</tr>
	<tr>
		<td style="width:20%">비밀번호 확인</td>
	  	<td>
			<input class="acc_input_box" type="password" name="passwd2" style="width:80%">
		</td>
	</tr>
	<tr>
		<td style="width:20%">이름</td>
	  	<td>
			<input class="acc_input_box" type="text" name="usrname" style="width:80%">
		</td>
	</tr>
	<tr>
		<td style="width:20%">생년월일</td>
	  	<td>
			<input class="acc_input_box" type="date" name="usrdate" style="width:80%">
		</td>
	</tr>
	<tr>
		<td style="width:20%">권한</td>
	  	<td>
	  		<select name="usrgrant" class="acc_input_box">
			    <option value="일반">일반</option>
			    <option value="관리자">관리자</option>
			</select>
		</td>
	</tr>
	<tr>
	  	<td colspan="2">
	  		<div id="chResult"></div>
		</td>
	</tr>
</table>
<input id="acc_submit_btn" type="button" class="acc_input_add_btn"  value="추가">
</form>


<script>
$(document).ready(function(){
	
	$('#acc_email_chk_btn').click(function(){   //submit 버튼을 클릭하였을 때
	
		let sendData = "email=" + $('input[name=email]').val();   //폼의 이름 값을 변수 안에 담아줌
		sendData += "&token=abcd";
		//alert('비동기전송 - 시작' + sendData);
		
		$.ajax({
			type:'post',   // post 방식으로 전송
			url:'<c:url value='/member/emailCheck'/>',   // 데이터를 주고받을 파일 주소
			data:sendData,   // 위의 변수에 담긴 데이터를 전송해준다.
			dataType:'html',   // html 파일 형식으로 값을 담아온다.
			success : function(data){   //파일 주고받기가 성공했을 경우. data 변수 안에 값을 담아온다.
				
				//$('#message').html(data);  //현재 화면 위 id="message" 영역 안에 data안에 담긴 html 코드를 넣어준다.
				//alert(data);
				var result = data;
				alert(result);
				if ( result.indexOf("true") != -1 ){
					$('#chkEmailResult').html('가입 허용');
				}else{
					$('#chkEmailResult').html('가입 불가');
				}
				
			}, error: function() { 
				//alert("에러 발생");
				var error_msg = "예기치 못한 문제가 발생하였습니다.";
				var ret_item = error_msg;
				
				$('#chResult').append( ret_item );
				
			}
		});
		
	});
	
	$('#acc_submit_btn').click(function(){   //submit 버튼을 클릭하였을 때
		
		let sendData = "email=" + $('input[name=email]').val();   //폼의 이름 값을 변수 안에 담아줌
		sendData += "&passwd1=" + $('input[name=passwd1]').val();
		sendData += "&passwd2=" + $('input[name=passwd2]').val();
		sendData += "&usrname=" + $('input[name=usrname]').val();
		sendData += "&usrdate=" + $('input[name=usrdate]').val();
		sendData += "&usrgrant=" + $('select[name=usrgrant]').val();
		sendData += "&token=bcde";
		
		alert('비동기전송 - 시작' + sendData);
		
		$.ajax({
			type:'post',   // post 방식으로 전송
			url:'<c:url value='/uni/core/cf3/acc/m1_ok'/>',   // 데이터를 주고받을 파일 주소
			data:sendData,   // 위의 변수에 담긴 데이터를 전송해준다.
			dataType:'html',   // html 파일 형식으로 값을 담아온다.
			success : function(data){   //파일 주고받기가 성공했을 경우. data 변수 안에 값을 담아온다.
				
				//$('#message').html(data);  //현재 화면 위 id="message" 영역 안에 data안에 담긴 html 코드를 넣어준다.
				//alert(data);
				var result = data.replace(" ", "");
				result = result.trim();
				
				// 성공 처리
				if ( result.indexOf('success') != -1){
					$('#chResult').html('');	
					alert(result);
				}
				// 오류 전달
				else{
					$('#chResult').html(result);	
				}
				
			}, error: function() { 
				//alert("에러 발생");
				var error_msg = "예기치 못한 문제가 발생하였습니다.";
				var ret_item = error_msg;
				
				//$('#chResult').append( ret_item );
				$('#chResult').html(error_msg);
				
			}
		});
		
	});
	
});

</script>

<jsp:include page="../include/layout/acc/html_footer.jsp"></jsp:include>