package com.hemanth.wallet.service.assembler;
import com.hemanth.wallet.db.entity.WalletBalance;
import com.hemanth.wallet.db.entity.WalletTransaction;
import com.hemanth.wallet.db.repository.WalletBalanceRepo;
import com.hemanth.wallet.service.dto.WalletTransactionRequestDto;
import com.hemanth.wallet.service.dto.WalletTransactionResponseDto;
import com.hemanth.wallet.service.dto.WalletUserResponseDto;
import com.hemanth.wallet.web.dto.request.WalletTransactionRequestResource;
import com.hemanth.wallet.web.dto.response.RequestAmountResponseResource;
import com.hemanth.wallet.web.dto.response.WalletBalanceResponseResource;
import com.hemanth.wallet.web.dto.response.WalletTransactionResponseResource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionAssembler {

    public List<WalletTransactionResponseResource> getTransactionRequestResource(List<WalletTransactionResponseDto> transactions) {
        return transactions.stream()
                .map(this::getTransactionRequestResource)
                .collect(Collectors.toList());
    }


    public WalletTransactionResponseResource getTransactionRequestResource(WalletTransactionResponseDto transactions) {
        WalletTransactionResponseResource response = new WalletTransactionResponseResource();
        if(transactions == null) {
            return response;

        }
        response.setId(transactions.getId());
        response.setAmount(transactions.getAmount());
        response.setPaymentMethod(transactions.getPaymentMethod());
        response.setStatus(transactions.getStatus());
        response.setComments(transactions.getComments());
        response.setType(transactions.getType());
        response.setAgent(transactions.getAgent());

        return response;
    }
    public WalletTransactionResponseDto getTransactionRequestResource(WalletTransaction transaction) {
        WalletTransactionResponseDto response = new WalletTransactionResponseDto();
        response.setId(transaction.getId());
        response.setAmount(transaction.getAmount());
        response.setType(transaction.getType());
        response.setStatus(transaction.getStatus());
        response.setComments(transaction.getComments());
        response.setAgent(transaction.getAgent());
        response.setPaymentMethod(transaction.getPaymentMethod());

        return response;
    }
    public WalletTransactionRequestDto getWalletTransactionRequestDto(WalletTransactionRequestResource request) {
        WalletTransactionRequestDto dto = new WalletTransactionRequestDto();
        dto.setAmount(request.getAmount());
        dto.setComments(request.getComments());
        dto.setAgent(request.getAgent());
        dto.setType(request.getType());
        dto.setPaymentMethod(request.getPaymentMethod());
        dto.setPhoneNumber(request.getPhoneNumber());
        return dto;
    }

    public RequestAmountResponseResource getRequestAmountRequestResource(WalletTransactionResponseDto transactions) {
        RequestAmountResponseResource response = new RequestAmountResponseResource();
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
