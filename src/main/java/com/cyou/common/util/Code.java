package com.cyou.common.util;

public enum Code {
	ERROR_APPID_EMPTY(1,"appId is empty");
	
	private int status;
	
	private String desc;
	
	private Code(int status,String desc){
		this.status = status;
		this.desc = desc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
