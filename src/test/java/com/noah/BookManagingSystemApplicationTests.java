package com.noah;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.noah.dao.AdminRepo;
import com.noah.entity.Admin;

@SpringBootTest
class BookManagingSystemApplicationTests {
	
	@Autowired 
	private AdminRepo adminRepo;
	
	@Test
	public void findByUserName() {
		String username = "noahtseng";
		String pwd = "banana123456";
		Admin admin = adminRepo.checkNameAndPwd(username, pwd);
		if(admin != null) {
			System.out.println("有這筆資料");
		}
	}
}
