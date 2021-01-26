package com.gyawalibros.controllers;

import com.gyawalibros.Service.CloudinaryService;
import com.gyawalibros.Service.UserDetailsImpl;
import com.gyawalibros.Service.UserService;
import com.gyawalibros.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CloudinaryService cloudinaryService;

    @GetMapping("/user")
    public String userProfile(Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        User matchedUser = userService.getUserByEmail(user.getEmail());

        matchedUser.setPassword("***");
        matchedUser.setConfirmationToken("***");
        model.addAttribute("user", matchedUser);

        return "user/profile";
    }

    @GetMapping("/user/update")
    public String getUserUpdate(Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        User currentUser = new User();
        currentUser.setFullName(user.getFullName());
        currentUser.setPrimaryPhoneNumber(user.getPrimaryPhoneNumber());
        currentUser.setSecondaryPhoneNumber(user.getSecondaryPhoneNumber());
        currentUser.setAreaLocation(user.getAreaLocation());

        model.addAttribute("user", currentUser);
        return "user/update";
    }

    @GetMapping("/user/changepassword")
    public String changeUserPassword() {
        return "user/changePassword";
    }

    @PostMapping("/user/changepassword")
    public String postchangedPassword(Model model, HttpServletRequest httpServletRequest) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String oldPassword = httpServletRequest.getParameter("old-password");
        String newPassword = httpServletRequest.getParameter("new-password");
        String retypedNewPassword = httpServletRequest.getParameter("retype-new-password");

        if (!oldPassword.equals(userDetails.getPassword())) {
            String oldPasswordMessage = "The old password did not match!";
            model.addAttribute("oldPasswordMessage", oldPasswordMessage);
            return "user/changepassword";
        }

        if (!newPassword.equals(retypedNewPassword)) {
            String retypePasswordMessage = "The retyped password did not match!";
            model.addAttribute("retypePasswordMessage", retypePasswordMessage);
            return "user/changepassword";
        }

        if (newPassword.length() < 5 || newPassword.length() > 15) {
            String passwordSizeMessage = "Password must be between 5 and 15 characters!";
            model.addAttribute("passwordSizeMessage", passwordSizeMessage);
            return "user/changepassword";
        }

        userService.changePassword(userDetails, newPassword);

        return "redirect:/user";
    }


    @PostMapping("/user/update")
    public String postUserUpdate(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        for (FieldError fieldError :
                fieldErrors) {
            if (!fieldError.getField().equals("email") && !fieldError.getField().equals("password")) {
                model.addAttribute(user);
                return "/user/update";
            }
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDetails.getUser();
        user.setId(currentUser.getId());

        userService.updateUser(user);
        return "redirect:/user";
    }

    @PostMapping("/user/profileImageUpload")
    public String profileImageUpload(Model model, @RequestParam("image") MultipartFile images) throws IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        String fileURL = cloudinaryService.upload(images);
        userService.updateProfilePic(userDetails, fileURL);

        User matchedUser = userService.getUserByEmail(user.getEmail());
        String uploadMessage = "Uploaded Sucessfully!";
        model.addAttribute("uploadMessage", uploadMessage);
        model.addAttribute("user", matchedUser);
        System.out.println("UPLOADED");
        return "/user/profile";
    }
}
