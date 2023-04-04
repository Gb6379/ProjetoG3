package com.group3.projeto.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Entity
@Table(name = "Endereco")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String street;

    private String neighborhood;

    private String city;

    private String state;

    private String cep;

    @JsonBackReference(value="address-reference")
    @ManyToOne()
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private UserModel user;

    @JsonBackReference(value="company-reference")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private CompanyModel company;



}
