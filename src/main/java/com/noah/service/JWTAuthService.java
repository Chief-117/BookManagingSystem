package com.noah.service;

import java.sql.Date;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.noah.dao.AdminRepo;
import com.noah.entity.AdminEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JWTAuthService {
	final Base64.Decoder decoder = Base64.getDecoder();
	/*
        0000 login success
        0001 wrong email
        0002 wrong pwd
        9999 unknown error
	 */	
	private AdminRepo adminRepo;
	
	@Autowired
	public JWTAuthService(AdminRepo adminRepo) {//建構子注入 constructor injection
		this.adminRepo = adminRepo;
	}
	public String VerifyAdmin(String adminMail,String adminPwd) {
		Optional<AdminEntity> admin = adminRepo.findAdminByAdminMail(adminMail);
		String result = "9999";
		if(admin.isPresent()) {//登入成功先確認PWD
				if(admin.get().getAdminPwd().equals(adminPwd)) {
				Algorithm algorithm = Algorithm.HMAC512("my-512-bit-secret");
				Date date = new Date(System.currentTimeMillis()+ 30 * 60 * 1000);
					result = Jwts.builder().setSubject(adminMail).setExpiration(date).signWith(SignatureAlgorithm.HS512,"MySecret")
			                .compact();	
				}else {
					result = "wrong pwd";
				}
		}else {
			result = "wrong email";
		}
		return result;
		
	}
//	public static boolean verify(String token) {
//		try {
//			Algorithm algorithm = Algorithm.HMAC512("my-512-bit-secret");
//			JWTVerifier verifier = JWT.require(algorithm).build();
//			DecodedJWT jwt = verifier.verify(token);
//			return true;
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
}

//				    String jwtToken = JWT.create()
//				            .withIssuer("auth0")
//				    		.withClaim("adminMail", adminMail)
//				            .withSubject(adminPwd)
//				            .withExpiresAt(date)
//				            .sign(algorithm);