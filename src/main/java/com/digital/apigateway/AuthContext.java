package com.digital.apigateway;

import org.springframework.stereotype.Component;

import com.digital.schema.Auth;
import com.digital.utils.HttpConnectionUtils;

@Component
public class AuthContext {

	/** 토큰 생성 */
	public Auth setToken(long personId) throws Exception {

		String url = "http://commerce-auth-svc/rest/auth/generatetoken";
		Auth auth = new Auth();
		auth.setPersonId(personId);
		String response = HttpConnectionUtils.postRequest(url, auth);
		auth = (Auth) HttpConnectionUtils.jsonToObject(response, auth.getClass());
		return auth;
	}

	/** 토큰 유효성 검증 */
	public Auth isValidToken(String token) throws Exception {
		
		String url_validToken = "http://commerce-auth-svc/rest/auth/validtoken";
		Auth auth_valid = new Auth();
		auth_valid.setToken(token);
		String connValidTokenResponse = HttpConnectionUtils.postRequest(url_validToken, auth_valid);
		auth_valid = (Auth) HttpConnectionUtils.jsonToObject(connValidTokenResponse, auth_valid.getClass());
		return auth_valid;
	}

	/** 토큰 유효 시간 검증*/
	public Auth isExpiredToken(String token) throws Exception {
		
		String url_checkExpire = "http://commerce-auth-svc/rest/auth/checkexpire";
		Auth auth_expired = new Auth();
		auth_expired.setToken(token);
		String connCheckExpireResponse = HttpConnectionUtils.postRequest(url_checkExpire, auth_expired);
		auth_expired = (Auth) HttpConnectionUtils.jsonToObject(connCheckExpireResponse, auth_expired.getClass());
		return auth_expired;
	}

	/** 사용자 고유ID 검색 */
	public Auth searchPersonId(Auth auth) throws Exception {
		
		String url = "http://commerce-auth-svc/rest/auth/personinfo";
		String personIdResponse = HttpConnectionUtils.postRequest(url, auth);
		auth = (Auth) HttpConnectionUtils.jsonToObject(personIdResponse, auth.getClass());
		return auth;
	}
}