package com.example.prototype.biz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prototype.web.dto.Cart;
import com.example.prototype.web.dto.CartItem;
import com.example.prototype.web.dto.Item;

/**
 * カートサービス
 */
@Service
public class CartService {

	/** カート（セッション管理） */
	@Autowired
	private Cart cart;

	/** カートに商品と数量を追加 */
	public void addItem(Item item, int quantity) {
		cart.getItems().compute(item.getId(), (key, val) -> {
			if (val == null) {
				// 商品が存在しない場合、新規追加
				return new CartItem(item, quantity);
			} else {
				// 商品が存在する場合、数量追加
				val.setQuantity(val.getQuantity() + quantity);
				return val;
			}
		});
	}

	/** カート内商品を削除 */
	public void deleteItem(String itemId) {
		cart.getItems().remove(itemId);
	}
	
	/** カート内の合計金額を取得 */
	public int getTotalPrice() {
		return  cart.getItems().values().stream()
				.mapToInt(CartItem::getTotal)
				.sum();
	}
	
	/** カート内の商品一覧を取得 */
	public List<CartItem> getAllItems() {
		return new ArrayList<>(cart.getItems().values());
	}
}
