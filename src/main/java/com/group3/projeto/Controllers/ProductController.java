package com.group3.projeto.Controllers;

import com.group3.projeto.models.ProductModel;
import com.group3.projeto.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductService productService;


    @GetMapping
    public List<ProductModel> listAll(){
        return productService.listProducts();
    }

    @GetMapping("/{id}")
    public ProductModel getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }

    @PostMapping("/category/{id}")
    public ProductModel save(@RequestBody ProductModel product, @PathVariable Long category_id){
        return productService.saveProduct(product, category_id);
    }

    @PutMapping("/{id}")
    public ProductModel update(@RequestBody ProductModel product, @PathVariable Long id){
        return productService.updateProduct(product,id);
    }

    @PutMapping("/{product_name}/{category_id}")//not working
    public List<ProductModel> addCategory(@PathVariable String product_name, @PathVariable Long category_id){
        return productService.addCategory(product_name,category_id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.deleteProduct(id);
    }

}
