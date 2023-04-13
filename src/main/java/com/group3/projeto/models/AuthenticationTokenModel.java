package com.group3.projeto.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.group3.projeto.enums.TokenType;
import com.group3.projeto.repositories.CompanyRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tokens")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationTokenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    @JsonBackReference(value="userToken-reference")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserModel user;

    @JsonBackReference(value="companyToken-reference")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    public CompanyModel company;

    public boolean revoked;

    public boolean expired;


}
