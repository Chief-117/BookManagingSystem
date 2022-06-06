package com.noah.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.noah.entity.AdminEntity;

public interface AdminRepo extends JpaRepository<AdminEntity,String>{

	@Query(value="select * from ADMIN where admin_name =:adminName and admin_pwd =:adminPwd" , nativeQuery = true)
	public AdminEntity checkNameAndPwd(@Param("adminName") String adminName, @Param("adminPwd") String adminPwd);

	Optional<AdminEntity> findAdminByAdminMail(String adminMail);
	//for AuthController produce JWT 
}
