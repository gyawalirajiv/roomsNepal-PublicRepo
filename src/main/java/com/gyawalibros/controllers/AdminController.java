package com.gyawalibros.controllers;

import com.gyawalibros.Service.UserService;
import com.gyawalibros.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;


//    @GetMapping("/user/list")
//    public String usersList(Model model){
//        List<com.gyawalibros.domain.User> userList = userService.getAllUsers();
//        model.addAttribute("users", userList);
//        return "admin/user/list";
//    }

    @GetMapping("/user/viewuserlist")
    public String viewUser(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "admin/user/viewuserlist";
    }

    @GetMapping("/user/viewlist")
    public String getView() {
        return "admin/user/viewlist";
    }


    @GetMapping("/user/viewdetail/{id}")
    public String viewUserDetail(@PathVariable Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "admin/user/viewdetail";
    }

    @GetMapping("/user/update/{id}")
    public String updateUser(@PathVariable Long id, Model model) {
        User user = userService.getUser(id);
        System.out.println(user.getFullName());
        model.addAttribute("user", user);
        return "admin/user/edit";
    }

    @PostMapping("/user/update")
    public String updateUserPost(@ModelAttribute User user) {
        user.setActive(true);
        userService.updateUser(user);
        return "redirect:/admin/user/viewuserlist";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        userService.deactivate(user);
        return "redirect:/admin/user/viewuserlist";
    }
}
