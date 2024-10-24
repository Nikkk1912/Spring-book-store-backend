package com.springbookserver.dto.reques;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotBlank(message = "Username is mandatory")
    @Size(max = 255, message = "Username can`t exceed 255 characters")
    private String username;
    @NotBlank(message = "Email is mandatory")
    @Size(max = 255, message = "Email can`t exceed 255 characters")
    private String email;
    private String password;
}
