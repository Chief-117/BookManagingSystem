package com.noah.service;

import java.util.Optional;

import com.noah.dao.AdminRepo;
import com.noah.entity.Admin;

public interface AdminService {
	
	
	public String findByName(String name,String pwd);
	
	public Optional<Admin> getOneAdmin(String name);
}
