package com.easypeach.shroop.modules.transaction.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreateRequest {
	private String buyerName;
	private String buyerPhoneNumber;
	private String buyerLocation;
}
