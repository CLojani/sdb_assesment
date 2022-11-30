package com.sdb.assesment.service;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
	
	private final String encryptKey = "sdb@sdb";

	//encryption
		public String encrypt(String pw) {
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(); 
			encryptor.setPassword(encryptKey);
			return encryptor.encrypt(pw);
		}
		//decryption
		public String decrypt(String enpw) {
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(); 
			encryptor.setPassword(encryptKey);
			return encryptor.decrypt(enpw);
		}
}
