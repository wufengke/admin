package com.cyou.common.util;

public enum Environment {

	LIVE(0,"live"),SANDBOX(1,"sandbox");
	
	private int status;
	
	private String statusDesc;
	
	private Environment(int status,String statusDesc){
		this.status = status;
		this.statusDesc = statusDesc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	
}
