package com.group3.projeto.dto;

import com.group3.projeto.models.CompanyModel;
import com.group3.projeto.models.ProductModel;

public class CompanyProductDto {

    private Long id;

    private ProductModel product;

    public CompanyProductDto() {

    }

    public CompanyProductDto(CompanyModel company){
        //this.setId()

    }


    @Override
    public String toString(){
        return "CompanyProductDto{" +
                "id=" + id +
                "productName=" + product.getName() +
                ", productPrice" + product.getPrice() +
                ", productImage" + product.getImageUrl() +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }


}
