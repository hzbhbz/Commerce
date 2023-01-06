package com.digital.apigateway;

import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import com.digital.schema.Person;
import com.digital.utils.HttpConnectionUtils;

@Component
public class PersonContext {
	
	/** 회원 가입 */
	public Person signUp(Person person) throws Exception {
		
		String url = "http://commerce-person-svc/rest/person/signUp";
		String response = HttpConnectionUtils.postRequest(url, person);
		person = (Person) HttpConnectionUtils.jsonToObject(response, person.getClass());
		return person;
	}
	
	/** 로그인 */
	public Person login(Person person) throws Exception {
		
		String url = "http://commerce-person-svc/rest/person/login";
		String response = HttpConnectionUtils.postRequest(url, person);
		person = (Person) HttpConnectionUtils.jsonToObject(response, person.getClass());
		return person;
	}
	
	/** 회원 검색 */
	public Person searchPerson(String personName) throws Exception {
		
		Person person = new Person();
		String url = "http://commerce-person-svc/rest/person/inquiry/";
		url += URLEncoder.encode(personName, "UTF-8");
		String response = HttpConnectionUtils.getRequest(url);
		person = (Person) HttpConnectionUtils.jsonToObject(response, person.getClass());
		return person;
	}
	
}