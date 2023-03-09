package com.group3.projeto.Controllers;

import com.group3.projeto.models.CategoryModel;
import com.group3.projeto.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//this annotation sets up the class contructos that would be necessary to initialize the UserService whithin the cointroller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public List<CategoryModel> listAll(){
        return categoryService.listCtegories();
    }

    @GetMapping("/{id}")
    public CategoryModel getCategory(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    @PostMapping
    public CategoryModel save(@RequestBody CategoryModel category){
        return categoryService.saveCategory(category);
    }

    @PutMapping("/{id}")
    public CategoryModel update(@RequestBody CategoryModel category, @PathVariable Long id){
        return categoryService.updateCategory(category,id);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id){
         categoryService.deleteCategory(id);
    }
}
