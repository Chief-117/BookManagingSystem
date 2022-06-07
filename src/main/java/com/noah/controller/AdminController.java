package com.noah.controller;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noah.api.model.AjaxResultType;
import com.noah.api.model.BaseResponse;
import com.noah.service.AdminService;
import com.noah.service.JWTAuthService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private JWTAuthService theJWTAuthService;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String Login(@RequestParam("name") String name,@RequestParam("pwd") String pwd) {
        return adminService.findByName(name,pwd);
	}
	@PostMapping("/login2")
	public BaseResponse JWTLogin(@RequestBody HashMap<String,String> map){
		BaseResponse res = new BaseResponse();
		String pwd= map.get("adminPwd");
		String mail= map.get("adminMail");
        String result="null data";
        if(StringUtils.isBlank(mail)||StringUtils.isBlank(pwd)){
            res.setCode(AjaxResultType.EMPTY.getCode());
            res.setMsg(AjaxResultType.EMPTY.getMsg());
        }else{
            result=theJWTAuthService.VerifyAdmin(mail, pwd);
            res.setCode(AjaxResultType.SUCCESS.getCode());
            res.setMsg(AjaxResultType.SUCCESS.getMsg());
            res.setData(result);
        }
        return res;
	}
}
