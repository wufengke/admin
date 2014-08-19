package com.cyou.base.aop;

import java.util.Date;

public class SystemLog {
	private Date createtime;
	private String userName;  
    private String exeOperation;
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getExeOperation() {
		return exeOperation;
	}
	public void setExeOperation(String exeOperation) {
		this.exeOperation = exeOperation;
	}
	
	@Override
	public String toString() {
		return this.getCreatetime()+":"+this.getUserName()+":"+this.getExeOperation();
	}
	
	
    
    
}
