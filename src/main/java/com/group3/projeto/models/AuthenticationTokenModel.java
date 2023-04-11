package com.group3.projeto.models;

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


    private String token;


    private Date createdDate;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    @ManyToOne(targetEntity = UserModel.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne(targetEntity = CompanyModel.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private CompanyModel company;

    private Date expirationDate;


}
