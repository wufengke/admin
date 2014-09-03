package com.cyou.userorder.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyou.core.bean.PageList;
import com.cyou.userorder.bean.UserOrder;
import com.cyou.userorder.bean.UserOrderModel;
import com.cyou.userorder.dao.PayDao;
import com.cyou.userorder.service.PayService;
@Service
@Transactional
public class PayServicempl  implements PayService{

	private static Logger logger = Logger.getLogger(PayServicempl.class);

	@Resource
	private PayDao payDao;
	@Override
	public boolean saveOrder(UserOrder order) {
		try {
			payDao.save(order);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	@Override
	public List<UserOrderModel> getOrderByUserId(String userId) {
		try {
			return payDao.getOrderByUserId(userId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<UserOrderModel>();
		}
	}
	@Override
	public List<UserOrderModel> getOrderByUserIdAndStatus(String userId,int status) {
		try {
			return payDao.getOrderByUserIdAndStatus(userId,status);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<UserOrderModel>();
		}
	}
	@Override
	public Long getCanceledOrderCountByUserId(String userId) {
		try {
			return payDao.getCanceledOrderCountByUserId(userId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0l;
		}
	}
	@Override
	public Long getNotPaidOrderCountByUserId(String userId) {
		try {
			return payDao.getNotPaidOrderCountByUserId(userId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0l;
		}
	}
	@Override
	public Long getPaidOrderCountByUserId(String userId) {
		try {
			return payDao.getPaidOrderCountByUserId(userId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0l;
		}
	}
	@Override
	public Long getRefundOrderCountByUserId(String userId) {
		try {
			return payDao.getRefundOrderCountByUserId(userId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0l;
		}
	}
	@Override
	public long getAllOrderCountByUserId(String userId) {
		try {
			return payDao.getAllOrderCountByUserId(userId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0l;
		}
	}
	@Override
	public UserOrderModel getUserOrderModelByOrderId(String orderId) {
		try {
			return payDao.getUserOrderModelByOrderId(orderId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new UserOrderModel();
		}
	}
	@Override
	public UserOrder getOrderbyOrderId(String orderId) {
		try {
			return payDao.getOrderbyOrderId(orderId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	@Override
	public void updateUserOrder(UserOrder order) {
		try {
			payDao.update(order);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	@Override
	public PageList getPageList(PageList pageList,Integer status) {
		return payDao.search("from UserOrder uo where " + ((status == null || status == 4)? " 1=1":" uo.status=" + status) + " order by uo.updateTime desc", null, pageList);
	}

}
