package com.group3.projeto.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.group3.projeto.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;
    @Email
    private String email;

    private String password;

    private int cpf;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

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

    @OneToMany(mappedBy = "user")
    private List<AuthenticationTokenModel> tokens;

    @JsonIgnore
    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<OrderModel> orders;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
