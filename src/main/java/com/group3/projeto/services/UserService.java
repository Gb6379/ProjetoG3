package com.group3.projeto.services;

import com.group3.projeto.exception.errors.UserExceptionNotFound;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public List<UserModel> listAll(){
        return userRepository.findAll();
    }

    public UserModel save(UserModel user){
        return userRepository.save(user);
    }

    public UserModel find(Long id){
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
