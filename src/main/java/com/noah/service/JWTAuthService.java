package com.noah.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noah.dao.AdminRepo;
import com.noah.entity.Admin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JWTAuthService {
	
	private AdminRepo adminRepo;
	
	@Autowired
	public JWTAuthService(AdminRepo adminRepo) {//建構子注入 constructor injection
		this.adminRepo = adminRepo;
	}
	public String VerifyAdmin(String adminName,String adminPwd,String adminMail) {
		Optional<Admin> admin = adminRepo.findAdminByAdminMail(adminMail);
		/*
        0000 login success
        0001 wrong email
        0002 wrong name
        0003 worng pwd
        9999 unknown error
        */
		String result = "9999";
		if(admin.isPresent()) {//登入成功先找NAME
			if(admin.get().getAdminName().equals(adminName)) {
				if(admin.get().getAdminPwd().equals(adminPwd)) {
					Date date = new Date(System.currentTimeMillis()+30*60*1000);
					String jwtToken = Jwts.builder().setSubject(adminMail).setExpiration(date).signWith(SignatureAlgorithm.HS512,"MySecret")
			                .compact();
					result = jwtToken;
				}else {
					result = "0002";
				}
				
			}else {
				result = "0002";
			}
		}else {
			result = "0001";
		}
		return result;
		
	}
}
