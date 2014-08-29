package com.cyou.userorder.dao;

import java.util.List;

import com.cyou.core.dao.BaseDao;
import com.cyou.userorder.bean.UserOrder;
import com.cyou.userorder.bean.UserOrderModel;

public interface PayDao extends BaseDao{

	List<UserOrderModel> getOrderByUserId(String userId);

	List<UserOrderModel> getOrderByUserIdAndStatus(String userId, int status);
	
	Long getCanceledOrderCountByUserId(String userId);

	Long getNotPaidOrderCountByUserId(String userId);

	Long getPaidOrderCountByUserId(String userId);

	Long getRefundOrderCountByUserId(String userId);

	long getAllOrderCountByUserId(String userId);

	UserOrderModel getUserOrderModelByOrderId(String orderId);

	UserOrder getOrderbyOrderId(String orderId);


}
