package com.group3.projeto.repositories;

import com.group3.projeto.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressModel,Long> {
}
