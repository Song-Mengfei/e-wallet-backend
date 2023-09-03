package com.ewallet.springbootewallet.controller;

import com.ewallet.springbootewallet.domain.User;
import com.ewallet.springbootewallet.service.UserService;
import com.ewallet.springbootewallet.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<User> loginController(@RequestParam String uname, @RequestParam String password) {
        User user = userService.loginService(uname, password);
        if (user != null) {
            return Result.success(user, "login success");
        } else {
            return Result.error("1", "incorrect username or password");
        }
    }

    @PostMapping("/register")
    public Result<User> registerController(@RequestBody User newUser) {
        User user = userService.registerService(newUser);
        if (user != null) {
            return Result.success(user, "register success");
        } else {
            return Result.error("2", "User already exist");
        }
    }
}
