package com.iot.mgt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.iot.mgt.service.MemberService;
import com.iot.mgt.vo.MemberVO;

public class MemberChecker {

	public final static int MEMBER_JOIN = 1;
	public final static int MEMBER_MODIFY = 2;
	
	private static MemberChecker instance;
	private MemberService memberService;
	private MemberVO vo;
	private String passwd1;
	private String passwd2;
	private String usrdate;
	private String token;
	
	private MemberChecker() {}
	
	public static MemberChecker getInstance() {
		
		if ( instance == null ) {
			instance = new MemberChecker();
		}
		
		return instance;
		
	}
	
	public MemberService getMemberService() {
		return this.memberService;
	}
	
	public void setMemberService(MemberService service) {
		this.memberService = service;
	}
	
	public MemberVO getMemberVo() {
		return this.vo;
	}
	
	public void setMemberVo(MemberVO vo) {
		this.vo = vo;
	}
	
	public String getPasswd1() {
		return this.passwd1;
	}
	
	public String getPasswd2() {
		return this.passwd2;
	}
	
	public void setPasswd(String passwd1, String passwd2) {
		this.passwd1 = passwd1;
		this.passwd2 = passwd2;
	}
	
	public String getUsrdate() {
		return this.usrdate;
	}
	
	public void setUsrdate(String usrdate) {
		this.usrdate = usrdate;
	}
	
	public String getToken() {
		return this.token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public int runChecker(int code) {
		
		int result = -1;
		
		switch(code) {
		
			case MEMBER_JOIN:
				result = memberJoin();
				break;
			
			case MEMBER_MODIFY:
				result = memberModify();
				break;
		
			default:
				
				break;
				
		}
		
		return result;
		
	}
	
	public String getMessage(int result, int code) {
		
		String msg = "";
		
		switch(code) {
		
			case MEMBER_JOIN:
				msg = getJoinMessage(result);
				break;
				
			case MEMBER_MODIFY:
				msg = getModifyMessage(result);
				break;
		
			default:
				
				break;
				
		} // end of switch
		
		return msg;
		
	}
	
	private int memberJoin() {

		MemberVO vo = this.getMemberVo();
		String passwd1 = this.getPasswd1();
		String passwd2 = this.getPasswd2();
		
		String usrdate = this.getUsrdate();
		String token = this.getToken();
		
		int result = 1;
		
		// 이메일 계정 길이 확인
		if ( vo.getEmail().length() >= 8	) {
			result = 1;
		}else {
			result = 2;
		}
		
		// 이메일 정규식 확인
        String regx = "^(.+)@(.+)$";
        //Compile regular expression to get the pattern  
        Pattern emailPattern = Pattern.compile(regx);
        Matcher matcher = emailPattern.matcher(vo.getEmail());
        
        // 이메일 정규식 일치 확인
        if (result == 1 && 
        		matcher.matches() == true) {
        	result = 1;
        }else if (result == 1 && 
        		matcher.matches() == false) {
        	result = 3;
        }
		
        // 이메일 중복 여부
        if (result == 1 &&
        		memberService.selectMember(vo) != null ) {
    	
    		result = 4;
    	}else if (result == 1 &&
        		memberService.selectMember(vo) == null ) {
    		result = 1;
    	}
        	    
		// 비밀번호 일치 확인
		if ( result == 1 && 
				passwd1.equals(passwd2) ){
			
			// 비밀번호 길이 확인
			if ( passwd1.length() >= 4) {
				result = 1;
				vo.setPasswd(passwd1);
			}else {
				result = 6;
			}
			
		}else if ( result == 1 && 
				passwd1.equals(passwd2) != true ) {
			result = 5;
		}
		
		// 이름 존재 확인
		if ( result == 1 &&
				vo.getUsrname() != null) {
			
			// 이름 길이 확인
			if ( vo.getUsrname().length() >= 3) {
				result = 1;
				//System.out.println("이름 길이:" + vo.getUsrname().length());
			}else {
				result = 8;
			}
			
		}else if (result == 1 &&
				vo.getUsrname() == null) {
			result = 7;
		}
		
		// 생년월일 입력 여부 확인
		if ( result == 1 &&
				usrdate.length() != 0 ) {
			result = 1;
			
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date to = transFormat.parse(usrdate);
				vo.setBirthdate(to);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}else if (result == 1 &&
				usrdate.length() == 0 ){
			result = 9;
		}
		
		// 권한 입력 확인
		if ( result == 1 &&
				vo.getUsrgrant().length() != 0) {
			result = 1;
		}else if (result == 1 &&
				vo.getUsrgrant().length() == 0) {
			result = 10;
		}
		
		// 토큰 입력 확인
		if ( result == 1 && 
				token.equals("bcde") ) {
			result = 1;
		}else if( result == 1 && 
				token.equals("bcde") != true ) {
			result = 11;
		}
		
		return result;
		
	}
	

	private int memberModify() {

		MemberVO vo = this.getMemberVo();
		String passwd1 = this.getPasswd1();
		String passwd2 = this.getPasswd2();
		
		String usrdate = this.getUsrdate();
		String token = this.getToken();
			
		int result = 1;
		
		// 이메일 계정 길이 확인
		if ( vo.getEmail().length() >= 8	) {
			result = 1;
		}else {
			result = 2;
		}
		
		// 이메일 정규식 확인
        String regx = "^(.+)@(.+)$";
        //Compile regular expression to get the pattern  
        Pattern emailPattern = Pattern.compile(regx);
        Matcher matcher = emailPattern.matcher(vo.getEmail());
        
        // 이메일 정규식 일치 확인
        if (result == 1 && 
        		matcher.matches() == true) {
        	result = 1;
        }else if (result == 1 && 
        		matcher.matches() == false) {
        	result = 3;
        }
		
        // 이메일 중복 여부
        if (result == 1 &&
        		memberService.selectMember(vo) != null ) {
    	
    		result = 1;
    	}else if (result == 1 &&
        		memberService.selectMember(vo) == null ) {
    		result = 4;
    	}
        	    
		// 비밀번호 일치 확인
		if ( result == 1 && 
				passwd1.equals(passwd2) ){
			
			// 비밀번호 길이 확인
			if ( passwd1.length() >= 4) {
				result = 1;
				vo.setPasswd(passwd1);
			}else {
				result = 6;
			}
			
		}else if ( result == 1 && 
				passwd1.equals(passwd2) != true ) {
			result = 5;
		}
		
		// 이름 존재 확인
		if ( result == 1 &&
				vo.getUsrname() != null) {
			
			// 이름 길이 확인
			if ( vo.getUsrname().length() >= 3) {
				result = 1;
				//System.out.println("이름 길이:" + vo.getUsrname().length());
			}else {
				result = 8;
			}
			
		}else if (result == 1 &&
				vo.getUsrname() == null) {
			result = 7;
		}
		
		// 생년월일 입력 여부 확인
		if ( result == 1 &&
				usrdate.length() != 0 ) {
			result = 1;
			
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date to = transFormat.parse(usrdate);
				vo.setBirthdate(to);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}else if (result == 1 &&
				usrdate.length() == 0 ){
			result = 9;
		}
		
		// 권한 입력 확인
		if ( result == 1 &&
				vo.getUsrgrant().length() != 0) {
			result = 1;
		}else if (result == 1 &&
				vo.getUsrgrant().length() == 0) {
			result = 10;
		}
		
		// 권한 입력 확인
		if ( result == 1 &&
				(vo.getLocked() == 0 || vo.getLocked() == 1) ) {
			result = 1;
		}else if (result == 1 &&
				(vo.getLocked() != 0 || vo.getLocked() != 1) ) {
			result = 11;
		}
		
		// 토큰 입력 확인
		if ( result == 1 && 
				token.equals("cdef") ) {
			result = 1;
		}else if( result == 1 && 
				token.equals("cdef") != true ) {
			result = 12;
		}
		
		return result;
		
	}
	
	private String getJoinMessage(int result) {
		
		String msg = "";

		// 메시지 출력
		switch( result ) {
			
			case 1:
				msg = "success";
				break;
				
			case 2:
				msg = "이메일 길이를 확인하세요.";
				break;
				
			case 3:
				msg = "이메일 규칙을 확인하세요.";
				break;

			case 4:
				msg = "이메일 주소가 중복됩니다.";
				break;
				
			case 5:
				msg = "비밀번호가 일치하지 않습니다.";
				break;
				
			case 6:
				msg = "비밀번호 길이를 4자리 이상으로 하세요.";
				break;
				
			case 7:
				msg = "이름을 입력하세요.";
				break;
				
			case 8:
				msg = "이름을 3자리 이상으로 해주세요.";
				break;
				
			case 9:
				msg = "생년월일을 입력하세요.";
				break;
			
			case 10:
				msg = "권한을 선택하세요.";
				break;
				
			case 11:
				msg = "토큰을 입력하세요.";
				break;
				
			default:
				msg = "예기치 못한 오류가 발생했습니다.";
				break;
				
		}
		
		return msg;
		
	}
	

	private String getModifyMessage(int result) {
		
		String msg = "";

		// 메시지 출력
		switch( result ) {
			
			case 1:
				msg = "success";
				break;
				
			case 2:
				msg = "이메일 길이를 확인하세요.";
				break;
				
			case 3:
				msg = "이메일 규칙을 확인하세요.";
				break;

			case 4:
				msg = "이메일 주소가 존재하지 않습니다.";
				break;
				
			case 5:
				msg = "비밀번호가 일치하지 않습니다.";
				break;
				
			case 6:
				msg = "비밀번호 길이를 4자리 이상으로 하세요.";
				break;
				
			case 7:
				msg = "이름을 입력하세요.";
				break;
				
			case 8:
				msg = "이름을 3자리 이상으로 해주세요.";
				break;
				
			case 9:
				msg = "생년월일을 입력하세요.";
				break;
			
			case 10:
				msg = "권한을 선택하세요.";
				break;
				
			case 11:
				msg = "락/상태를 입력하세요.";
				break;
				
			case 12:
				msg = "토큰을 입력하세요.";
				break;
				
			default:
				msg = "예기치 못한 오류가 발생했습니다.";
				break;
				
		}
		
		return msg;
		
	}

	
}
