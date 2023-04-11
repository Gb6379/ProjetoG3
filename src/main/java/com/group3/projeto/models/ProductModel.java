package com.group3.projeto.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "produto")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private double price;

    private String imageUrl;

    private int amount;

    //categoria relationship
    @JsonBackReference(value="category-reference")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private CategoryModel category;

    //categoria relationship
    @JsonBackReference(value="companyP-reference")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private CompanyModel company;

    @JsonIgnore
    @JsonManagedReference(value="cartProd-reference")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<CartModel> carts;








}
