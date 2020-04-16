package com.birtouta.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public interface Metier {
	
	 static final char[] hexArray = "0123456789ABCDEF".toCharArray();

	
	public static String generateTokenHash()throws NoSuchAlgorithmException, UnsupportedEncodingException  {
		MessageDigest salt = MessageDigest.getInstance("SHA-256");
		salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
		return  bytesToHex(salt.digest());
	}
	
	public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
	
	public static Date getCurrentTimestamp() {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		try {
			d = df.parse(df.format(new Timestamp(System.currentTimeMillis())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
}
