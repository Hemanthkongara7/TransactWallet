package com.hemanth.wallet.web.controller;

import com.hemanth.wallet.service.WalletTransactionService;
import com.hemanth.wallet.service.assembler.BalanceAssembler;
import com.hemanth.wallet.service.assembler.TransactionAssembler;
import com.hemanth.wallet.service.dto.BalanceResponseDto;
import com.hemanth.wallet.service.dto.WalletTransactionRequestDto;
import com.hemanth.wallet.service.dto.WalletTransactionType;
import com.hemanth.wallet.web.Security.Authorized;
import com.hemanth.wallet.web.dto.request.ReviewPaymentRequestResource;
import com.hemanth.wallet.web.dto.request.WalletTransactionRequestResource;
import com.hemanth.wallet.service.dto.WalletTransactionResponseDto;
import com.hemanth.wallet.web.dto.response.RequestAmountResponseResource;
import com.hemanth.wallet.web.dto.response.WalletBalanceResponseResource;
import com.hemanth.wallet.web.dto.response.WalletTransactionResponseResource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private WalletTransactionService walletTransactionService;

    @Autowired
    private TransactionAssembler transactionAssembler;

    @Autowired
    private BalanceAssembler balanceAssembler;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/{id}")
    public WalletTransactionResponseResource getTransactionById(@PathVariable Long id) {
        WalletTransactionResponseDto response = walletTransactionService.getTransactionById(id);
        WalletTransactionResponseResource walletTransactionResponse = transactionAssembler.getTransactionRequestResource(response);
        return walletTransactionResponse;
    }

    @GetMapping("")
    @Authorized
    public List<WalletTransactionResponseResource> getTransactionsByUserId(HttpServletRequest httpRequest) {
        Long userId = (Long) httpRequest.getAttribute("id");
        List<WalletTransactionResponseDto> transactions = walletTransactionService.getTransactionsByUserId(userId);
        List<WalletTransactionResponseResource> walletTransactionResponse = transactionAssembler.getTransactionRequestResource(transactions);
        return walletTransactionResponse;
    }


    @PostMapping("/{transactionId}/review")
    @Authorized
    public ResponseEntity<WalletBalanceResponseResource> reviewPaymentStatus(@PathVariable("transactionId") Long transactionId, @RequestBody ReviewPaymentRequestResource reqResource, HttpServletRequest httpRequest){
        Long senderUserId = (Long) httpRequest.getAttribute("id");
        logger.info("before review payment status ");
        WalletTransactionResponseDto responseDto = walletTransactionService.reviewPaymentStatus(senderUserId, transactionId, reqResource.getStatus());
        logger.info("after review payment status");
        WalletBalanceResponseResource responseResource = balanceAssembler.getTransactionBalanceRequestResource(responseDto);
        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
    }

    @PostMapping("/credit")
    @Authorized
    public ResponseEntity<WalletBalanceResponseResource> transferAmount(@RequestBody WalletTransactionRequestResource request, HttpServletRequest httpRequest) {
        Long senderUserId = (Long) httpRequest.getAttribute("id");
        WalletTransactionRequestDto requestDto = transactionAssembler.getWalletTransactionRequestDto(request);
        WalletTransactionResponseDto responseDto = walletTransactionService.transferAmount(requestDto, senderUserId);
        WalletBalanceResponseResource responseResource = balanceAssembler.getTransactionBalanceRequestResource(responseDto);
        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
    }

    @PostMapping("/requestFunds")
    @Authorized
    public ResponseEntity<RequestAmountResponseResource> requestFunds(@RequestBody WalletTransactionRequestResource request, HttpServletRequest httpRequest) {
        Long requesterId = (Long) httpRequest.getAttribute("id");
        request.setType(WalletTransactionType.Deposit);
        WalletTransactionRequestDto requestDto = transactionAssembler.getWalletTransactionRequestDto(request);
        requestDto.setUserId(requesterId);
        WalletTransactionResponseDto responseDto = walletTransactionService.requestFund(requestDto, requesterId);
        RequestAmountResponseResource responseResource = transactionAssembler.getRequestAmountRequestResource(responseDto);
        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
    }



//    @PostMapping("/credit")
//    public ResponseEntity<WalletTransactionResponseResource> creditMoney(@RequestBody WalletTransactionRequestResource request) {
//        request.setType(WalletTransactionType.Credit);
//        WalletTransactionRequestDto requestDto = transactionAssembler.getWalletTransactionRequestDto(request);
//        WalletTransactionResponseDto responseDto = walletTransactionService.addCreditTransaction(requestDto);
//        WalletTransactionResponseResource responseResource = transactionAssembler.getTransactionRequestResource(responseDto);
//        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
//    }

//    @PostMapping("/debit")
//    public ResponseEntity<WalletTransactionResponseResource> debitMoney(@RequestBody WalletTransactionRequestResource request) {
//        request.setType(WalletTransactionType.Debit);
//        WalletTransactionRequestDto requestDto = transactionAssembler.getWalletTransactionRequestDto(request);
//        WalletTransactionResponseDto responseDto = walletTransactionService.addCreditTransaction(requestDto);
//        WalletTransactionResponseResource responseResource = transactionAssembler.getTransactionRequestResource(responseDto);
//        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
//    }

    @GetMapping("/user/{phoneNumber}")
    public List<WalletTransactionResponseResource> getTransactionsByUserPhoneNumber(@PathVariable String phoneNumber) {
        List<WalletTransactionResponseDto> transactions = walletTransactionService.getTransactionsByUserPhoneNumber(phoneNumber);
        return transactionAssembler.getTransactionRequestResource(transactions);
    }
}







