package com.cyou.userorder.service;

import java.util.List;

import com.cyou.core.bean.PageList;
import com.cyou.userorder.bean.UserOrder;
import com.cyou.userorder.bean.UserOrderModel;

public interface PayService{

	boolean saveOrder(UserOrder order);

	List<UserOrderModel> getOrderByUserId(String userId);

	Long getNotPaidOrderCountByUserId(String userId);

	Long getPaidOrderCountByUserId(String userId);

	Long getCanceledOrderCountByUserId(String userId);

	Long getRefundOrderCountByUserId(String userId);

	List<UserOrderModel> getOrderByUserIdAndStatus(String userId,int status);

	long getAllOrderCountByUserId(String userId);

	UserOrderModel getUserOrderModelByOrderId(String orderId);

	UserOrder getOrderbyOrderId(String orderId);

	void updateUserOrder(UserOrder order);

	PageList getPageList(PageList pagelist,Integer status);

}
