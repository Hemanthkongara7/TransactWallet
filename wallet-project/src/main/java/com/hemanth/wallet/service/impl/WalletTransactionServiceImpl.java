package com.hemanth.wallet.service.impl;

import com.hemanth.wallet.Exception.WalletException;
import com.hemanth.wallet.Exception.WalletExceptionMessage;
import com.hemanth.wallet.db.entity.WalletBalance;
import com.hemanth.wallet.db.entity.WalletTransaction;
import com.hemanth.wallet.db.entity.WalletUser;
import com.hemanth.wallet.db.repository.TransactionRepo;
import com.hemanth.wallet.db.repository.WalletBalanceRepo;
import com.hemanth.wallet.service.WalletTransactionService;
import com.hemanth.wallet.service.WalletUserService;
import com.hemanth.wallet.service.assembler.TransactionAssembler;
import com.hemanth.wallet.service.assembler.UserAssembler;
import com.hemanth.wallet.service.dto.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.hemanth.wallet.service.dto.WalletTransactionPaymentMethod.UPI;

@Service
public class WalletTransactionServiceImpl implements WalletTransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private TransactionAssembler transactionAssembler;

    @Autowired
    private WalletUserService walletUserService;

    @Autowired
    private WalletBalanceRepo walletBalanceRepo;

    private static final Logger logger = LoggerFactory.getLogger(WalletTransactionServiceImpl.class);


    @Override
    public WalletTransactionResponseDto getTransactionById(Long transactionId) {
        return null;
    }


    @Override
    public List<WalletTransactionResponseDto> getTransactionsByUserId(Long userId) {
        List<WalletTransaction> transactions = transactionRepo.findByWalletUserId(userId);
        return transactions.stream()
                .map(transactionAssembler::getTransactionRequestResource)
                .toList();
    }

    @Override
    public List<WalletTransactionResponseDto> getTransactionsByUserPhoneNumber(String phoneNumber) {
        List<WalletTransaction> transactions = transactionRepo.findByWalletUserPhoneNumber(phoneNumber);
        return transactions.stream()
                .map(transactionAssembler::getTransactionRequestResource)
                .toList();
    }

    @Override
    public WalletTransactionResponseDto addCreditTransaction(WalletTransactionRequestDto request) {
        WalletUserResponseDto walletUserResponseDto = walletUserService.findByPhoneNumber(request.getPhoneNumber());

        if (walletUserResponseDto == null) {
            throw new WalletException(WalletExceptionMessage.INVALID_USER_STATUS);
        }

        WalletTransaction transaction = new WalletTransaction();
        transaction.setAmount(request.getAmount());
        transaction.setComments(request.getComments());
        transaction.setAgent(request.getAgent());
        transaction.setType(request.getType());
        transaction.setPaymentMethod(request.getPaymentMethod());
        WalletUser userRef = new WalletUser();
        userRef.setId(walletUserResponseDto.getId());
        transaction.setWalletUser(userRef);
        WalletTransaction savedTransaction = transactionRepo.save(transaction);

        return transactionAssembler.getTransactionRequestResource(savedTransaction);
    }

    @Override
    @Transactional
    public WalletTransactionResponseDto transferAmount(WalletTransactionRequestDto request, Long senderId) {

        logger.info("Initiating transfer for senderId: {}", senderId);

        WalletUserResponseDto senderDto = walletUserService.getUserById(senderId);
        if (senderDto == null) {
            throw new WalletException(WalletExceptionMessage.INVALID_USER_STATUS);
        }

        WalletUserResponseDto receiverDto = walletUserService.findByPhoneNumber(request.getPhoneNumber());
        if (receiverDto == null) {
            throw new WalletException(WalletExceptionMessage.INVALID_USER_STATUS);
        }
        WalletBalance senderBalance = walletBalanceRepo.findByWalletUserId(senderId);
        if (senderBalance == null) {
            throw new WalletException(WalletExceptionMessage.INVALID_USER_STATUS);
        }

        if (senderDto.getPhoneNumber().equals(request.getPhoneNumber())) {
            throw new WalletException(WalletExceptionMessage.SELF_TRANSACTION_NOT_ALLOWED);
        }

        WalletBalance receiverBalance = walletBalanceRepo.findByWalletUserId(receiverDto.getId());
        if (receiverBalance == null) {
            receiverBalance = new WalletBalance();
            receiverBalance.setOutStandingBalance(BigDecimal.ZERO);
            receiverBalance.setUnclearedAmount(BigDecimal.ZERO);
        }
        BigDecimal transferAmount = new BigDecimal(request.getAmount());
        if (senderBalance.getOutStandingBalance().compareTo(transferAmount) < 0) {
            throw new WalletException(WalletExceptionMessage.IN_SUFFICIENT_BALANCE);
        }
        senderBalance.setOutStandingBalance(senderBalance.getOutStandingBalance().subtract(transferAmount));
        receiverBalance.setOutStandingBalance(receiverBalance.getOutStandingBalance().add(transferAmount));

        walletBalanceRepo.save(senderBalance);
        walletBalanceRepo.save(receiverBalance);

        WalletTransaction transaction = new WalletTransaction();
        transaction.setAmount(request.getAmount());
        transaction.setComments("Sending funds to: " + receiverDto.getId());
        transaction.setAgent(null);
        transaction.setType(WalletTransactionType.Debit);
        transaction.setPaymentMethod(UPI);
        transaction.setStatus(WalletTransactionStatus.Approved);
        WalletUser senderUserRef = new WalletUser();
        senderUserRef.setId(senderId);
        transaction.setWalletUser(senderUserRef);
        WalletTransaction savedTransaction = transactionRepo.save(transaction);

        WalletTransaction receiverTransaction = new WalletTransaction();
        receiverTransaction.setAmount(request.getAmount());
        receiverTransaction.setComments("Received from: " + senderId);
        receiverTransaction.setAgent(request.getAgent());
        receiverTransaction.setType(WalletTransactionType.Credit);
        receiverTransaction.setPaymentMethod(UPI);
        receiverTransaction.setStatus(WalletTransactionStatus.Approved);
        WalletUser userRef = new WalletUser();
        userRef.setId(receiverDto.getId());
        receiverTransaction.setWalletUser(userRef);
        transactionRepo.save(receiverTransaction);

        return transactionAssembler.getTransactionRequestResource(savedTransaction);
    }

    @Override
    public WalletTransactionResponseDto requestFund (WalletTransactionRequestDto requestDto, Long creditorUserId) {
        logger.info("User is requesting funds from phone number: {}", requestDto.getPhoneNumber());
        WalletUserResponseDto sender = walletUserService.findByPhoneNumber(requestDto.getPhoneNumber());
        if (sender == null) {
            throw new WalletException(WalletExceptionMessage.INVALID_USER_STATUS);
        }
        WalletUserResponseDto requester = walletUserService.getUserById(creditorUserId);
        if (requester == null) {
            throw new WalletException(WalletExceptionMessage.INVALID_USER_STATUS);
        }

        if (requester.getPhoneNumber().equals(sender.getPhoneNumber())) {
            throw new WalletException(WalletExceptionMessage.SELF_TRANSACTION_NOT_ALLOWED);
        }

        WalletTransaction creditorTransaction = new WalletTransaction();
        creditorTransaction.setAmount(requestDto.getAmount());
        creditorTransaction.setComments("amount requested from user: "+ sender.getId());
        creditorTransaction.setAgent(null);
        creditorTransaction.setType(WalletTransactionType.Credit);
        creditorTransaction.setPaymentMethod(UPI);
        creditorTransaction.setStatus(WalletTransactionStatus.Requested);
        WalletUser senderUserRef = new WalletUser();
        senderUserRef.setId(creditorUserId);
        creditorTransaction.setWalletUser(senderUserRef);
        WalletTransaction savedCreditorTransaction = transactionRepo.save(creditorTransaction);

        WalletTransaction debitorTransaction = new WalletTransaction();
        debitorTransaction.setAmount(requestDto.getAmount());
        debitorTransaction.setComments("requested by user: " + creditorUserId);
        debitorTransaction.setAgent(null);
        debitorTransaction.setType(WalletTransactionType.Debit);
        debitorTransaction.setDebitorTransactionId(savedCreditorTransaction.getId());
        debitorTransaction.setPaymentMethod(UPI);
        debitorTransaction.setStatus(WalletTransactionStatus.Requested);
        WalletUser requesterUserRef = new WalletUser();
        requesterUserRef.setId(sender.getId());
        debitorTransaction.setWalletUser(requesterUserRef);
        WalletTransaction reqSavedTransaction = transactionRepo.save(debitorTransaction);

        return transactionAssembler.getTransactionRequestResource(savedCreditorTransaction);
    }
    @Override
    @Transactional
    public WalletTransactionResponseDto reviewPaymentStatus(Long debitorId , Long transactionId, WalletTransactionStatus status) {

        logger.info("Initiating transfer request processing for UserId: {}", debitorId);


        Optional<WalletTransaction> debitorWalletTransactionOptional = transactionRepo.findByIdAndWalletUserId(transactionId, debitorId);
        if (debitorWalletTransactionOptional.isEmpty()) {
            throw new WalletException(WalletExceptionMessage.INVALID_TRANSACTION_ID);
        }
        WalletTransaction debitorWalletTransaction = debitorWalletTransactionOptional.get();
        WalletBalance senderBalance = walletBalanceRepo.findByWalletUserId(debitorWalletTransaction.getWalletUser().getId());

        if (debitorWalletTransaction.getStatus() != WalletTransactionStatus.Requested) {
            throw new WalletException(WalletExceptionMessage.REQUEST_ALREADY_ACCESSED);
        }

        Optional<WalletTransaction> creditorWalletTransactionOptional = transactionRepo.findById(debitorWalletTransaction.getDebitorTransactionId());
        if (creditorWalletTransactionOptional.isEmpty()) {
            throw new WalletException(WalletExceptionMessage.IN_SUFFICIENT_BALANCE);
        }
        WalletTransaction creditorWalletTransaction =creditorWalletTransactionOptional.get();
        WalletBalance receiverBalance = walletBalanceRepo.findByWalletUserId(creditorWalletTransaction.getWalletUser().getId());

        BigDecimal transferAmount = new BigDecimal(debitorWalletTransaction.getAmount());
        if (senderBalance.getOutStandingBalance().compareTo(transferAmount) < 0) {
            // TODO decline transaction due to insufficient funds
            throw new WalletException(WalletExceptionMessage.IN_SUFFICIENT_BALANCE);
        }

        senderBalance.setOutStandingBalance(senderBalance.getOutStandingBalance().subtract(transferAmount));
        receiverBalance.setOutStandingBalance(receiverBalance.getOutStandingBalance().add(transferAmount));

        walletBalanceRepo.save(senderBalance);
        walletBalanceRepo.save(receiverBalance);

        debitorWalletTransaction.setComments("Sent funds to: " + creditorWalletTransaction.getWalletUser().getId());
        debitorWalletTransaction.setType(WalletTransactionType.Debit);
        debitorWalletTransaction.setPaymentMethod(UPI);
        debitorWalletTransaction.setStatus(WalletTransactionStatus.Approved);
        WalletTransaction savedTransaction = transactionRepo.save(debitorWalletTransaction);


        creditorWalletTransaction.setComments("Received from: " + debitorId);
        creditorWalletTransaction.setType(WalletTransactionType.Credit);
        creditorWalletTransaction.setPaymentMethod(UPI);
        creditorWalletTransaction.setStatus(WalletTransactionStatus.Approved);
        transactionRepo.save(creditorWalletTransaction);

        return transactionAssembler.getTransactionRequestResource(savedTransaction);
    }
}



