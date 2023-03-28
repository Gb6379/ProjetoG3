package com.group3.projeto.Controllers;

import com.group3.projeto.dto.AddCartDto;
import com.group3.projeto.models.CartModel;
import com.group3.projeto.models.ProductModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.services.CartService;
import com.group3.projeto.services.ProductService;
import com.group3.projeto.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
//this annotation sets up the class contructos that would be necessary to initialize the UserService whithin the cointroller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private final CartService cartService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final ProductService productService;

    @GetMapping
    public List<CartModel> list(){
        return cartService.ListCarts();
    }

    @GetMapping("/{id}")
    public CartModel getCartByUserId(@PathVariable Long id){
        return cartService.getCartProduct(id);
    }



    @PostMapping("/{product_id}/{user_id}")
    public CartModel save(@PathVariable Long product_id,@PathVariable Long user_id){//this endpoint is not used, because the addProduct does its job when a product is added to users cart
        return cartService.saveCart(product_id,user_id);
    }

    @PutMapping("/{cart_id}")
    public CartModel update(@RequestBody AddCartDto cartDto, @PathVariable Long cart_id){
        UserModel user = userService.findUserById(cartDto.getUserId());
        ProductModel product = productService.getProduct(cartDto.getProductId());
        return cartService.updateCart(cartDto, product, user, cart_id);
    }

    @PostMapping("/create")
    public String addCart(@RequestBody AddCartDto addCartDto){//set the item's amount that's gonna be add and diminsh from its total amount
        UserModel user = userService.findUserById(addCartDto.getUserId());
        ProductModel product = productService.getProduct(addCartDto.getProductId());
        return cartService.addCart(addCartDto, product, user);
    }

    @DeleteMapping("/{product_id}/{cart_id}")
    public void delete(@PathVariable Long product_id, @PathVariable Long cart_id){
        cartService.deleteCart(product_id,cart_id);
    }

    @DeleteMapping
    public void deleteAll(){
        cartService.deleteAll();
    }
}
