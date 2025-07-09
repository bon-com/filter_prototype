package com.example.prototype.web.controller.type01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.prototype.biz.service.type01.CartService;
import com.example.prototype.biz.service.type01.ItemService;
import com.example.prototype.web.dto.type01.CartAddForm;

@Controller
@RequestMapping("type01/cart")
public class CartController {
	
	/** 商品サービス */
	@Autowired
	private ItemService itemService;
	/** カートサービス */
	@Autowired
	private CartService cartService;

	/**
	 * 商品をカートに追加する
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/add")
	public String addItem(CartAddForm form) {
		// 追加対象の商品が実在すれば追加
		var item = itemService.findById(form.getItemId());
		int quantity = form.getQuantity();
		if (item != null && quantity > 0) {
			cartService.addItem(item, quantity);
		}

		return "redirect:/type01/items/";
	}
	
	/**
	 * 商品をカートから削除
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/delete/{id}")
	public String deleteItem(@PathVariable String id) {
		cartService.deleteItem(id);
		return "redirect:/type01/order/";
	}
}
