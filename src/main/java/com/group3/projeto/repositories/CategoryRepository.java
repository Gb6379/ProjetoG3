package com.group3.projeto.repositories;

import com.group3.projeto.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel,Long> {
}
