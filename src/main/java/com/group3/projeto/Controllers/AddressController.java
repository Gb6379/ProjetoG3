package com.group3.projeto.Controllers;


import com.group3.projeto.models.AddressModel;
import com.group3.projeto.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//this annotation sets up the class contructos that would be necessary to initialize the UserService whithin the cointroller
@RequestMapping("/address")
public class AddressController {

    private final AddressService addresService;


    @GetMapping()
    public List<AddressModel> listAll(){
        return addresService.listAddresses();
    }

    @PostMapping()
    public AddressModel save(@RequestBody AddressModel address){
        return addresService.saveAddres(address);
    }
}
