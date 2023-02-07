package com.group3.projeto.services;

import com.group3.projeto.exception.errors.UserExceptionNotFound;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public List<UserModel> listUsers(){
        return userRepository.findAll();
    }

    public UserModel save(UserModel user){
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
        try {
            userRepository.deleteById(id);
        } catch (UserExceptionNotFound e){
            throw new UserExceptionNotFound("id nao localizado");

        }

    }


}
