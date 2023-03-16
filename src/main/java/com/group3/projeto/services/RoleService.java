package com.group3.projeto.services;

import com.group3.projeto.models.RoleModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.RoleRepository;
import com.group3.projeto.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    public RoleModel save(RoleModel role){
        return roleRepository.save(role);
    }

    /*public UserModel setRole(Long userId,Long roleId){//work this method around
        Optional<RoleModel> roleEntity = roleRepository.findById(roleId);
        return userRepository.findById(userId).map(record -> {
            record.setRole(roleEntity.get());
            return
        })
        Optional<RoleModel> roleEntity = roleRepository.findById(roleId);
        Optional<UserModel>user = userRepository.findById(userId);
        user.get().setRole(roleEntity.get());
        return userRepository.save(user);

    }*/
}
