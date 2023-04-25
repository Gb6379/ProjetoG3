package com.group3.projeto.repositories;

import com.group3.projeto.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel,Long> {

    List<AddressModel> findByUserId(Long user_Id);
}
