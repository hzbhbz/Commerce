package com.digital.apigateway;

import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import com.digital.schema.Category;
import com.digital.utils.HttpConnectionUtils;

@Component
public class CategoryContext {

	/** 카테고리 등록 */
	public Category insertCategory(Category category) throws Exception {
		
		String url = "http://commerce-product-svc/rest/category/insert";
		String response = HttpConnectionUtils.postRequest(url, category);
		category = (Category) HttpConnectionUtils.jsonToObject(response, category.getClass());
		return category;
	
	}
	
	/** 카테고리 검색 */
	public Category searchCategory(String categoryName) throws Exception {
		
		Category category = new Category();
		String url = "http://commerce-product-svc/rest/category/inquiry/";
		url += URLEncoder.encode(categoryName, "UTF-8");
		String response = HttpConnectionUtils.getRequest(url);
		category = (Category) HttpConnectionUtils.jsonToObject(response, category.getClass());
		return category;
	}
}