package com.cyou.common.util;
/**
 * 
 * @author wufengke
 *
 */
public enum ApplyTeachStatus {
	
	REJECT(-1,"拒绝"),PENDING(0,"待审"),APROVE(1,"通过");
	
	private int status;
	
	private String statusDesc;
	
	private ApplyTeachStatus(int status,String statusDesc){
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
	public static void main(String[] args) {
	}
	
}
