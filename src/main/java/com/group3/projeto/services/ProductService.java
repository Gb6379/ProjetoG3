package com.group3.projeto.services;

import com.group3.projeto.dto.CompanyListProductsDto;
import com.group3.projeto.models.CategoryModel;
import com.group3.projeto.models.ProductModel;
import com.group3.projeto.repositories.AuthRepository;
import com.group3.projeto.repositories.CategoryRepository;
import com.group3.projeto.repositories.CompanyRepository;
import com.group3.projeto.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {


    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private  final CategoryRepository categoryRepository;

    private final JwtService jwtService;

    private final CompanyRepository companyRepository;



    public List<ProductModel> listProducts(){
        return productRepository.findAll();
    }

    public ProductModel getProduct(Long id){
        return productRepository.findById(id).orElseThrow();
    }

    public ProductModel saveProductandCategory(ProductModel product,Long id, Long company_id){
        var company = companyRepository.findById(company_id).get();
        product.setCompany(company);
        Optional<CategoryModel> category = categoryRepository.findById(id);
        product.setCategory(category.get());
        return productRepository.save(product);
    }

    public ProductModel onlySaveProduct(ProductModel product, Long company_id){
        var company = companyRepository.findById(company_id).get();
        product.setCompany(company);
        return productRepository.save(product);
    }

    public List<ProductModel> addCategory(String product_name, Long category_id){
        Optional<CategoryModel> category = categoryRepository.findById(category_id);
        List<ProductModel> product = productRepository.findByName(product_name);
        product.forEach(p -> {
            p.setCategory(category.get());
            productRepository.save(p);
        });
       //product.setCategory(category.get());
        return product;
    }

    public ProductModel updateProduct(ProductModel product, Long id){
        return productRepository.findById(id).map(record -> {
            record.setAmount(product.getAmount());
            record.setName(product.getName());
            record.setPrice(product.getPrice());
            record.setImageUrl(product.getImageUrl());
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
