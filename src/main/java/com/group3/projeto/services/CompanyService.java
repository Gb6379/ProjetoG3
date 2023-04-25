package com.group3.projeto.services;

import com.group3.projeto.models.CompanyModel;
import com.group3.projeto.models.ProductModel;
import com.group3.projeto.repositories.CompanyRepository;
import com.group3.projeto.repositories.ProductRepository;
import com.group3.projeto.res.CompanyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final ProductRepository productRepository;

    public List<CompanyModel> listCompanies(){
        return companyRepository.findAll();
    }

    public CompanyModel getCompany(Long company_id){
        return companyRepository.findById(company_id).get();
    }

    public List<ProductModel> getCompanyProducts(Long company_id){
        return productRepository.findByCompanyId(company_id);
    }

    public CompanyResponse update(CompanyModel company,Long company_id){
        return companyRepository.findById(company_id).map(record -> {
            record.setName(company.getName());
            record.setEmail(company.getEmail());
            record.setPhone(company.getPhone());
            record.setCnpj(company.getCnpj());
            companyRepository.save(record);
            return CompanyResponse.builder().message("empresa atualizada").build();
        }).orElseGet(()->{
            return CompanyResponse.builder().message("algo deu errado").build();
        });
    }

    public void delete(Long company_id){
        companyRepository.deleteById(company_id);
    }
}
