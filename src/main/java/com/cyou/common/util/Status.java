package com.cyou.common.util;
/**
 * 
 * @author wufengke
 *
 */
public enum Status {
	
	/**可用状态控制**/
	DISABLE(0,"不可用"),ENABLE(1,"可用");
	
	private int status;
	
	private String statusDesc;
	
	private Status(int status,String statusDesc){
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
