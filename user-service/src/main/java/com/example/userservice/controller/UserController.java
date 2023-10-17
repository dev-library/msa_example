package com.example.userservice.controller;

import com.example.userservice.domain.User;
import com.example.userservice.dto.RequestCreateUserDto;
import com.example.userservice.dto.ResponseFindUserDto;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-service")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("health-check")
    public String healthCheck(){
        return "server is available!";
    }

    @PostMapping("users")
    public ResponseEntity<?> createUser(@Valid @RequestBody RequestCreateUserDto userDto){
        userService.createUser(userDto);
        return ResponseEntity.ok("회원 가입 완료!");
    }

    // 가입된 계정을 uuid기반으로 찾아올 수 있도록 get요청을 만들어주세요.
    // users/{uuid} 로 조회했을때 해당하는 User의 엔터티 객체를 받아옵니다.
    @GetMapping("users/{uuid}")
    public ResponseEntity<?> findUserByUuid(@PathVariable String uuid){
        ResponseFindUserDto userDto = userService.findUserByUuid(uuid);
        return ResponseEntity.ok(userDto);
    }
}
