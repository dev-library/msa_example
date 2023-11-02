package com.example.userservice.service;

import com.example.userservice.domain.Order;
import com.example.userservice.domain.User;
import com.example.userservice.dto.RequestCreateUserDto;
import com.example.userservice.dto.ResponseFindUserDto;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.feignclient.OrderFeignClient;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final OrderFeignClient orderFeignClient;

    // 회원가입
    public void createUser(RequestCreateUserDto userDto){
        // dto를 entity로 변경해주는 작업이 필요함
        User user = userDto.toEntity();
        userRepository.save(user);
    }

    public ResponseFindUserDto findUserByUuid(String uuid){
        User user = userRepository.findUserByUuid(uuid);

        if(user == null)
            throw new UserNotFoundException("해당 유저는 존재하지 않습니다.");
        ResponseFindUserDto dto = new ResponseFindUserDto(user);
        dto.setOrderList(List.of());
        return dto;
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public ResponseFindUserDto findUserOrderList(String userId){
        // 1. 특정 유저 아이디를 가져옵니다.
        User user = userRepository.findUserByUserId(userId);
        ResponseFindUserDto userDto = new ResponseFindUserDto(user);
        log.info("아이디 기반으로 유저 정보 얻어오기 : ", userDto);
        // 2. feign 클라이언트를 이용해서 특정 유저의 구매목록을 가져옵니다.
        List<Order> orderList = orderFeignClient.getOrderListByUserId(userId);
        log.info("아이디 기반으로 주문 정보 얻어오기 : ", orderList);
        // 3. 세터로 합쳐줍니다.
        userDto.setOrderList(orderList);
        log.info("세터로 합쳐졌는지 확인하기 : ", userDto);
        // 4. 합쳐준걸 리턴해주세요.
        return userDto;
    }


}
