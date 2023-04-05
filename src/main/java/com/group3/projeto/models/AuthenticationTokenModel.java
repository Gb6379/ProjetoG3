package com.group3.projeto.models;

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

    @OneToOne(targetEntity = UserModel.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserModel user;

    private Date expirationDate;


}
