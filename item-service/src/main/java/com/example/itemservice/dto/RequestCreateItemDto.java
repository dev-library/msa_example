package com.example.itemservice.dto;

import com.example.itemservice.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter @AllArgsConstructor
@NoArgsConstructor
public class RequestCreateItemDto {

    private String productName;
    private Long stock;
    private Long pricePerItem;

    public Item toEntity(){
        return Item.builder()
                .productId(UUID.randomUUID().toString())
                .stock(this.stock)
                .productName(this.productName)
                .pricePerItem(this.pricePerItem)
                .build();
    }
}
