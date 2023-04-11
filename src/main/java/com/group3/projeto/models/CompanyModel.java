package com.group3.projeto.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group3.projeto.enums.Role;
import jakarta.persistence.*;
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
import java.util.List;

@Entity
@Table(name = "empresa")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String cnpj;

    private String password;

    private String email;

    private int phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonManagedReference(value="company-reference")
    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<AddressModel> addresses;

    @JsonManagedReference(value="companyP-reference")
    @OneToMany(mappedBy = "company")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ProductModel> product;

    @OneToMany(mappedBy = "company")
    private List<AuthenticationTokenModel> tokens;


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
