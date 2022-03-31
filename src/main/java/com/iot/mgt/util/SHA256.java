package com.iot.mgt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

	private static SHA256 instance;

	private SHA256() {}
	
	public static SHA256 getInstance() {
		
		if ( instance == null ) {
			instance = new SHA256();
		}
		
		return instance;
	}
	
    public String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
    	
        StringBuilder builder = new StringBuilder();
        
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        
        return builder.toString();
        
    }

}