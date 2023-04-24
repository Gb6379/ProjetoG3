package com.group3.projeto.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.projeto.enums.Role;
import com.group3.projeto.enums.TokenType;
import com.group3.projeto.models.AuthenticationTokenModel;
import com.group3.projeto.models.CompanyModel;
import com.group3.projeto.models.UserModel;
import com.group3.projeto.repositories.AddressRepository;
import com.group3.projeto.repositories.AuthRepository;
import com.group3.projeto.repositories.CompanyRepository;
import com.group3.projeto.repositories.UserRepository;
import com.group3.projeto.request.AuthenticationRequest;
import com.group3.projeto.request.RegisterRequest;
import com.group3.projeto.res.AuthResponse;
import com.group3.projeto.res.RefreshTokenResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
                    .phone(request.getPhone())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.COMPANY)
                    .build();
            var savedComapny = companyRepository.save(company);
            var jwtToken = jwtService.generateTokenWithNoExtraClaims(savedComapny);
            var refreshToken = jwtService.generateRefreshToken(company);
            saveCompanyToken(savedComapny,jwtToken);
            return AuthResponse
                    .builder()
                    .token(jwtToken)
                    .build();

        }else {
            var existentUser = userRepository.findByEmail(request.getEmail());
            if (existentUser.isPresent()) {
                throw new RuntimeException("usuario já existe");

            }
            var user = UserModel.builder()
                    .firstName(request.getName())
                    .lastName(request.getLastname())
                    .email(request.getEmail())
                    .cpf(request.getCpf())
                    .phone(request.getPhone())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();

            /*request.getAddressModel().setUser(user);
            addressRepository.save(request.getAddressModel());*/

            var savedUser = userRepository.save(user);

            var jwtToken = jwtService.generateTokenWithNoExtraClaims(user);//if I pass company here that implemnts user detaisl it should work

            var refreshToken = jwtService.generateRefreshToken(user);

            saveUserToken(savedUser, jwtToken);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
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
                .username(user.getFirstName())
                .userId(user.getId())
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

        var token = AuthenticationTokenModel.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        authRepository.save(token);

    }

    public void saveCompanyToken(CompanyModel company, String jwtToken){

        var token = AuthenticationTokenModel.builder()
                .company(company)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        authRepository.save(token);

    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUser(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateTokenWithNoExtraClaims(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = RefreshTokenResponse.builder()
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }

        }
    }

    private void revokeAllUserTokens(UserModel user) {
        var validUserTokens = authRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        authRepository.saveAll(validUserTokens);
    }

}
