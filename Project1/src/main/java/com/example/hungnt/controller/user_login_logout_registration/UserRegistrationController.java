package com.example.hungnt.controller.user_login_logout_registration;

import com.example.hungnt.dto.UserDto;
import com.example.hungnt.entity.Profile;
import com.example.hungnt.entity.User;
import com.example.hungnt.service.ProfileService;
import com.example.hungnt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserRegistrationController {
    private UserService userService;
    private ProfileService profileService;

    @ModelAttribute("userdto")
    public UserDto userResgistrationDto(){
        return new UserDto();
    }

    @GetMapping("/registration")
    public String showRegistrationForm(){
        return "/registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("userdto") UserDto userDto){
        if(userService.checkUserbyEmail(userDto.getEmail())){
            return "redirect:/registration?emailexist";
        }
        if(!userDto.getPassword().equals(userDto.getCheckPass())){
            return "redirect:/registration?checkpass";
        }

        User newUser = userService.save(userDto);
        Profile profile = new Profile();
        profile.setUser(newUser);
        profileService.save(profile);
        return "redirect:/registration?success";
    }
}
