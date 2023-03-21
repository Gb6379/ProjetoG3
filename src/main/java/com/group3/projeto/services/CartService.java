package com.group3.projeto.services;

import com.group3.projeto.models.CartModel;
import com.group3.projeto.models.ProductModel;
import com.group3.projeto.repositories.CartRepository;
import com.group3.projeto.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final ProductRepository productRepository;

    public List<CartModel> ListCarts(){
        return cartRepository.findAll();
    }

    public CartModel getCart(Long id){
        return cartRepository.findById(id).orElseThrow();
    }

    public CartModel saveCart(CartModel cart){
        return cartRepository.save(cart);
    }

    public CartModel updateCart(CartModel cart, Long id){
        return cartRepository.findById(id)
                .map(record -> {
            record.setUser(cart.getUser());
            record.setOrder(cart.getOrder());
            record.setProducts(cart.getProducts());
            return cartRepository.save(record);
        }).orElseGet(() -> {
            return cartRepository.save(cart);
        });
    }

    public CartModel addToCart(Long productId, Long cartId){
        ProductModel product = productRepository.findById(productId).get();
        CartModel cart = cartRepository.findById(cartId).get();
        cart.addProduct(product);

        return cartRepository.save(cart);

    }

    public void deleteCart(Long id){
        cartRepository.deleteById(id);
    }
}
