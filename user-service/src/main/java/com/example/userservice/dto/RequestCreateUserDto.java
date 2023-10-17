package com.example.userservice.dto;

import com.example.userservice.domain.User;
import jakarta.validation.constraints.*;
import lombok.*;

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
        return User.builder()
                .email(this.email)
                .encPw(this.pw)
                .userId(this.userId)
                .name(this.name)
                .uuid(UUID.randomUUID().toString())
                .createAt(LocalDateTime.now())
                .build();
    }
}
