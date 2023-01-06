package com.digital.apigateway;

import org.springframework.stereotype.Component;

import com.digital.schema.Phone;
import com.digital.utils.HttpConnectionUtils;

@Component
public class PhoneContext {

	/** PhoneID를 통한 전화번호 검색 */
	public Phone searchPhoneByID(Phone phone) throws Exception {
		
		String url = "http://commerce-person-svc/rest/phone/detailinfo";
		String response = HttpConnectionUtils.postRequest(url, phone);
		phone = (Phone) HttpConnectionUtils.jsonToObject(response, phone.getClass());
		return phone;
	}
	
	/** PhoneNumber 를 통한 전화번호 ID 검색 */
	public Phone searchPhoneByDetail(Phone phone) throws Exception {
		
		String url = "http://commerce-person-svc/rest/phone/info";
		String response = HttpConnectionUtils.postRequest(url, phone);
		phone = (Phone) HttpConnectionUtils.jsonToObject(response, phone.getClass());
		return phone;
	}

	
}