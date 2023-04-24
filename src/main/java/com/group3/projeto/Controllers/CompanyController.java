package com.group3.projeto.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
@CrossOrigin(origins = "http://localhost:4200/")
public class CompanyController {


}
