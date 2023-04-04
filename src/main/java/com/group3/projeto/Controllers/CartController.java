package com.group3.projeto.Controllers;

import com.group3.projeto.dto.AddCartDto;
import com.group3.projeto.dto.CartListDto;
import com.group3.projeto.models.CartModel;
import com.group3.projeto.models.ProductModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.res.ApiResponse;
import com.group3.projeto.services.CartService;
import com.group3.projeto.services.ProductService;
import com.group3.projeto.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{user_id}")
    public ResponseEntity<CartListDto> getCartByUserId(@PathVariable Long user_id){
        CartListDto cart = cartService.getCartProducts(user_id);
        return new ResponseEntity<CartListDto>(cart, HttpStatus.OK);
    }

    @PutMapping("/{cart_id}")
    public CartModel update(@RequestBody AddCartDto cartDto, @PathVariable Long cart_id){
        UserModel user = userService.findUserById(cartDto.getUserId());
        ProductModel product = productService.getProduct(cartDto.getProductId());
        return cartService.updateCart(cartDto, product, user, cart_id);
    }

    @PostMapping("/add")
    public String addCart(@Valid @RequestBody AddCartDto addCartDto){//set the item's amount that's gonna be add and diminsh from its total amount
        UserModel user = userService.findUserById(addCartDto.getUserId());
        ProductModel product = productService.getProduct(addCartDto.getProductId());
        return cartService.addCart(addCartDto, product, user);
    }

    @PutMapping("/addItem/{user_id}/{product_id}")
    public ResponseEntity<String> addItem(@PathVariable Long user_id, @PathVariable Long product_id){
         cartService.addItem(user_id, product_id);
        return new ResponseEntity<String>("Item adicionado",HttpStatus.OK);
    }


    @DeleteMapping("/removeItem/{user_id}/{product_id}")
    public String removeItem(@PathVariable Long user_id, @PathVariable Long product_id){
        return cartService.deleteItem(user_id,product_id);
    }

    @DeleteMapping("/emptyCart/{user_id}")
    public String deleteCartItems(@PathVariable Long user_id){
        return cartService.deleteItems(user_id);
    }

    @DeleteMapping
    public void deleteAll(){
        cartService.deleteAll();
    }
}
