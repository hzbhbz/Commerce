//package com.digital.config;
//
//import java.util.Arrays;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//
//import com.digital.schema.Auth;
//import com.digital.utils.HttpConnectionUtils;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Component
//public class AuthInterceptor implements HandlerInterceptor {
//	
////	@Resource
////	AuthContext authContext;
//
//	public static List<String> authEssential = Arrays.asList("/api/**");
//	public static List<String> authInessential = Arrays.asList("/api/person/**");
//
//	public void addInterceptors(InterceptorRegistry registry) {
//
//		// 인증 관련 인터셉터 추가
//		registry.addInterceptor(new AuthInterceptor()).addPathPatterns(AuthInterceptor.authEssential)
//				.excludePathPatterns(AuthInterceptor.authInessential); // 로그인, 회원가입 검증 X
//	}
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//
//		String token = request.getHeader("Authorization");
//		
//		/** 토큰 유효성 검증 */
////		Auth auth_vaild = new Auth();
////		auth_vaild= authContext.isValiedToken(token);
//		Auth authValid = new Auth();
//		authValid.setToken(token);
//		
//		String validTokenUrl = "http://localhost:9001/rest/auth/validtoken";
//		String validTokenResponse = HttpConnectionUtils.postRequest(validTokenUrl, authValid);
//		authValid = (Auth) HttpConnectionUtils.jsonToObject(validTokenResponse, authValid.getClass());
//		if(!authValid.isValidToken()) {
//			response.setContentType("application/json");
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().write("{\"errorCode\":\"401\",\"errorMsg\":\"Token이 없습니다.\"}");
//			return false;
//		}
//
//		/** 토큰 유효 시간 검증*/
////		Auth auth_expired = new Auth();
////		auth_expired = authContext.isExpiredToken(token);
//		Auth authExpire = new Auth();
//		authExpire.setToken(token);
//		
//		String checkExpireUrl = "http://localhost:9001/rest/auth/checkexpire";
//		String checkExpireResponse = HttpConnectionUtils.postRequest(checkExpireUrl, authExpire);
//		authExpire = (Auth) HttpConnectionUtils.jsonToObject(checkExpireResponse, authExpire.getClass());
//		
//		if (!authExpire.isExpiredToken()) {
//			// 30분
//			response.getWriter().write("{\"errorCode\":\"402\",\"errorMsg\":\"로그아웃 되었습니다.\"}");
//			return false;
//		}
//
//		return true;
//	}
//
//}
