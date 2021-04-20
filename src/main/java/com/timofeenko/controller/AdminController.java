package com.timofeenko.controller;

import com.timofeenko.model.Role;
import com.timofeenko.model.User;
import com.timofeenko.service.RoleService;
import com.timofeenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAllUsersForAdmin(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "list_of_users_for_admin";
    }

    @GetMapping("/user/new")
    public String addNewUser(@ModelAttribute User user, Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "new_user";
    }

    @PostMapping("/user/new")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("user/edit")
    public String editUser(@RequestParam int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit_user";
    }

    @PostMapping("user/edit")
    public String saveEditedUser(@ModelAttribute User user) {
        System.out.println("_______________User roles before update: " + user.getRoles());
//        user.setRoles(user.getRoles());
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("user/delete")
    public String deleteUser(@RequestParam int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

//    @PostMapping ("user/delete")
//    public String deleteUserUsigUserModel(@ModelAttribute User user) {
//        userService.deleteUserById(user.getId());
//        return "redirect:/admin";
//    }
}
