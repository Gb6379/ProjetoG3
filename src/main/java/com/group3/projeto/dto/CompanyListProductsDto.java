package com.group3.projeto.dto;

import java.util.List;

public class CompanyListProductsDto {

    private List<CompanyProductDto> companyProductDtos;

    public CompanyListProductsDto(List<CompanyProductDto> companyProductsDtoList) {
        this.companyProductDtos = companyProductsDtoList;

    }

    public List<CompanyProductDto> getcartItems() {
        return companyProductDtos;
    }

    public void setCartItems(List<CartItemDto> cartItemDtoList) {
        this.companyProductDtos = companyProductDtos;
    }

}
