package com.noah.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.noah.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin,Integer>{

	@Query(value="select * from ADMIN where admin_name =:adminName and admin_pwd =:adminPwd" , nativeQuery = true)
	public Admin checkNameAndPwd(@Param("adminName") String adminName, @Param("adminPwd") String adminPwd);

}
