package com.group3.projeto.services;

import com.group3.projeto.dto.CartItemDto;
import com.group3.projeto.dto.CartListDto;
import com.group3.projeto.models.OrderItemModel;
import com.group3.projeto.models.OrderModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.OrderItemRepository;
import com.group3.projeto.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    public List<OrderModel> listOrders(Long user_id) {
        return orderRepository.findByUserId(user_id);
    }

    public OrderModel getOrderById(Long order_id){
        return orderRepository.findById(order_id).get();
    }
    public void placeOrder(Long user_Id,String session_id){
        CartListDto cartList = cartService.getCartProducts(user_Id);
        UserModel user = userService.findUserById(user_Id);
        List<CartItemDto> cartItemDtoList = cartList.getcartItems();

        OrderModel newOrder =  new OrderModel();
        newOrder.setCreatedDate(new Date());
        newOrder.setUser(user);
        newOrder.setSessionId(session_id);
        newOrder.setTotalPrice(cartList.getTotalCost());
        orderRepository.save(newOrder);

        cartItemDtoList.forEach(item ->{

            OrderItemModel newOrderItem = new OrderItemModel();
            newOrderItem.setCreatedDate(new Date());
            newOrderItem.setPrice(item.getProduct().getPrice());
            newOrderItem.setProduct(item.getProduct());
            newOrderItem.setOrder(newOrder);
            newOrderItem.setQuantity(item.getQuantity());
            orderItemRepository.save(newOrderItem);

        });

        cartService.deleteItems(user_Id);

    }

    public List<OrderModel> listOrdersFromUser(Long user_id){
        return orderRepository.findByUserId(user_id);
    }




}
