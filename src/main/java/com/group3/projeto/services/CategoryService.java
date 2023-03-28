package com.group3.projeto.services;

import com.group3.projeto.exception.errors.AddressExceptionNotFound;
import com.group3.projeto.exception.errors.CategoryExceptionNotFound;
import com.group3.projeto.models.CategoryModel;
import com.group3.projeto.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public List<CategoryModel> listCtegories(){
        return categoryRepository.findAll();
    }

    public CategoryModel getCategory(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryExceptionNotFound("Categoria nao existe"));
    }

    public CategoryModel saveCategory(CategoryModel category){
        return categoryRepository.save(category);
    }

    public CategoryModel updateCategory(CategoryModel category, Long id){
            return categoryRepository.findById(id).map(record -> {
                record.setCategoryName(category.getCategoryName());
                record.setDescription(category.getDescription());
                return categoryRepository.save(record);
            }).orElseGet( () -> {
                    return categoryRepository.save(category);
            });
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
