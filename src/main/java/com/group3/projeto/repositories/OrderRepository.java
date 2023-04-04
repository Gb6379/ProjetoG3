package com.group3.projeto.repositories;

import com.group3.projeto.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    public List<OrderModel> findByUserId(Long user_id);
}
