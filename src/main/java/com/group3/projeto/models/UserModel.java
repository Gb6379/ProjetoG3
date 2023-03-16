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
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private int cpf;

    private String phone;

    @JsonManagedReference(value="address-reference")
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<AddressModel> addresses;

    @JsonManagedReference(value="user-reference")
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PaymentModel> payments;

    @JsonManagedReference(value="cartUser-reference")
    @OneToOne(mappedBy = "user")
    private CartModel cart;

    @JsonBackReference(value="role-reference")
    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<RoleModel> roles = new HashSet<>();

    public void addRole(RoleModel roles){
        this.roles.add(roles);
    }

}
