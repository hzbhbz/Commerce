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

import com.digital.apigateway.PhoneContext;
import com.digital.schema.ErrorMsg;
import com.digital.schema.Phone;
import com.digital.utils.ExceptionUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "전화번호", description = "Phone Related API")
@RequestMapping(value = "/api/phone")

public class PhoneRoute {

	@Resource
	PhoneContext phoneContext;

	@RequestMapping(value = "/detailinfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "전화번호 정보", notes = "PhoneNumber 찾기 위한 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Phone.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> phoneNumberDetailInfo(@Parameter(name = "전화번호 정보", required = true) @RequestBody Phone phone) throws Exception {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		Phone phoneRes = new Phone();
		try {
			// PhoneId Routing (PhoneNumber 찾기)
			phoneRes = phoneContext.searchPhoneByID(phone);
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Phone>(phoneRes, header, HttpStatus.valueOf(200));
	}

	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "전화번호 ID 정보", notes = "PhoneID 찾기 위한 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Phone.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> phoneNumberInfo(@Parameter(name = "전화번호 ID정보", required = true) @RequestBody Phone phone) throws Exception {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		Phone phoneRes = new Phone();
		try {
			// PhoneId Routing (PhoneID 찾기)
			phoneRes = phoneContext.searchPhoneByDetail(phone);
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Phone>(phoneRes, header, HttpStatus.valueOf(200));
	}
}
