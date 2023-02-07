package com.group3.projeto.services;

import com.group3.projeto.models.AddressModel;
import com.group3.projeto.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {

    private final AddressRepository addressRepository;

    public List<AddressModel> listAddresses(){
        return addressRepository.findAll();
    }

    public AddressModel saveAddres(AddressModel address){
        return addressRepository.save(address);
    }
}
