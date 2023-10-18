package com.example.itemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ResponseBuyItemDto {

    private String productId;

    private String productName;

    Long stock;

    Long pricePerItem;

    String userId;

    String orderId;
}
