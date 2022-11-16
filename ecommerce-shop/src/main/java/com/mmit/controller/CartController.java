package com.mmit.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mmit.controller.request.OrderProductData;
import com.mmit.controller.request.OrderReceiverData;
import com.mmit.controller.request.OrderRequestData;
import com.mmit.model.entity.OrderItem;
import com.mmit.model.entity.OrderStatus;
import com.mmit.model.entity.Orders;
import com.mmit.model.entity.User;
import com.mmit.model.service.OrderService;
import com.mmit.model.service.ProductService;
import com.mmit.model.service.UserService;

@Controller
public class CartController {

	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/cart/detail")
	public  String home() {
		return "cart";
	}
	
	@GetMapping("/cart/checkout")
	public String checkoutPage(ModelMap map, Principal principal) {
		System.err.println("principal : " + principal);
		
		User loginUser = userService.profile(principal.getName());
		map.put("name", loginUser.getName());
		map.put("phone", loginUser.getPhone());
		map.put("email", loginUser.getEmail());
		map.put("address", loginUser.getAddress());
		return "checkout";
	}
	
	@PostMapping("/cart/place-order")
	@ResponseBody
	public String makeOrder(@RequestBody OrderRequestData obj,Principal principal) {
		
		try {
			System.out.println("obj :" + obj.getReceiver());
			OrderReceiverData receiver = obj.getReceiver();
			List<OrderProductData> itemList = obj.getOrderItems();
			System.err.println("itemList " + itemList);
		
			Orders new_order = new Orders();
			new_order.setStatus(OrderStatus.pending);
			new_order.setShippingAddress(receiver.getAddress());
			new_order.setShippingEmail(receiver.getEmail());
			new_order.setShippingName(receiver.getName());
			new_order.setShippingPhone(receiver.getPhone());
			User customer = userService.profile(principal.getName());
			new_order.setCustomer(customer);
			
			
			//add order items
			for(var item : itemList) {
				System.err.println("amount : " + item.getPrice());
				new_order.setAmount(item.getPrice());
				
				var product = productService.findById(item.getProductId());
				System.err.println("product : " + product.getPrice());
				OrderItem order_item = new OrderItem();
			
				order_item.setProduct(product);
				System.err.println("Product : " + order_item);
				order_item.setQuantity(item.getQty());
				System.out.println("order_item : " + new_order.getOrderItems().size());
				new_order.addOrderItem(order_item);
			}
			
			//save order to db
			Orders saveOrder = orderService.save(new_order);
			return saveOrder.getId() + "";
			//return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
		
		
	}
}
