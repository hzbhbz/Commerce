//package com.digital.config;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//
//		// 인증 관련 인터셉터 추가
//		registry.addInterceptor(new AuthInterceptor()).addPathPatterns(AuthInterceptor.authEssential)
//				.excludePathPatterns(AuthInterceptor.authInessential); // 로그인, 회원가입 검증 X
//	}
//
//}
