package com.group3.projeto.services;

import com.group3.projeto.dto.AddCartDto;
import com.group3.projeto.models.CartModel;
import com.group3.projeto.models.ProductModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.CartRepository;
import com.group3.projeto.repositories.ProductRepository;
import com.group3.projeto.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final UserRepository userRepository;

    public List<CartModel> ListCarts(){
        return cartRepository.findAll();
    }

    public CartModel getCartProduct(Long id){
        CartModel cart = cartRepository.findById(id).get();
        return cart;
    }


    public CartModel saveCart(Long productId,Long userId){
        UserModel user = userRepository.findById(userId).get();
        ProductModel product = productRepository.findById(productId).get();

        CartModel newCart = new CartModel();
        int newamountAdd = newCart.getQuantity();
        newamountAdd = newamountAdd + 1;
        Date date = new Date();
        newCart.setCreatedDate(date);
        newCart.setProductName(product.getName());
        newCart.setQuantity(newamountAdd);
        newCart.setUser(user);
        newCart.setProduct(product);

        return cartRepository.save(newCart);
    }

    public CartModel updateCart(AddCartDto cartDto, ProductModel product, UserModel user, Long cart_id){
        int amount = product.getAmount();
        amount = amount - cartDto.getQuantity();
        product.setAmount(amount);
        productRepository.save(product);
        CartModel cartmodel = cartRepository.findById(cart_id).get();
        cartmodel.setQuantity(cartDto.getQuantity());
        cartmodel.setCreatedDate(new Date());
        return cartRepository.save(cartmodel);
    }

    public String addCart(AddCartDto addCartDto, ProductModel product, UserModel user){
        int amount = product.getAmount();
        amount = amount - addCartDto.getQuantity();
        product.setAmount(amount);
        productRepository.save(product);
        CartModel cart = new CartModel(product,addCartDto.getQuantity(),user);
        cart.setProductName(product.getName());
        cartRepository.save(cart);

        return "Produto adicionado com sucesso";
    }

    public void deleteCart(Long productId, Long cartId){
        ProductModel product = productRepository.findById(productId).get();
        CartModel cart = cartRepository.findById(cartId).get();
        int p_amount = product.getAmount();

        p_amount = p_amount + 1;
        product.setAmount(p_amount);
        productRepository.save(product);

        int amount = cart.getQuantity();
        if(amount == 1){
            cartRepository.deleteById(cartId);
        }else {
            amount = amount - 1;

            cart.setQuantity(amount);
            cartRepository.save(cart);
        }
           // }
        //cartRepository.deleteById(cartId);
    }

    public void deleteAll(){
        cartRepository.deleteAll();
    }
}
