package com.group3.projeto.Controllers;

import com.group3.projeto.models.CompanyModel;
import com.group3.projeto.models.ProductModel;
import com.group3.projeto.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
@CrossOrigin(origins = "http://localhost:4200/")
public class CompanyController {

    private final CompanyService companyService;
    @GetMapping("/products/{company_id}")
    public List<ProductModel> getProducts(@PathVariable Long company_id){
        return companyService.getCompanyProducts(company_id);
    }

    @GetMapping
    public List<CompanyModel> getCompanies(){
        return companyService.listCompanies();
    }


}
