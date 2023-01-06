package com.digital.apigateway;

import org.springframework.stereotype.Component;

import com.digital.schema.Address;
import com.digital.utils.HttpConnectionUtils;

@Component
public class AddressContext {

	/** Address 정보 검색 (AddressID -> AddressDetail) */
	public Address searchAddressById(Address address) throws Exception {
		
		String url = "http://commerce-person-svc/rest/address/detailinfo";
		String response = HttpConnectionUtils.postRequest(url, address);
		address = (Address) HttpConnectionUtils.jsonToObject(response, address.getClass());
		return address;
	}
	
	/** Address 정보 검색 (AddressDetail -> AddressID) */
	public Address searchAddressByDetail(Address address) throws Exception {
		
		String url = "http://commerce-person-svc/rest/address/info";
		String response = HttpConnectionUtils.postRequest(url, address);
		address = (Address) HttpConnectionUtils.jsonToObject(response, address.getClass());
		return address;
	}

}
