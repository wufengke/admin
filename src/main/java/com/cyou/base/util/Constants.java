package com.cyou.base.util;

public class Constants {
	//server的status属性 0可用 1不可用
	public static Integer serverStatusKeYong = 0;
	public static Integer serverStatusBuKeYong = 1;
	
	//server的workStatus属性 0空闲 1良好 2繁忙 3维护
	public static Integer serverWorkStatusKongXian = 0;
	public static Integer serverWorkStatusLiangHao = 1;
	public static Integer serverWorkStatusFanMang = 2;
	public static Integer serverWorkStatusWeiHu = 3;
	
	//server的display属性 0显示  1不显示
	public static Integer serverDisplayXianShi = 0;
	public static Integer serverDisplayBuXianShi = 1;
	
	//server的recommend属性 推荐：0一般，1推荐
	public static Integer serverRecommendYiBan = 0;
	public static Integer serverRecommendTuiJian = 1;
}
