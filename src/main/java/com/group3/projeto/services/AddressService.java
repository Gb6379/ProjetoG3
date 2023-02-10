package com.group3.projeto.services;

import com.group3.projeto.models.AddressModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.AddressRepository;
import com.group3.projeto.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public List<AddressModel> listAddresses(){
        return addressRepository.findAll();
    }

    public AddressModel saveAddres(AddressModel address, Long id){
        Optional<UserModel> userEntity = userRepository.findById(id);
        address.setUser(userEntity.get());
        return addressRepository.save(address);
    }
}
