package com.emudhra.emra.subscriber.config;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CustomEncryption {
	
	   public static String emEncrypt(String plainText) {
	       try {
	           final MessageDigest md = MessageDigest.getInstance("md5");
	           final byte[] digestOfPassword = md.digest("%#$%$^".getBytes("utf-8"));
	           final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
	           for (int j = 0, k = 16; j < 8;) {
	               keyBytes[k++] = keyBytes[j++];
	           }

	           final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
	           byte[] IV = {(byte) 240, (byte) 3, (byte) 45, (byte) 29, (byte) 0, (byte) 76, (byte) 173, (byte) 59};
	           final IvParameterSpec iv = new IvParameterSpec(IV);
	           final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
	           cipher.init(Cipher.ENCRYPT_MODE, key, iv);

	           final byte[] plainTextBytes = plainText.getBytes("utf-8");
	           final byte[] cipherText = cipher.doFinal(plainTextBytes);

	           return new String(Base64.getEncoder().encode(cipherText));//encode(cipherText));
	       } catch (Exception ex) {
	       	System.out.println("Exception is"+ex); 
	           return null;
	       }
	   }
	   
	   public static String emDecrypt(String ecryptedString) throws Exception {
	       String status = "";
	       try {
	           byte[] message = Base64.getDecoder().decode(ecryptedString);//decode(ecryptedString);
	           final MessageDigest md = MessageDigest.getInstance("md5");
	           final byte[] digestOfPassword = md.digest("%#$%$^".getBytes("utf-8"));
	           final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
	           for (int j = 0, k = 16; j < 8;) {
	               keyBytes[k++] = keyBytes[j++];
	           }
	           final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
	           byte[] IV = {(byte) 240, (byte) 3, (byte) 45, (byte) 29, (byte) 0, (byte) 76, (byte) 173, (byte) 59};
	           final IvParameterSpec iv = new IvParameterSpec(IV);
	           final Cipher decipher = Cipher.getInstance("DESede/CBC/NoPadding");
	           decipher.init(Cipher.DECRYPT_MODE, key, iv);
	           final byte[] plainText = decipher.doFinal(message);

	           return new String(plainText);
	       } catch (Exception ex) {
	       	System.out.println("Exception is"+ex);
	           return null;
	       }
	   }
}
