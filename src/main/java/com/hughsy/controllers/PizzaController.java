package com.hughsy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hughsy.domain.Order;
import com.hughsy.domain.Pizza;
import com.hughsy.domain.Topping;
import com.hughsy.enums.PizzaCrustEnum;
import com.hughsy.enums.PizzaSizeEnum;
import com.hughsy.repository.OrderRepository;
import com.hughsy.repository.ToppingRepository;

@Controller
@RequestMapping("/orders/{orderId}/pizzas")
public class PizzaController {

	@Autowired
	private ToppingRepository toppingRepo;
	@Autowired
	private OrderRepository orderRepo;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String pizzaGet(@PathVariable Long orderId, ModelMap model) {

		model.put("pizzaSizes", PizzaSizeEnum.values());
		model.put("pizzaCrusts", PizzaCrustEnum.values());
		model.put("toppings", toppingRepo.findAll());

		Pizza pizza = new Pizza();
		model.put("pizza", pizza);

		return "pizzas";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String pizzaPost(@ModelAttribute Pizza pizza, @PathVariable Long orderId, ModelMap model) {

		Order order = orderRepo.findById(orderId).get();

		for (Topping topping : pizza.getToppings()) {
			topping.getPizzas().add(pizza);
		}

		pizza.setOrder(order);
		order.getPizzas().add(pizza);

		orderRepo.save(order);

		return "redirect:/orders/" + orderId;
	}
}
