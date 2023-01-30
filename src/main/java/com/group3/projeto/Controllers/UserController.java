package com.group3.projeto.Controllers;

import com.group3.projeto.models.UserModel;
import com.group3.projeto.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor//this annotation sets up the class contructos that would be necessary to initialize the UserService whithin the cointroller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserModel> listAll() {
        return userService.listAll();
    }

    @PostMapping()
    public UserModel save(@RequestBody UserModel user) {
        return userService.save(user);
    }
}
