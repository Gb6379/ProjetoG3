package com.group3.projeto.Controllers;

import com.group3.projeto.models.UserModel;
import com.group3.projeto.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor//this annotation sets up the class contructos that would be necessary to initialize the UserService whithin the cointroller
@RequestMapping("/users")
//@CrossOrigin(origins = "http://localhost:9090")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/users")
    public List<UserModel> listAll() {
        return userService.listUsers();
    }

    @GetMapping("/{id}")
    public UserModel findById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @PostMapping()
    public UserModel save(@RequestBody UserModel user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public UserModel update(@RequestBody UserModel user, @PathVariable Long id){
        return userService.update(user,id);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
}
