package com.example.userservice.dto;

import com.example.userservice.domain.User;
import lombok.*;

@Getter @Setter @AllArgsConstructor
@NoArgsConstructor @Builder @ToString
public class ResponseFindUserDto {

    private String uuid;

    private Long id;

    private String email;

    private String name;

    private String userId;

    public ResponseFindUserDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.userId = user.getUserId();
        this.uuid = user.getUuid();
    }
}
