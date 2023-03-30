package com.group3.projeto.dto;

import com.group3.projeto.models.CartModel;
import com.group3.projeto.models.ProductModel;
import jakarta.validation.constraints.NotNull;

public class CartItemDto {
    private Long id;
    private @NotNull Integer quantity;
    private @NotNull ProductModel product;

    public CartItemDto() {
    }

    public CartItemDto(CartModel cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", productName=" + product.getName() +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

}