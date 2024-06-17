package com.example.hungnt.controller.user_login_logout_registration;

import com.example.hungnt.entity.User;
import com.example.hungnt.dto.UserDto;
import com.example.hungnt.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
public class UserLoginController {
    private UserService userService;

    @ModelAttribute("userdto")
    public UserDto userDto(){
        return new UserDto();
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "/login";
    }

    @PostMapping("/login")
    public String Login(@ModelAttribute("userdto") UserDto userDto, Model model, HttpSession session){
        if(!userService.checkUserbyEmail(userDto.getEmail())){
            return "redirect:/login?emailwrong";
        }
        User user = userService.getUserbyEmail(userDto.getEmail());

        if(userService.checkPasswordUser(userDto.getEmail(), userDto.getPassword())){

            session.setAttribute("user", user);

            return "redirect:/home?success";
        }

        return "redirect:/login?passwordwrong";
    }
}
