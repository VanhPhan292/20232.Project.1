package com.example.hungnt.controller.navbar;

import com.example.hungnt.dto.ProfileDto;
import com.example.hungnt.dto.UserDto;
import com.example.hungnt.entity.Profile;
import com.example.hungnt.entity.User;
import com.example.hungnt.repository.ProfileRepository;
import com.example.hungnt.service.UserService;
import com.example.hungnt.service.ProfileService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@NoArgsConstructor
@AllArgsConstructor
@SessionAttributes("userdto")

public class ProfileController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileRepository profileRepository;
    @ModelAttribute("userdto")
    public UserDto userDto(){
        return new UserDto();
    }

    @ModelAttribute("profileuser")
    public ProfileDto profileDto(){
        return new ProfileDto();
    }

    @GetMapping("/profile")
    public String showprofile(@ModelAttribute("userdto")UserDto userDto, Model model, HttpSession session){
        User user = userService.getUserbyEmail(userDto.getEmail());
        if(session.getAttribute("user")==null){//
            return "redirect:/login";//
        }//
        if(user ==null){
            return "redirect:/login";
        }
        model.addAttribute("user",user);
        Profile profile = profileRepository.findProfileByUser(user);
        model.addAttribute("profile",profile);
        return "profile";
    }

    @PostMapping("/profile/{id}")
    public String getprofile(@SessionAttribute("userdto")UserDto userDto,
                             @PathVariable String id ,
                             Model model,
                             @ModelAttribute("profile") ProfileDto profileDto){
        Profile profileupdate = profileRepository.findProfileById(Integer.parseInt(id));
        profileupdate.setAdress(profileDto.getAdress());
        profileupdate.setDateofBirth(profileDto.getDateofBirth());
        profileupdate.setGender(profileDto.getGender());
        profileupdate.setFirstName(profileDto.getFirstName());
        profileupdate.setLastName(profileDto.getLastName());
        profileService.update(profileupdate);
        return "redirect:/profile";
    }

}
