package com.example.hungnt.service;

import com.example.hungnt.dto.UserDto;
import com.example.hungnt.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User save(UserDto userDto);
    Boolean checkPasswordUser(String email, String password);
    Boolean checkUserbyEmail(String email);
    User getUserbyEmail(String email);
}
