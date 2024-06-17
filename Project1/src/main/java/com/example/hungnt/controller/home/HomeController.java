package com.example.hungnt.controller.home;


import com.example.hungnt.dto.UserDto;

import com.example.hungnt.entity.User;
import com.example.hungnt.model.FileInfo;
import com.example.hungnt.service.FilesStorageService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
public class HomeController {
    @Autowired
    FilesStorageService storageService;

    @ModelAttribute("userdto")
    public UserDto userDto(){
        return new UserDto();
    }

    @GetMapping("/home")
    public String showHomeForm(@ModelAttribute("userdto") UserDto userDto, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user==null) {//
            return "redirect:/login";//
        }//
        List<FileInfo> fileInfos = storageService.loadAll(user).map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(HomeController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        model.addAttribute("files", fileInfos);
        return "/home";
    }

    @GetMapping("/")
    public String homepage(HttpSession session) {
        if(session.getAttribute("user")==null) {//
            return "redirect:/login";//
        }//
        return "redirect:/home";
    }

    @GetMapping("/home/new")
    public String newFile(Model model, HttpSession session) {
        if(session.getAttribute("user")==null) {//
            return "redirect:/login";//
        }//
        return "upload_form";
    }

    @PostMapping("/home/upload")
    public String uploadFile(Model model, @RequestParam("file") MultipartFile file) {
        String message = "";

        try {
            storageService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            model.addAttribute("message", message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            model.addAttribute("message", message);
        }

        return "upload_form";
    }

    @GetMapping("/home/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) { //*

        Resource file = storageService.load(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/home/delete/{filename:.+}")
    public String deleteFile(@PathVariable String filename, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        if(session.getAttribute("user")==null) {//
            return "redirect:/login";//
        }//

        try {
            boolean existed = storageService.delete(filename);

            if (existed) {
                redirectAttributes.addFlashAttribute("message", "Delete the file successfully: " + filename);
            } else {
                redirectAttributes.addFlashAttribute("message", "The file does not exist!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message",
                    "Could not delete the file: " + filename + ". Error: " + e.getMessage());
        }

        return "redirect:/home";
    }
}
