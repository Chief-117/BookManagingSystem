package com.noah.api.model;

import lombok.Getter;

@Getter
public enum AjaxResultType {
	SUCCESS("200","作業處理成功"),
	BAD_REQUEST("E01","作業處理失敗"),
	FALILURE("E99","作業處理失敗"),
	EMPTY("050","查無資料");
	
	String code;
	
	String msg;
	
	AjaxResultType(String code ,String msg){
		this.code = code; 
		this.msg = msg; 
	}	
}

