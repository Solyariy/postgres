package com.demodb.postgres.security.securityConfig;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Encoder extends BCryptPasswordEncoder {
	
	private final BCryptPasswordEncoder encoder;
	
	public Encoder() {
		encoder = new BCryptPasswordEncoder();
	}
	
	public String encode(String s) {
		return encoder.encode(s);
	}
}
