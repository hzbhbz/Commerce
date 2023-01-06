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

import com.digital.apigateway.AddressContext;
import com.digital.schema.Address;
import com.digital.schema.ErrorMsg;
import com.digital.schema.Phone;
import com.digital.utils.ExceptionUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "주소", description = "Address Related API")
@RequestMapping(value = "/api/address")

public class AddressRoute {

	@Resource
	AddressContext addressContext;
	
	@RequestMapping(value = "/detailinfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "주소 정보", notes = "AddressDetail 찾기 위한 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Phone.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> addressDetailInfo(@Parameter(name = "주소 정보", required = true) @RequestBody Address address)
			throws Exception {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		Address addresRes = new Address();
		try {
			addresRes = addressContext.searchAddressById(address);
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Address>(addresRes, header, HttpStatus.valueOf(200));
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "주소 ID 정보", notes = "AddressID 찾기 위한 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Phone.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> addressIdInfo(@Parameter(name = "주소 ID 정보", required = true) @RequestBody Address address)
			throws Exception {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		Address addresRes = new Address();
		try {
			addresRes = addressContext.searchAddressByDetail(address);
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Address>(addresRes, header, HttpStatus.valueOf(200));
	}
}
