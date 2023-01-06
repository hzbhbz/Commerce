package com.digital.route;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digital.apigateway.OrderContext;
import com.digital.schema.ErrorMsg;
import com.digital.schema.Order;
import com.digital.utils.ExceptionUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "주문", description = "Order Related API")
@RequestMapping(value = "/api/order")

public class OrderRoute {

	@Resource
	OrderContext orderContext;

	@RequestMapping(value = "/inquiry/{keyword}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "가주문서 검색", notes = "고객 ID 입력해 주문 내역을 검색하는 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Order.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> searchOrder(@PathVariable long keyword) throws Exception {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		Order orderRes = new Order();
		ErrorMsg errors = new ErrorMsg();

		try {
			// Order Info Routing
			orderRes = orderContext.searchOrder(keyword);
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Order>(orderRes, header, HttpStatus.valueOf(200)); // ResponseEntity를 활용한 응답 생성
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "가주문서 작성", notes = "상품을 주문하기 위한 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "주문이 완료되었습니다. 결제를 진행해주세요.", response = Order.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> writeOrder(@Parameter(name = "주문서", required = true) @RequestBody Order order, HttpServletRequest request) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		Order orderRes = new Order();
		try {
			String token = request.getHeader("Authorization");
			
			// Order Insert Routing
			orderRes = orderContext.insertOrder(order, token);
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Order>(orderRes, header, HttpStatus.valueOf(200)); // ResponseEntity를 활용한 응답 생성
	}

}
