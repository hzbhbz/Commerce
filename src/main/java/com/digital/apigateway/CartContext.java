package com.digital.apigateway;

import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import com.digital.schema.Cart;
import com.digital.schema.CartList;
import com.digital.utils.HttpConnectionUtils;

@Component
public class CartContext {

	/** 장바구니 검색 */
	public CartList searchCartList(long personId) throws Exception {
		
		CartList carList = new CartList();
		String url = "http://commerce-order-svc/rest/cart/inquiry/";
		url += URLEncoder.encode(String.valueOf(personId), "UTF-8");
		String response = HttpConnectionUtils.getRequest(url);
		carList = (CartList) HttpConnectionUtils.jsonToObject(response, carList.getClass());
		return carList;
	}

	/** 장바구니 담기 */
	public Cart insertCart(Cart cart, String token) throws Exception {
		
		String url = "http://commerce-order-svc/rest/cart/insert";
		String response = HttpConnectionUtils.postRequest(url, cart, token);
		cart = (Cart) HttpConnectionUtils.jsonToObject(response, cart.getClass());
		return cart;
	}
}