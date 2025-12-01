package com.tastyExpress.dao;

import java.util.List;
import com.tastyExpress.model.OrderItem;

public interface OrderItemDAO {

	void addOrderItem(OrderItem orderitem);
	
	OrderItem getOderItem(int orderItemId);
	
	void updateOrderItem(OrderItem orderitem);
	
	void deleteOrderItem(int orderItemId);
	
	List<OrderItem> getAllOrderItems();
}
