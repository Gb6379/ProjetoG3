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
@Table(name = "categoria")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String tipo;

    @JsonManagedReference(value="category-reference")
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<ProductModel> product;
}
