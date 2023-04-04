package com.group3.projeto.services;

import com.group3.projeto.dto.AddCartDto;
import com.group3.projeto.dto.CartItemDto;
import com.group3.projeto.dto.CartListDto;
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

    public CartListDto getCartProducts(Long user_id){
        List<CartModel> userCartItems = cartRepository.findByUserId(user_id);
        List<CartItemDto> cartItems = new ArrayList<>();
        userCartItems.forEach(item ->{
             CartItemDto cartItemDto = getDtoFromCart(item);
             cartItems.add(cartItemDto);
        });
        int totalCost = 0;
        for(CartItemDto cartItemDto: cartItems){
            totalCost += (cartItemDto.getProduct().getPrice()* cartItemDto.getQuantity());
        }
        return new CartListDto(cartItems,totalCost);
    }

    public static CartItemDto getDtoFromCart(CartModel cart) {
        return new CartItemDto(cart);
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
        if(amount == 0){
            return "produto fora do estoque";//do exception
        }
        amount = amount - addCartDto.getQuantity();
        product.setAmount(amount);
        productRepository.save(product);
        CartModel cart = new CartModel(product,addCartDto.getQuantity(),user);
        cart.setProductName(product.getName());
        cartRepository.save(cart);
        return "created";
    }


    public void addItem(Long user_id, Long product_id){
        List<CartModel> userCartItems = cartRepository.findByUserId(user_id);//if this is empty create a cart
        ProductModel product = productRepository.findById(product_id).get();
       // boolean containsProduct = userCartItems.contains(product.getCarts().get(0).getId());

        //if(containsProduct == false) //wordk this out and make a way to creat a cart in case user add an item thres no cart in the db
            userCartItems.forEach(item -> {
                if (item.getProduct().getId() == product_id) {
                   /* if(product.getAmount() == 0){
                        throw new RuntimeException("Produto fora de estoque");
                    }*/
                    CartModel cart = cartRepository.findById(item.getId()).get();
                    //ProductModel product = productRepository.findById(product_id).get();//get product from id table
                    int quantity = cart.getQuantity() + 1;//get item cart quantity and dimish one from it
                    cart.setQuantity(quantity);
                    int amount = product.getAmount() - 1;//return the item to the product original amount, in other words add up the item back to stock
                    product.setAmount(amount);
                    cartRepository.save(cart);
                    productRepository.save(product);
                }
            });

    }

    public String deleteItems(Long user_id){
        List<CartModel> userCartItems = cartRepository.findByUserId(user_id);
        userCartItems.forEach(item -> {
            cartRepository.deleteById(item.getId());

            ProductModel product = productRepository.findById(item.getProduct().getId()).get();
            int quantity = item.getQuantity();
            int amount = product.getAmount();
            product.setAmount(amount + quantity);
            productRepository.save(product);
        });
        return "carrinho vazio";
    }

    public String deleteItem(Long user_id,Long product_id){
        List<CartModel> userCartItems = cartRepository.findByUserId(user_id);
        userCartItems.forEach(item -> {
            if(item.getProduct().getId() == product_id) {
                CartModel cart = cartRepository.findById(item.getId()).get();
                ProductModel product = productRepository.findById(product_id).get();//get product from id table
                int quantity = cart.getQuantity() - 1;//get item cart quantity and dimish one from it
                cart.setQuantity(quantity);
                int amount = product.getAmount() + 1;//return the item to the product original amount, in other words add up the item back to stock
                product.setAmount(amount);
                cartRepository.save(cart);
                productRepository.save(product);
                if (item.getQuantity() == 0) {
                    cartRepository.deleteById(cart.getId());
                }
            } else {
                return;
            }
        });
        return "item excluido";

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
