package com.noah.controller;

import java.util.HashMap;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noah.api.model.AjaxResultType;
import com.noah.api.model.BaseResponse;
import com.noah.service.AdminService;
import com.noah.service.JwtAuthService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private JwtAuthService jwtAuthService;

	@Autowired
	private AdminService adminService;

	@PostMapping("/login")
	public BaseResponse JWTLogin(@RequestBody HashMap<String, String> map) {
		BaseResponse res = new BaseResponse();
		String pwd = map.get("adminPwd");
		String mail = map.get("adminMail");
		String result = jwtAuthService.VerifyAdmin(mail, pwd);
		if (!StringUtils.isBlank(result)) {
			res.setCode(AjaxResultType.SUCCESS.getCode());
			res.setMsg(AjaxResultType.SUCCESS.getMsg());
			res.setData(result);
		} else {
			res.setCode(AjaxResultType.EMPTY.getCode());
			res.setMsg(AjaxResultType.EMPTY.getMsg());
			res.setData(result);
		}
		return res;
	}

	@GetMapping("/hello")
	public String hello() {
//		JwtToken jwtToken = new JwtToken();
//		BaseResponse res = new BaseResponse();
//		res.setCode("200");
//	    String token = jwtToken.generateToken(map);
		return "HelloWorld";
	}
}
