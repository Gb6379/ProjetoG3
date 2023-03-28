package com.group3.projeto.repositories;

import com.group3.projeto.models.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CartRepository extends JpaRepository<CartModel,Long> {

    public List<CartModel> findByUserId(Long userId);
    public CartModel findByProductId(Long productId);

    //public String fincProductByName(String name);



}
