package com.group3.projeto.repositories;

import com.group3.projeto.models.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartModel,Long> {

    //public CartModel findByTipo(String descricao);
}
