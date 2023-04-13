package com.group3.projeto.repositories;

import com.group3.projeto.models.AuthenticationTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationTokenModel,Long> {

    List<AuthenticationTokenModel> findAllValidTokenByUser(Integer id);

    Optional<AuthenticationTokenModel> findByToken(String token);
}
