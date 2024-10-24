package com.springbookserver.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private CartResponseDto cartResponseDto;
}
