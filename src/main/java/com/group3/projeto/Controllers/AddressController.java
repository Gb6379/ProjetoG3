package com.group3.projeto.Controllers;


import com.group3.projeto.models.AddressModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.UserRepository;
import com.group3.projeto.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
//this annotation sets up the class contructos that would be necessary to initialize the UserService whithin the cointroller
@RequestMapping("/address")
@CrossOrigin(origins = "http://localhost:4200/")
public class AddressController {

    @Autowired
    private final AddressService addresService;
    @Autowired
    private final UserRepository userRepository;


    @GetMapping()
    public List<AddressModel> listAll(){
        return addresService.listAddresses();
    }
    @GetMapping("/{id}")
    public AddressModel getAddress(@PathVariable Long id){
        return addresService.getAddress(id);
    }

    @GetMapping("/{user_id}")
    public List<AddressModel> getUsersAddress(@PathVariable Long user_id){
        return addresService.getUsersAdresses(user_id);
    }

    @PostMapping("/user/{id}")
    public AddressModel save(@RequestBody AddressModel address, @PathVariable Long id){
        return addresService.saveAddres(address,id);
    }

    @PutMapping("/{id}")
    public AddressModel update(@RequestBody AddressModel address, @PathVariable Long id) {
        return addresService.updateAddress(address,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        addresService.deleteAddress(id);
    }


}
