package com.example.hungnt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements  Serializable {
    private String Email;
    private String UserDisplayName;
    private String Password;
    private String CheckPass;

    public UserDto(String email, String userDisplayName, String password) {
        Email = email;
        UserDisplayName = userDisplayName;
        Password = password;
    }
}
