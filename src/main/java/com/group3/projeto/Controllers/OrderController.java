package com.group3.projeto.Controllers;

import com.group3.projeto.models.OrderModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.UserRepository;
import com.group3.projeto.services.JwtService;
import com.group3.projeto.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final UserRepository userRepository;

    @GetMapping
    public List<OrderModel> listOrder(@PathVariable Long user_id){
        return orderService.listOrders(user_id);
    }
}
