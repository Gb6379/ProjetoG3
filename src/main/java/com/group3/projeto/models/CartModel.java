package com.group3.projeto.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "carrinho")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    @JsonBackReference(value="cartUser-reference")
    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    @JsonManagedReference(value="cartOrder-reference")
    @OneToOne(mappedBy = "cart")
    private OrderModel order;

    @JsonBackReference(value="role-reference")
    @ManyToMany
    @JoinTable(name = "cart_proudcts",
            joinColumns = @JoinColumn(name="cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<ProductModel> products = new HashSet<>();

    public void addProduct(ProductModel product){
        this.products.add(product);
    }

}
