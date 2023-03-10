package com.group3.projeto.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    private int cpf;

    private String phone;

    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    RoleModel role;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<AddressModel> addresses;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<PaymentModel> payments;

    @OneToOne(mappedBy = "user")
    CartModel cart;







}
