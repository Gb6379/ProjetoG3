package com.group3.projeto.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "cart")
    @JsonIgnore
    private List<ProductModel> products;

    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserModel user;

    @OneToOne(mappedBy = "cart")
    OrderModel order;

}
