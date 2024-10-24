package com.springbookserver.service.interfaces;

import com.springbookserver.dto.reques.UserRequestDto;
import com.springbookserver.dto.response.CartResponseDto;
import com.springbookserver.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto getById(Long id);
    UserResponseDto create(UserRequestDto userRequestDto);
    UserResponseDto update(Long id, String currentPassword, UserRequestDto userRequestDto);
    void deleteUser(Long id);
    CartResponseDto getUsersCartById(Long id);

}
