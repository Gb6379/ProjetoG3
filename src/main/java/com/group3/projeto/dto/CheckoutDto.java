package com.group3.projeto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutDto {

    private String productName;
    private int  quantity;
    private double total;
    private long productId;
    private long userId;
    private String currency;
    private String method;
    private String intent;
}
