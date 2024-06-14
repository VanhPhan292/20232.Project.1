package com.example.hungnt.service.impl;

import com.example.hungnt.dto.UserDto;
import com.example.hungnt.entity.User;
import com.example.hungnt.repository.UserRepository;
import com.example.hungnt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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
        User iden = userRepository.save(user);
        createDir(iden);
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

    public void createDir(User user){
        String directoryName = user.getUserDisplayName() + "_id=" + user.getID();

        // Address of Current Directory
        String currentDirectory = System.getProperty("user.dir");

        // Specify the path of the directory to be created
        String directoryPath = currentDirectory + File.separator + directoryName;

        // Create a File object representing the directory
        File directory = new File(directoryPath);

        // Attempt to create the directory
        boolean directoryCreated = directory.mkdir();

        if (directoryCreated) {
            System.out.println("Directory created successfully at: " + directoryPath);
        } else {
            System.out.println("Failed to create directory. It may already exist at: " + directoryPath);
        }
    }
}
