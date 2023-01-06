package com.digital.apigateway;

import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import com.digital.schema.Inventory;
import com.digital.utils.HttpConnectionUtils;

@Component
public class InventoryContext {

	/** 재고 검색 */
	public Inventory searchInventory(String productName) throws Exception {

		Inventory inventory = new Inventory();
		String url = "http://commerce-inventory-svc/rest/inventory/quantity/inquiry/";
		url += URLEncoder.encode(productName, "UTF-8");
		String response = HttpConnectionUtils.getRequest(url);
		inventory = (Inventory) HttpConnectionUtils.jsonToObject(response, inventory.getClass());
		return inventory;
	}

	/** 재고 정보 검색 */
	public Inventory searchInventoryById(Inventory inventory) throws Exception {
		
		String url = "http://commerce-inventory-svc/rest/inventory/info";
		String response = HttpConnectionUtils.postRequest(url, inventory);
		inventory = (Inventory) HttpConnectionUtils.jsonToObject(response, inventory.getClass());
		return inventory;
	}

	/** 재고 등록 */
	public boolean insertInventory(Inventory inventory) throws Exception {
		
		String url = "http://commerce-inventory-svc/rest/inventory/insert";
		String response = HttpConnectionUtils.postRequest(url, inventory);
		inventory = (Inventory) HttpConnectionUtils.jsonToObject(response, inventory.getClass());
		if(inventory != null) {
			return true;
		}
		return false;
	}

}