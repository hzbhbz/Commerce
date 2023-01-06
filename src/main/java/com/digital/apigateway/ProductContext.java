package com.digital.apigateway;

import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import com.digital.schema.Product;
import com.digital.utils.HttpConnectionUtils;

@Component
public class ProductContext {

	/** 상품 검색 */
	public Product searchProduct(String productName) throws Exception {
		
		Product product = new Product();
		String url = "http://commerce-product-svc/rest/product/inquiry/";
		url += URLEncoder.encode(productName, "UTF-8");
		String response = HttpConnectionUtils.getRequest(url);
		product = (Product) HttpConnectionUtils.jsonToObject(response, product.getClass());
		return product;
	}

	/** 상품 등록 */
	public Product insertProduct(Product product) throws Exception {
		
		String url = "http://commerce-product-svc/rest/product/insert";
		String response = HttpConnectionUtils.postRequest(url, product);
		product = (Product) HttpConnectionUtils.jsonToObject(response, product.getClass());
		return product;
	}
}
