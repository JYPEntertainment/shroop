package com.easypeach.shroop.modules.transaction.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.easypeach.shroop.modules.common.ControllerTest;
import com.easypeach.shroop.modules.product.service.ProductService;
import com.easypeach.shroop.modules.transaction.dto.request.TransactionCreateRequest;
import com.easypeach.shroop.modules.transaction.dto.response.BuyerResponse;
import com.easypeach.shroop.modules.transaction.dto.response.TransactionCreatedResponse;
import com.easypeach.shroop.modules.transaction.dto.response.TransactionInfoResponse;
import com.easypeach.shroop.modules.transaction.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest extends ControllerTest {

	@MockBean
	TransactionService transactionService;

	@MockBean
	ProductService productService;

	@Test
	@DisplayName("결제창 테스트 코드")
	void getBuyingForm() throws Exception {

		TransactionInfoResponse dto = new TransactionInfoResponse("aaaa", "상품", 10000L, 20000L);

		given(transactionService.createTransactionInfoResponse(any(), any())).willReturn(dto);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/buying/1")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.productImgUrl").value("aaaa"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("상품"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(10000L))
			.andExpect(MockMvcResultMatchers.jsonPath("$.point").value(20000L));

	}

	@Test
	@DisplayName("결제하기 테스트 코드")
	void buyingProduct() throws Exception {
		doNothing().when(transactionService).createTransaction(any(), any(), any());

		ObjectMapper objectMapper = new ObjectMapper();
		TransactionCreateRequest transactionCreateRequest = new TransactionCreateRequest("이종만", "01050505050",
			"강서구에살아");
		String json = objectMapper.writeValueAsString(transactionCreateRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/buying/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
			.andExpect(status().isOk())
			.andDo(document("buying",
				responseFields(
					fieldWithPath("message").description("결제가 완료되었습니다.")
				)))
			.andDo(print());
	}

	@Test
	@DisplayName("결제 완료창 테스트 코드")
	void getBuyingCompletedForm() throws Exception {
		TransactionCreatedResponse transactionCreatedResponse = new TransactionCreatedResponse(1L, "url", "title",
			30000L, "이종문", "01050502222", "강서구에삽니다");

		given(transactionService.createTransactionCreatedResponse(any())).willReturn(transactionCreatedResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/buying/completed/1")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.transactionId").value(1L))
			.andExpect(MockMvcResultMatchers.jsonPath("$.productImgUrl").value("url"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.productTitle").value("title"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.productPrice").value(30000L))
			.andExpect(MockMvcResultMatchers.jsonPath("$.buyerName").value("이종문"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.buyerPhoneNumber").value("01050502222"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.buyerLocation").value("강서구에삽니다"))
			.andDo(print());

	}

	@DisplayName("구매자정보 조회")
	@Test
	void getBuyer() throws Exception {
		Long productId = 1L;
		BuyerResponse buyerResponse = new BuyerResponse("구매자 이름", "구매자 주소", "구매자 휴대번호");

		given(transactionService.getBuyer(productId)).willReturn(buyerResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/buying/buyer/{productId}", productId)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(document("buyer",
				responseFields(
					fieldWithPath("name").description("구매자 이름"),
					fieldWithPath("location").description("구매자 주소"),
					fieldWithPath("phone_number").description("구매자 휴대번호")
				)))
			.andDo(print());

	}
}