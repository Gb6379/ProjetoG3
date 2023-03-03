package com.group3.projeto.models;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addres_id", referencedColumnName = "id")
    AddressModel address;

    //carrinho relationship
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    CartModel cart;
}
