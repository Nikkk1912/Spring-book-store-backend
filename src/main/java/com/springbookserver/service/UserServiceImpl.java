package com.springbookserver.service;

import com.springbookserver.dao.interfaces.CartDao;
import com.springbookserver.dao.interfaces.UserDao;
import com.springbookserver.dto.reques.UserRequestDto;
import com.springbookserver.dto.response.CartResponseDto;
import com.springbookserver.dto.response.UserResponseDto;
import com.springbookserver.exeption_handling.exceptions.IncorrectPasswordException;
import com.springbookserver.model.User;
import com.springbookserver.service.interfaces.UserService;
import com.springbookserver.utils.DtoMapper;
import com.springbookserver.utils.PasswordHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private CartDao cartDao;

    @Override
    public UserResponseDto getById(Long id) {
        return DtoMapper.userToDto(userDao.getById(id));
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        User userSave = userDao.save(new User(userRequestDto.getUsername(), userRequestDto.getEmail(), PasswordHandler.encodePassword(userRequestDto.getPassword())));
        return DtoMapper.userToDto(userSave);
    }

    @Override
    public UserResponseDto update(Long id, String currentPassword , UserRequestDto userRequestDto) {
        User user = userDao.getById(id);

        if(PasswordHandler.checkPassword(currentPassword, user.getPassword())) {
            user.setEmail(userRequestDto.getEmail());
            user.setUsername(userRequestDto.getUsername());
            if (userRequestDto.getPassword() != null) user.setPassword(PasswordHandler.encodePassword(userRequestDto.getPassword()));
        } else throw new IncorrectPasswordException();

        userDao.update(user);
        return DtoMapper.userToDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.delete(id);
    }

    @Override
    public CartResponseDto getUsersCartById(Long id) {
        return DtoMapper.cartToDto(cartDao.getById(id));
    }
}