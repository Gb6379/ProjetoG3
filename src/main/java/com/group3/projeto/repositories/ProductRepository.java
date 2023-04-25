package com.group3.projeto.repositories;

import com.group3.projeto.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel,Long> {

    public List<ProductModel> findByName(String name);
    List<ProductModel> findByCompanyId(Long id);
}
