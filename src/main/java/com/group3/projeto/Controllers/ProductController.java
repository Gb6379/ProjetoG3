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

    @PostMapping
    public ProductModel save(@RequestBody ProductModel product){
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public ProductModel update(@RequestBody ProductModel product, Long id){
        return productService.updateProduct(product,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.deleteProduct(id);
    }

}
