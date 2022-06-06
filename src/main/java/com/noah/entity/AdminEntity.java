package com.noah.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="ADMIN")
public class AdminEntity {
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
	public AdminEntity(String adminName, String adminPwd, String adminMail) {
		this.adminName = adminName;
		this.adminPwd = adminPwd;
		this.adminMail = adminMail;
	}
}
