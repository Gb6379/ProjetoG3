package com.group3.projeto.services;

import com.group3.projeto.enums.Role;
import com.group3.projeto.models.AuthenticationTokenModel;
import com.group3.projeto.models.CompanyModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.AuthRepository;
import com.group3.projeto.repositories.CompanyRepository;
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

    private final UserRepository userRepository;

    private final CompanyRepository companyRepository;//idk

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final AuthRepository authRepository;

    public AuthResponse register(RegisterRequest request){//do a register only for company or verify on the request if it cotains cpf or cnpj
        if(request.getCpf() == null){//idk
            var existentCompany = companyRepository.findByEmail(request.getEmail()); //idk
            var company = CompanyModel.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .cnpj(request.getCnpj())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.COMPANY)
                    .build();
            var savedComapny = companyRepository.save(company);
            var jwtToken = jwtService.generateTokenWithNoExtraClaims(savedComapny);
            saveCompanyToken(savedComapny,jwtToken);
            return AuthResponse.builder().token(jwtToken).build();

        }else {
            var existentUser = userRepository.findByEmail(request.getEmail());
            if (existentUser.isPresent()) {
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


            var savedUser = userRepository.save(user);

            var jwtToken = jwtService.generateTokenWithNoExtraClaims(user);//if I pass company here that implemnts user detaisl it should work

            saveUserToken(savedUser, jwtToken);
            return AuthResponse.builder().token(jwtToken).build();
        }
    }

    public AuthResponse authenticateUser(AuthenticationRequest request){//do the login for company such as register was done to be dinamic with the 2 kinds
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateTokenWithNoExtraClaims(user);
        saveUserToken(user, jwtToken);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthResponse authenticateCompany(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),request.getPassword())
        );

        var company = companyRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateTokenWithNoExtraClaims(company);
        saveCompanyToken(company,jwtToken);
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

    public void saveCompanyToken(CompanyModel company, String jwtToken){

        var expirationDate = jwtService.extractExpirationDate(jwtToken);
        var issuetaAt = jwtService.extractIssuedAt(jwtToken);

        AuthenticationTokenModel authModel = new AuthenticationTokenModel();
        authModel.setCompany(company);
        authModel.setToken(jwtToken);
        authModel.setCreatedDate(issuetaAt);
        authModel.setExpirationDate(expirationDate);
        authRepository.save(authModel);

    }


}
