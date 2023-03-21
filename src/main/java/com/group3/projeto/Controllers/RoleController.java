package com.group3.projeto.Controllers;

import com.group3.projeto.models.RoleModel;
import com.group3.projeto.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//this annotation sets up the class contructos that would be necessary to initialize the UserService whithin the cointroller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private final RoleService roleService;

    @PostMapping
    public RoleModel save(@RequestBody RoleModel role){
        return roleService.save(role);
    }
}
