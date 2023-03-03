package com.group3.projeto.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "empresa")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String cnpj;

    private int phone;

    @JsonManagedReference
    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<AddressModel> addresses;


}
