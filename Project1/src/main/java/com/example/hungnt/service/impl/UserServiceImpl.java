package com.example.hungnt.service.impl;

import com.example.hungnt.dto.UserDto;
import com.example.hungnt.entity.User;
import com.example.hungnt.repository.UserRepository;
import com.example.hungnt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(UserDto userDto) {
        LocalDateTime Date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String creationDate = Date.format(formatter);
        User user = new User(userDto.getEmail(),
                userDto.getUserDisplayName(),
                userDto.getPassword(),
                creationDate,
                "ROLE_USER");
        userRepository.save(user);
    }

    @Override
    public Boolean checkPasswordUser(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        return user.getPassword().equals(password);
    }

    @Override
    public Boolean checkUserbyEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return user != null;
    }

    @Override
    public User getUserbyEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
