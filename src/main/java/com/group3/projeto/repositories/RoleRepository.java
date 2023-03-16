package com.group3.projeto.repositories;

import com.group3.projeto.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    public RoleModel findByDescricao(String descricao);

}
