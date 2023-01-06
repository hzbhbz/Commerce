package com.digital.apigateway;

import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import com.digital.schema.Order;
import com.digital.utils.HttpConnectionUtils;

@Component
public class OrderContext {

	/** 가주문서 작성 */
	public Order insertOrder(Order order, String token) throws Exception {
		
		String url = "http://commerce-order-svc/rest/order/insert";
		String response = HttpConnectionUtils.postRequest(url, order, token);
		order = (Order) HttpConnectionUtils.jsonToObject(response, order.getClass());
		return order;
	}

	/** 가주문서 검색 */
	public Order searchOrder(long personId) throws Exception {
		
		Order order = new Order();
		String url = "http://commerce-order-svc/rest/order/inquiry/";
		url += URLEncoder.encode(String.valueOf(personId), "UTF-8");
		String response = HttpConnectionUtils.getRequest(url);
		order = (Order) HttpConnectionUtils.jsonToObject(response, order.getClass());
		return order;
	}
}