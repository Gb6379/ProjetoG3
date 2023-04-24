package com.group3.projeto.Controllers;

import com.group3.projeto.models.UserModel;
import com.group3.projeto.request.AuthenticationRequest;
import com.group3.projeto.request.RegisterRequest;
import com.group3.projeto.res.AuthResponse;
import com.group3.projeto.services.AuthenticationService;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse>register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/user/authenticate")
    public ResponseEntity<AuthResponse>authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticateUser(request));
    }

    @PostMapping("/company/authenticate")
    public ResponseEntity<AuthResponse>authenticate2(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticateCompany(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }
}
