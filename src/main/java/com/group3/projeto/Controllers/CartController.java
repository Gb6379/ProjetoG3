package com.group3.projeto.Controllers;

import com.group3.projeto.models.CartModel;
import com.group3.projeto.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//this annotation sets up the class contructos that would be necessary to initialize the UserService whithin the cointroller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private final CartService cartService;

    @GetMapping
    public List<CartModel> list(){
        return cartService.ListCarts();
    }

    @GetMapping("/{id}")
    public CartModel getCart(@PathVariable Long id){
        return cartService.getCart(id);
    }

    @PostMapping
    public CartModel save(@RequestBody CartModel cart){
        return cartService.saveCart(cart);
    }

    @PutMapping
    public CartModel update(@RequestBody CartModel cart, Long id){
        return cartService.updateCart(cart,id);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id){
        cartService.deleteCart(id);
    }
}
