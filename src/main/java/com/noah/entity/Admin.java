package com.noah.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADMIN")
public class Admin {
	@Id
	@Column(name="admin_id")
	private Integer adminId;
	
	@Column(name="admin_name")
	private String adminName;
	
	@Column(name="admin_pwd")
	private String adminPwd;
	
	@Column(name="admin_mail")
	private String adminMail;

	@Override
	public String toString() {
		return "ADMIN [adminId=" + adminId + ", adminName=" + adminName + ", adminPwd=" + adminPwd + ", adminMail="
				+ adminMail + "]";
	}
	public Admin() {
		
	}
	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public String getAdminMail() {
		return adminMail;
	}

	public void setAdminMail(String adminMail) {
		this.adminMail = adminMail;
	}
	
}
