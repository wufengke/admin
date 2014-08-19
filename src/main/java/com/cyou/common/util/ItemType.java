package com.cyou.common.util;


/**
 * 
 * @author wufengke
 *
 */
public enum ItemType {
	
	CONSUME("可消耗"),
	NON_CONSUME("不可消耗");
	
	private String desc;
	
	private ItemType(String desc){
		this.desc = desc;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static void main(String[] args) {
		ItemType[] dddItemTypes = ItemType.values();
		for (int i = 0; i < dddItemTypes.length; i++) {
			System.out.println(dddItemTypes[i].ordinal());
		}
	}
}
