package com.example.userservice.dto;

import com.example.userservice.domain.User;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder @ToString
public class RequestCreateUserDto {

    @Email
    private String email;

    @Size(min = 8, max = 20, message = "비밀번호는 최소 8글자이고, 최대 20글자입니다.")
    private String pw;

    @NotNull
    private String name;

    @NotNull
    private String userId;

    public User toEntity(){
        // 암호화 비번을 저장하기 위해 필요한 암호화 라이브러리
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return User.builder()
                .email(this.email)
                .encPw(bCryptPasswordEncoder.encode(this.pw))
                .userId(this.userId)
                .name(this.name)
                .uuid(UUID.randomUUID().toString())
                .createAt(LocalDateTime.now())
                .build();
    }
}
