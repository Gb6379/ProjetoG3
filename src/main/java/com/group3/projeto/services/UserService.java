package com.group3.projeto.services;

import com.group3.projeto.exception.errors.UserExceptionNotFound;
import com.group3.projeto.models.RoleModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.RoleRepository;
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
public class UserService {

   @Autowired
    private final UserRepository userRepository;

   @Autowired
   private final RoleRepository roleRepository;

   @Autowired
   private final AddressService addressService;

    public List<UserModel> listUsers(){
        return userRepository.findAll();
    }

    public UserModel save(UserModel user){//do the same thing as in addressService, but it would be better to have a specific methos to set user roles
        return userRepository.save(user);
    }

    public UserModel setRole(Long id){

     UserModel user = userRepository.findById(id).get();
     RoleModel role = roleRepository.findByDescricao("admin");
     user.addRole(role);

     return userRepository.save(user);

    }

    public UserModel update(UserModel user, Long id){
        return userRepository.findById(id)
                .map(record -> {
                    record.setEmail(user.getEmail());
                    record.setCpf(user.getCpf());
                    record.setPhone(user.getPhone());
                    return userRepository.save(record);
                }).orElseGet(() ->{
                   return userRepository.save(user);
                });
    }

    public UserModel findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserExceptionNotFound("Id não encontrado"));
    }

    public void delete(Long id){
        UserModel user = this.findUserById(id);
        //user.setRole(null);
        userRepository.deleteById(id);
        }



}
