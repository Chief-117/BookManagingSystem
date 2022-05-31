package com.noah.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noah.service.AdminService;
import com.noah.service.JWTAuthService;

@Controller
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private JWTAuthService theJWTAuthService;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String Login(@RequestParam("name") String name,@RequestParam("pwd") String pwd) {
        return adminService.findByName(name,pwd);
	}
	@PostMapping("/login2")
	public ResponseEntity<String> JWTLogin(@RequestBody Map<String,String> map){
		String mail= map.get("adminMail");
        String pwd= map.get("adminPwd");
        String name= map.get("adminName");
        String result="null data";
        if(StringUtils.isBlank(mail)||StringUtils.isBlank(pwd)||StringUtils.isBlank(name)){
            return ResponseEntity.ok(result);
        }else{
            result=theJWTAuthService.VerifyAdmin(name, pwd, mail);
            return ResponseEntity.ok(result);  
        }	
	}
}
