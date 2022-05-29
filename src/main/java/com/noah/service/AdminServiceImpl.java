package com.noah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noah.dao.AdminRepo;
import com.noah.entity.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	public AdminServiceImpl(AdminRepo adminRepo) {
		this.adminRepo = adminRepo;
	}

	@Override
	public String findByName(String name, String pwd) {
		if (name.isEmpty() || pwd.isEmpty()) {
				return "Error";
			} else {
				Admin admin = adminRepo.checkNameAndPwd(name, pwd);
				if (admin != null) {
					return "OK";
			}
		}
		return "Error";
	}

}
