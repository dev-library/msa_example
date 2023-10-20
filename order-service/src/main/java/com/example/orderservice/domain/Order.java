package com.example.orderservice.domain;

import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Builder @AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false) // 주문코드도 당연히 불변
    private String orderId;

    private Long count;

    @CreatedDate
    @Column(updatable = false) // 가입시간은 불변
    private LocalDateTime createAt;

    private String userId;

    private String productId;

}
