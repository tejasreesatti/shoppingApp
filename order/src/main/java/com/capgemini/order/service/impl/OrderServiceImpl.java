package com.capgemini.order.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.order.entity.Order;
import com.capgemini.order.exceptions.orderNotFoundException;
import com.capgemini.order.repository.OrderRepository;
import com.capgemini.order.service.OrderService;

public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order addOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order editOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Order order) {
		orderRepository.delete(order);
		
	}

	@Override
	public List<Order> displayAllOrders() {
		return orderRepository.findAll();
				
	}

	@Override
	public Order findOrderById(int orderId) {
		Optional<Order> optionalorder= orderRepository.findById(orderId);
		if(optionalorder.isPresent())
			return optionalorder.get();
		throw new orderNotFoundException("Product does not exists");
	}

}
