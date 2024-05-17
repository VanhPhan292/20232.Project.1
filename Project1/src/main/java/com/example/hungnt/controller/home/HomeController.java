package com.example.hungnt.controller.home;

import com.example.hungnt.dto.UserDto;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
public class HomeController {
    @ModelAttribute("userdto")
    public UserDto userDto(){
        return new UserDto();
    }

    @GetMapping("/home")
    public String showHomeForm(@ModelAttribute("userdto") UserDto userDto, Model model){
        return "/home";
    }
}
