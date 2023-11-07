package com.example.itemservice.controller;

import com.example.itemservice.dto.RequestCreateItemDto;
import com.example.itemservice.dto.ResponseOrderByItemDto;
import com.example.itemservice.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("item-service")
@RequiredArgsConstructor
public class ItemController {

    private final Environment env;

    private final ItemService itemService;

    // 직렬화, 역직렬화 담당 라이브러리.
    private final ObjectMapper objectMapper;



    @GetMapping("health-check")
    public String healthCheck(){
        return "item-service service is available.";
    }

    @PostMapping("items")
    public ResponseEntity<?> createItem(@Valid @RequestBody RequestCreateItemDto requestCreateItemDto)
        throws JsonProcessingException {
        //메시지 큐로만 전달하면 됨.
        itemService.publishCreateItemMessage(requestCreateItemDto);
        //itemService.createItem(requestCreateItemDto);
        return ResponseEntity.ok("메시지큐에 생성 요청 적재 완료!");
    }

    @GetMapping("profile-check")
    public String profileCheck(){
        return env.getProperty("pro.file");
    }

    @GetMapping("items/{productId}/orders")
    public ResponseEntity<?> getOrdersByProductId(@PathVariable String productId){
        ResponseOrderByItemDto dto = itemService.findOrderByItem(productId);
        return ResponseEntity.ok(dto);
    }

    //PathVariable을 이용해서 message를 큐에 적재할수있도록
    // 엔드포인트를 직접 설정해주세요.
    @GetMapping("items/{message}/message")
    public ResponseEntity<?> publishTestMessage(@PathVariable String message) {
        itemService.publishTestMessage(message);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
