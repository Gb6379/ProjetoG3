package com.group3.projeto.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "funcao")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @OneToOne(mappedBy = "role")
    UserModel user;

}
