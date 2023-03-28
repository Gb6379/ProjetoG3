package com.group3.projeto.repositories;

import com.group3.projeto.models.OrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemModel,Long> {
}
