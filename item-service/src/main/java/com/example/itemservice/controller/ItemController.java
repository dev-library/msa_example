package com.example.itemservice.controller;

import com.example.itemservice.dto.RequestCreateItemDto;
import com.example.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("item-service")
@RequiredArgsConstructor
public class ItemController {

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


}