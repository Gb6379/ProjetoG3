package com.group3.projeto.repositories;

import com.group3.projeto.models.CompanyModel;
import com.group3.projeto.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyModel,Long> {

    public Optional<CompanyModel> findByEmail(String email);
}

