package com.hemanth.wallet.service.assembler;

import com.hemanth.wallet.service.dto.WalletTransactionResponseDto;
import com.hemanth.wallet.web.dto.response.WalletBalanceResponseResource;
import org.springframework.stereotype.Component;

@Component
public class BalanceAssembler {
    public WalletBalanceResponseResource getTransactionBalanceRequestResource(WalletTransactionResponseDto transactions) {
        WalletBalanceResponseResource response = new WalletBalanceResponseResource();
        if(transactions == null) {
            return response;

        }
        response.setId(transactions.getId());
        response.setAmount(transactions.getAmount());
        response.setPaymentMethod(transactions.getPaymentMethod());
        response.setStatus(transactions.getStatus());
        response.setType(transactions.getType());

        return response;
    }
}
