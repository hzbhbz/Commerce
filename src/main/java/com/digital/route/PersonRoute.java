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

import com.digital.apigateway.AuthContext;
import com.digital.apigateway.PersonContext;
import com.digital.schema.Auth;
import com.digital.schema.ErrorMsg;
import com.digital.schema.Person;
import com.digital.schema.SuccessMsg;
import com.digital.utils.ExceptionUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "고객", description = "Person Related API")
@RequestMapping(value = "/api/person")

public class PersonRoute {
	
	@Resource
	AuthContext authContext;
	@Resource
	PersonContext personContext;

	@RequestMapping(value = "/inquiry/{keyword}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "회원 검색", notes = "이름으로 회원가입 여부를 검색하는 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Person.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> personList(@PathVariable String keyword) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		Person personRes = new Person();
		ErrorMsg errors = new ErrorMsg();

		try {
			// search person routing
			personRes = personContext.searchPerson(keyword);
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Person>(personRes, header, HttpStatus.valueOf(200)); // ResponseEntity를 활용한 응답 생성
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "회원가입", notes = "회원가입을 위한 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Person.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> signUp(@Parameter(name = "회원가입을 위한 고객정보", required = true) @RequestBody Person person) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		Person personRes = new Person();
		try {
			// signup routing
			personRes = personContext.signUp(person);

		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Person>(personRes, header, HttpStatus.valueOf(200)); // ResponseEntity를 활용한 응답 생성
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "로그인", notes = "로그인을 위한 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Person.class),
			@ApiResponse(code = 201, message = "성공", response = SuccessMsg.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> login(@Parameter(name = "로그인 정보", required = true) @RequestBody Person person) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		SuccessMsg success = new SuccessMsg();
		try {
			// login routing
			person = personContext.login(person);
			
			// Token set routing
			Auth auth = new Auth();
			auth = authContext.setToken(person.getPersonId());

			success.setSuccessCode(200);
			success.setSuccessMsg("AccessToken :" + auth.getToken());

		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}

		return new ResponseEntity<SuccessMsg>(success, header, HttpStatus.valueOf(200)); // ResponseEntity를 활용한 응답 생성
	}

}
