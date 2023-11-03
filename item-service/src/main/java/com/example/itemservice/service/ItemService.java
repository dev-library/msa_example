package com.example.itemservice.service;

import com.example.itemservice.domain.Item;
import com.example.itemservice.domain.Order;
import com.example.itemservice.dto.RequestCreateItemDto;
import com.example.itemservice.dto.ResponseOrderByItemDto;
import com.example.itemservice.feignclient.OrderFeignClient;
import com.example.itemservice.repository.ItemRepository;
import com.example.itemservice.util.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final OrderFeignClient orderFeignClient;
    private final Producer producer;

    public void createItem(RequestCreateItemDto requestCreateItemDto){
        itemRepository.save(requestCreateItemDto.toEntity());
    }

    public ResponseOrderByItemDto findOrderByItem(String productId){
        // 1. 특정 아이템을 가져옵니다.
        Item item = itemRepository.findItemByProductId(productId)
                        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이템은 없습니다"));
        // ResponseDTO 로 변경하는 코드 추가하기!!
        ResponseOrderByItemDto itemDto = new ResponseOrderByItemDto(item);
        // 2. feign 클라이언트를 이용해서 특정 유저의 구매목록을 가져옵니다.
        List<Order> orderList = orderFeignClient.getOrdersByItemId(productId);
        System.out.println("orderList : " + orderList);
        // 3. 세터로 합쳐줍니다.
        itemDto.setOrderList(orderList);
        // 4. 합쳐준걸 리턴해주세요.
        return itemDto;
    }

    public void publishTestMessage(String message){
        producer.sendTestMessage(message);
    }

}
