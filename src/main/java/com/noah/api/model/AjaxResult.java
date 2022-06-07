package com.noah.api.model;

import lombok.Data;

@Data
public class AjaxResult {
	
	private String code;
	
	private String msg;
	
	private String data;
}
