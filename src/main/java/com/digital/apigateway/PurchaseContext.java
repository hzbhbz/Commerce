package com.digital.apigateway;

import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import com.digital.schema.Order;
import com.digital.schema.Purchase;
import com.digital.utils.HttpConnectionUtils;

@Component
public class PurchaseContext {

	/** 결제 내역 조회 */
	public Order searchPurchase(long personId) throws Exception {

		Order order = new Order();
		String url = "http://commerce-order-svc/rest/purchase/inquiry/";
		url += URLEncoder.encode(String.valueOf(personId), "UTF-8");
		String response = HttpConnectionUtils.getRequest(url);
		order = (Order) HttpConnectionUtils.jsonToObject(response, order.getClass());
		return order;
	}

	/** 결제 */
	public Purchase insertPurchase(Purchase purchase) throws Exception {

		String url = "http://commerce-order-svc/rest/purchase";
		String response = HttpConnectionUtils.postRequest(url, purchase);
		purchase = (Purchase) HttpConnectionUtils.jsonToObject(response, purchase.getClass());
		return purchase;
	}

}