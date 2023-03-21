package com.group3.projeto.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //addres relationship
    @JsonBackReference(value="addressOrder-reference")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addres_id", referencedColumnName = "id")
    private AddressModel address;

    //carrinho relationship
    @JsonBackReference(value="cartOrder-reference")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private CartModel cart;
}
