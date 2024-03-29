package com.group3.projeto.request;

import com.group3.projeto.models.AddressModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;

    private  String lastname;

    private String cpf;

    private String cnpj;

    private String phone;

    private String email;

    private String password;
}
