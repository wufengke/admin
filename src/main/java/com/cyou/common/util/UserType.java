package com.cyou.common.util;

public enum UserType {
	INDIVIDUAL(0,"个人"),ENTERPRISE(1,"企业");
	
	private int type;
	private String typeDesc;
	
	private UserType(int type,String typeDesc){
		this.type=type;
		this.typeDesc=typeDesc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	

}
