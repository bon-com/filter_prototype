package com.example.prototype.web.controller.type01;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.prototype.biz.service.type01.CartService;

@Controller
@RequestMapping("type01/order")
public class OrderController {
	
	/** カートサービス */
	@Autowired
	private CartService cartService;
	
	/**
	 * 注文内容確認画面の表示
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/")
	public String confirm(Model model) {
		// カート情報
		model.addAttribute("cartitems", cartService.getAllItems());
		// 合計金額
		model.addAttribute("totalPrice", cartService.getTotalPrice());
		
		return "type01/confirm";
	}
	
	@GetMapping(value = "/complete")
	public String complete(HttpSession session) {
		// カート情報をセッションから削除
	    session.removeAttribute("scopedTarget.cart");
		return "type01/complete";
	}
}
