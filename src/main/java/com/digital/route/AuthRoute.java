package com.digital.route;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digital.apigateway.AuthContext;
import com.digital.schema.Auth;
import com.digital.schema.ErrorMsg;
import com.digital.utils.ExceptionUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "인증", description = "Auth Related API")
@RequestMapping(value = "/api/auth")

public class AuthRoute {
	
	@Resource
	AuthContext authContext;
	
	@RequestMapping(value = "/personinfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "사용자 ID 확인", notes = "토큰 정보를 이용하여 사용자 ID를 확인한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = Auth.class),
		@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> getPersonId (@Parameter(name = "사용자 ID", required = false)@RequestBody Auth auth) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		Auth authRes = new Auth();
		try {
			authRes = authContext.searchPersonId(auth);
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Auth>(authRes, header, HttpStatus.valueOf(200));
	}

}
