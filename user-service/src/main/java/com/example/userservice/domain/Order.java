package com.example.userservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder @ToString
public class Order {

    private Long id;

    private String orderId;

    private Long count;

    private LocalDateTime createAt;

    private String userId;

    private String productId;

}
