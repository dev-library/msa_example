package com.example.itemservice.service;

import com.example.itemservice.dto.RequestCreateItemDto;
import com.example.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void createItem(RequestCreateItemDto requestCreateItemDto){
        itemRepository.save(requestCreateItemDto.toEntity());
    }

}
