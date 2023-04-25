package com.group3.projeto.Controllers;

import com.group3.projeto.models.OrderModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.UserRepository;
import com.group3.projeto.res.ApiResponse;
import com.group3.projeto.res.AuthResponse;
import com.group3.projeto.services.JwtService;
import com.group3.projeto.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200/")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @GetMapping
    public List<OrderModel> listOrder(@PathVariable Long user_id){
        return orderService.listOrders(user_id);
    }

    @GetMapping("/{order_id}")
    public OrderModel getOrder(@PathVariable Long order_id){
        return orderService.getOrderById(order_id);
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<ApiResponse> saveOrder(@PathVariable Long user_id){
        return ResponseEntity.ok(orderService.placeOrder(user_id));
    }

}
