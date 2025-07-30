package com.hemanth.wallet.service.dto;

import lombok.Data;

@Data
public class BalanceResponseDto {
    private Long userId;
    private String phoneNumber;
    private Long balance;
}

