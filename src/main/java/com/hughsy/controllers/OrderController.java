package com.hughsy.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hughsy.domain.Customer;
import com.hughsy.domain.Order;
import com.hughsy.repository.OrderRepository;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderRepository orderRepo;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String orderGet(ModelMap model) {

		List<Order> orders = orderRepo.findAll();

		model.put("orders", orders);	

		return "orders";
	}

	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
	public String orderGet(@PathVariable Long orderId, ModelMap model) {

		Order order = orderRepo.findById(orderId).get();
		model.put("order", order);
		return "orders";
	}

	@RequestMapping(value = "/{orderId}", method = RequestMethod.POST)
	public String orderPost(@PathVariable Long orderId, HttpServletRequest request, @ModelAttribute Order order,
			ModelMap model) {
		model.put("orderId", orderId);
		return "redirect:/orders/" + orderId + "/pizzas";
	}
	
	@RequestMapping(value = "/{orderId}/completeOrder", method = RequestMethod.POST)
	public String submitOrder(@PathVariable Long orderId, ModelMap model) {
		Order order = orderRepo.findById(orderId).get();
		order.setCompleted(true);
		orderRepo.save(order);
		return "redirect:/orders/";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String orderPost(HttpServletRequest request, @ModelAttribute Order order, ModelMap model) {

		Customer customer = (Customer) request.getSession().getAttribute("customer");
		order.setCustomer(customer);

		Order savedOrder = orderRepo.save(order);
		return "redirect:/orders/" + savedOrder.getId() + "/pizzas";
	}
}
