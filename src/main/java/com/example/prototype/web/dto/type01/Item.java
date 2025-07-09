package com.example.prototype.web.dto.type01;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 商品クラス
 */
@Data
@AllArgsConstructor
public class Item {
	/** 商品ID */
	private String id;
	/** 商品名 */
	private String name;
	/** 値段 */
	private int price;
}
