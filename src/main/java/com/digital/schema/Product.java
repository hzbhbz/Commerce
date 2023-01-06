package com.digital.schema;

//import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;

@ArraySchema
public class Product {

	@ApiModelProperty(required = false, position = 1, example = "1", dataType = "long", notes = "상품ID")
	private long productId;
	@ApiModelProperty(required = true, position = 2, example = "100", dataType = "long", notes = "상품 카테고리 ID")
	private long categoryId;
	@ApiModelProperty(required = true, position = 4, example = "20000", dataType = "long", notes = "상품 가격")
	private long price;
	@ApiModelProperty(required = true, position = 5, example = "상품명", dataType = "string")
	private String productName;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

//	public static void main (String [] args) throws Exception {
//		
//		Class cl = Class.forName("com.digital.schema.Product");
//		Object obj = cl.newInstance();
//		
//		Method [] methods = cl.getMethods();
//		
//		for (Method method : methods) {
//			System.out.println(method.getName());
//			Parameter [] params = method.getParameters();
//		}
//	}
}
