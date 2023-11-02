package com.example.orderservice.service;

import com.example.orderservice.domain.Order;
import com.example.orderservice.dto.RequestCreateOrderDto;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(RequestCreateOrderDto requestCreateOrderDto){
        Order order = requestCreateOrderDto.toEntity();
        orderRepository.save(order);
    }
    // userId를 입력하면 Order리스트를 넘겨주는 메서드 호출을 통해 리턴까지 해주세요.
    public Optional<List<Order>> getOrderListByUserId(String userId){
        return orderRepository.findOrderByUserId(userId);
    }
    public Optional<List<Order>> getOrderListByProductId(String productId){
        return orderRepository.findOrdersByProductId(productId);
    }

}
