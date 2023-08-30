package com.easypeach.shroop.modules.transaction.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easypeach.shroop.modules.auth.support.LoginMember;
import com.easypeach.shroop.modules.global.response.BasicResponse;
import com.easypeach.shroop.modules.member.domain.Member;
import com.easypeach.shroop.modules.transaction.dto.request.DeliveryRequest;
import com.easypeach.shroop.modules.transaction.service.DeliveryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/delivery")
public class DeliveryController {

	private final DeliveryService deliveryService;

	@PostMapping("/{productId}")
	public ResponseEntity<BasicResponse> saveDelivery(final @LoginMember Member member,
		final @PathVariable Long productId,
		final @RequestBody DeliveryRequest deliveryRequest) {

		deliveryService.saveDelivery(member.getId(), productId, deliveryRequest);

		return ResponseEntity.status(HttpStatus.OK).body(new BasicResponse("택배 등록이 완료되었습니다."));
	}
}
