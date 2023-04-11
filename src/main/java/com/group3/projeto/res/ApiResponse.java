package com.group3.projeto.res;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private  boolean success;
    private String message;
}
