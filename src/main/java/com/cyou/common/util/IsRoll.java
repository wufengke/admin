package com.cyou.common.util;

public enum IsRoll {
	
	ROLL(0,"否"),NOTROLL(1,"是");
	
	private int status;
	
	private String statusDesc;
	
	private IsRoll(int status,String statusDesc){
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
