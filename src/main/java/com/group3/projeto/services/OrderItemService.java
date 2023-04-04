package com.group3.projeto.services;

import com.group3.projeto.models.OrderItemModel;
import com.group3.projeto.repositories.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void addOrderedProducts(OrderItemModel orderItem) {
        orderItemRepository.save(orderItem);
    }
}
