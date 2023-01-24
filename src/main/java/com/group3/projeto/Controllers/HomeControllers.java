package com.group3.projeto.Controllers;


import com.group3.projeto.services.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeControllers {

    private final HomeService homeService;


}
