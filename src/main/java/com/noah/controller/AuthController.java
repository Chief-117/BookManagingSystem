package com.noah.controller;

import java.util.Optional;

import com.noah.dao.AdminRepo;
import com.noah.entity.Admin;

public class AuthController {
	
	private AdminRepo adminRepo;
	
	public String VerifyAdmin(String adminName,String adminPwd) {
		Optional<Admin> admin = adminRepo.findAdminByAdminName(adminName);
		/*
        0000 login success
        0001 wrong adminNaME
        0002 wrong PWD
        9999 unknown error
        */
		String result = "9999";
		if(admin.isPresent()) {
			if(admin.get().getAdminPwd().equals(adminPwd)) {
				result = "0000";
			}else {
				result = "0002";
			}
		}else {
			result = "0001";
		}
		return result;
		
	}
}
