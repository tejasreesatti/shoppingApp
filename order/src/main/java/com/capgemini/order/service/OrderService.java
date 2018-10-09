package com.capgemini.order.service;

import java.util.List;

import com.capgemini.order.entity.Order;

public interface OrderService {

	public Order addOrder (Order order);
	public Order editOrder (Order order);
	public void deleteOrder (Order order);
	public Order findOrderById (int orderId);
	public List<Order> displayAllOrders();
}
