package com.group3.projeto.repositories;

import com.group3.projeto.models.AuthenticationTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthenticationTokenModel,Long> {

    public AuthenticationTokenModel findTokenByUser(Long user_id);
    public AuthenticationTokenModel findTokenByToken(String token);
}
