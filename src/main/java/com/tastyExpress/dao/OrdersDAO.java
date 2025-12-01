package com.tastyExpress.dao;

import java.util.List;

import com.tastyExpress.model.Orders;

public interface OrdersDAO {

	void  addOrder(Orders orders);
	
	Orders getOrder(int orderId);
	
	void updateOrder(Orders orders);
	
	void deleteOrder(int orderId);
	
	List<Orders> getAllOrders();
	
	List<Orders> getOrdersByUserId(int userId);

}
