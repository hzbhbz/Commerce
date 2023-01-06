package com.digital.route;

import javax.annotation.Resource;

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

import com.digital.apigateway.InventoryContext;
import com.digital.schema.ErrorMsg;
import com.digital.schema.Inventory;
import com.digital.utils.ExceptionUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "재고", description = "Inventory Related API")
@RequestMapping(value = "/api/inventory")

public class InventoryRoute {

	@Resource
	InventoryContext inventoryContext;

	@RequestMapping(value = "/quantity/inquiry/{keyword}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "재고 수량 검색", notes = "상품명을 입력해 재고 수량을 검색하는 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Inventory.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> searchQuantity(@PathVariable String keyword) throws Exception {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		Inventory inventoryRes = new Inventory();
		try {
			// Inventory Routing
			inventoryRes = inventoryContext.searchInventory(keyword);
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Inventory>(inventoryRes, header, HttpStatus.valueOf(200)); // ResponseEntity를 활용한 응답 생성
	}

	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "재고 정보", notes = "상품 ID를 입력해 재고 정보를 가져오는 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Inventory.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> searchInventoryInfo(
			@Parameter(name = "재고 정보", required = true) @RequestBody Inventory inventory) throws Exception {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		Inventory inventoryRes = new Inventory();
		try {
			// Inventory Routing
			inventoryRes = inventoryContext.searchInventoryById(inventory);
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Inventory>(inventoryRes, header, HttpStatus.valueOf(200)); // ResponseEntity를 활용한 응답 생성
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "재고 수량 등록", notes = "재고 수량을 관리하는 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Inventory.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })

	public ResponseEntity<?> insert(@Parameter(name = "재고 등록", required = true) @RequestBody Inventory inventory)
			throws Exception {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		Inventory inventoryRes = new Inventory();
		try {
			// Inventory Routing
			if (inventoryContext.insertInventory(inventory)) {
				inventoryRes = inventoryContext.searchInventoryById(inventory);
			}
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Inventory>(inventoryRes, header, HttpStatus.valueOf(200));// ResponseEntity를 활용한 응답
																								// 생성
	}
}
