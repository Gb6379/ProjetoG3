package com.group3.projeto.repositories;

import com.group3.projeto.models.HomeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<HomeModel,Long> {
    //metodos especificos para busca do objeto
}
