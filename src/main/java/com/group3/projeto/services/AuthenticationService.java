package com.group3.projeto.services;

import com.group3.projeto.enums.Role;
import com.group3.projeto.models.AuthenticationTokenModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.AuthRepository;
import com.group3.projeto.repositories.UserRepository;
import com.group3.projeto.request.AuthenticationRequest;
import com.group3.projeto.request.RegisterRequest;
import com.group3.projeto.res.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final AuthRepository authRepository;

    public AuthResponse register(RegisterRequest request){
        var existentUser = userRepository.findByEmail(request.getEmail());
        if(existentUser.isPresent()){
            //throw new RuntimeException("usuario já existe");
            return AuthResponse.builder().message("Usuario já existe")
                    .build();
        }
        var user = UserModel.builder()
                .firstName(request.getName())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateTokenWithNoExtraClaims(user);
        saveUserToken(user,jwtToken);
        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateTokenWithNoExtraClaims(user);

        saveUserToken(user,jwtToken);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }


    public void saveUserToken(UserModel user, String jwtToken){

        var expirationDate = jwtService.extractExpirationDate(jwtToken);
        var issuetaAt = jwtService.extractIssuedAt(jwtToken);

        AuthenticationTokenModel authModel = new AuthenticationTokenModel();
        authModel.setUser(user);
        authModel.setToken(jwtToken);
        authModel.setCreatedDate(issuetaAt);
        authModel.setExpirationDate(expirationDate);
        authRepository.save(authModel);

    }


}
