package com.group3.projeto.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pagamento")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int cardNumber;

    private String cardName;

    private int cvv;

    private int validateDate;

    private boolean payOnDelivery;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private UserModel user;
}
