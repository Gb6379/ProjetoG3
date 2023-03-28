package com.group3.projeto.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GeneratedColumn;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
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

    private String productName;

    @Column(name = "created_date")
    private Date createdDate;

    @JsonBackReference(value="cartProd-reference")
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductModel product;

    @JsonIgnore
    @OneToOne(targetEntity = UserModel.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserModel user;

    private int quantity;


    public CartModel(ProductModel product, int quantity, UserModel user) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.createdDate = new Date();
    }
}
