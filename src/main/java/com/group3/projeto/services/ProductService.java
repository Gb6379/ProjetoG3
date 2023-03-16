package com.group3.projeto.services;

import com.group3.projeto.models.ProductModel;
import com.group3.projeto.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {


    @Autowired
    private final ProductRepository productRepository;

    public List<ProductModel> listProducts(){
        return productRepository.findAll();
    }

    public ProductModel getProduct(Long id){
        return productRepository.findById(id).orElseThrow();
    }

    public ProductModel saveProduct(ProductModel product){
        return productRepository.save(product);
    }

    public ProductModel updateProduct(ProductModel product, Long id){
        return productRepository.findById(id).map(record -> {
            record.setAmount(product.getAmount());
            record.setName(product.getName());
            record.setValue(product.getValue());
            record.setCategory(product.getCategory());
            return productRepository.save(record);
        }).orElseGet(() -> {
            return productRepository.save(product);
        });
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
