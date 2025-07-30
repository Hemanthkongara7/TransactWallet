package com.hemanth.wallet.service;

import com.hemanth.wallet.service.dto.WalletTransactionRequestDto;
import com.hemanth.wallet.service.dto.WalletTransactionStatus;
import com.hemanth.wallet.web.dto.request.WalletTransactionRequestResource;
import com.hemanth.wallet.service.dto.WalletTransactionResponseDto;
import com.hemanth.wallet.db.repository.TransactionRepo;

import java.util.List;

public interface WalletTransactionService {

    WalletTransactionResponseDto getTransactionById(Long transactionId);

    List<WalletTransactionResponseDto> getTransactionsByUserId(Long userId);

    WalletTransactionResponseDto addCreditTransaction(WalletTransactionRequestDto request);

    List<WalletTransactionResponseDto> getTransactionsByUserPhoneNumber(String phoneNumber);

    WalletTransactionResponseDto transferAmount (WalletTransactionRequestDto request, Long senderId);

    WalletTransactionResponseDto requestFund (WalletTransactionRequestDto requestDto, Long requesterUserId);

    WalletTransactionResponseDto reviewPaymentStatus (Long UserId, Long transactionId , WalletTransactionStatus status );


}
