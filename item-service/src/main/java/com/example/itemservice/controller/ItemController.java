package com.example.itemservice.controller;

import com.example.itemservice.dto.RequestCreateItemDto;
import com.example.itemservice.dto.ResponseOrderByItemDto;
import com.example.itemservice.service.ItemService;
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

    @GetMapping("health-check")
    public String healthCheck(){
        return "item-service service is available.";
    }

    @PostMapping("items")
    public ResponseEntity<?> createItem(@RequestBody RequestCreateItemDto requestCreateItemDto){
        itemService.createItem(requestCreateItemDto);
        return new ResponseEntity(HttpStatus.CREATED);
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
