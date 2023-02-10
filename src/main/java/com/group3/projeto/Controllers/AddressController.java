package com.group3.projeto.Controllers;


import com.group3.projeto.models.AddressModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.UserRepository;
import com.group3.projeto.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
//this annotation sets up the class contructos that would be necessary to initialize the UserService whithin the cointroller
@RequestMapping("/address")
public class AddressController {

    private final AddressService addresService;
    private final UserRepository userRepository;


    @GetMapping()
    public List<AddressModel> listAll(){
        return addresService.listAddresses();
    }

    @PutMapping("/{id}")
    public AddressModel save(@RequestBody AddressModel address, @PathVariable Long id){

        return addresService.saveAddres(address,id);
    }
}
