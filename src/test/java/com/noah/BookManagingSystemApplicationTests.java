package com.noah;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.noah.dao.AdminRepo;
import com.noah.entity.Admin;

@SpringBootTest
class BookManagingSystemApplicationTests {
	
	@Autowired 
	private AdminRepo adminRepo;
	
	@Test//內建測試可先透過此地方打API
	public void findByUserName() {
		String username = "noahtseng";
		String pwd = "banana123456";
		String mail = "a9710725@yahoo.com.com";
		Admin admin = adminRepo.checkNameAndPwd(username, pwd);
		Optional<Admin> a2 = adminRepo.findAdminByAdminMail(mail);
		if(admin != null) {
			System.out.println("有這筆資料");
		}
		if(a2.isPresent()) {//測試JPA內建方法 find...By屬性值
			System.out.println("JWT測試有");
		}
	}
}
