package com.group3.projeto.services;

import com.group3.projeto.exception.errors.AddressExceptionNotFound;
import com.group3.projeto.models.AddressModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.AddressRepository;
import com.group3.projeto.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {

    @Autowired
    private final AddressRepository addressRepository;
    @Autowired
    private final UserRepository userRepository;

    public List<AddressModel> listAddresses(){
        return addressRepository.findAll();
    }

    public AddressModel getAddress(Long id){
        return addressRepository.findById(id).orElseThrow(() -> new AddressExceptionNotFound("Endereço não existe"));//treat the especific address exception
    }

    public AddressModel saveAddres(AddressModel address, Long id){
        Optional<UserModel> userEntity = userRepository.findById(id);
        address.setUser(userEntity.get());
        return addressRepository.save(address);
    }

    public AddressModel updateAddress(AddressModel address , Long id){
        return addressRepository.findById(id)
                .map(record -> {
            record.setStreet(address.getStreet());
            record.setNeighborhood(address.getNeighborhood());
            record.setCep(address.getCep());
            record.setCity(address.getCity());
            record.setState(address.getState());
            return addressRepository.save(record);
        }).orElseGet(() -> {
            return addressRepository.save(address);
        });
    }

    public void deleteAddress(Long id){
        addressRepository.deleteById(id);
    }

    public void deleteAll(){
        addressRepository.deleteAll();
    }
}
