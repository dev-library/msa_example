package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.RequestCreateUserDto;
import com.example.userservice.dto.ResponseFindUserDto;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public void createUser(RequestCreateUserDto userDto){
        // dto를 entity로 변경해주는 작업이 필요함
        User user = userDto.toEntity();
        userRepository.save(user);
    }

    public ResponseFindUserDto findUserByUuid(String uuid){
        User user = userRepository.findUserByUuid(uuid);
        return new ResponseFindUserDto(user);
    }


}
