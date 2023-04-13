package com.group3.projeto.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "orderItem")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;


    @Column(name = "created_date")
    private Date createdDate;

    @JsonBackReference(value="orderItem-reference")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderModel order;


    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private ProductModel product;

}
