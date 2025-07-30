package com.hemanth.wallet.service.impl;
import com.hemanth.wallet.Exception.EntityNotFound;
import com.hemanth.wallet.Exception.WalletException;
import com.hemanth.wallet.db.entity.WalletBalance;
import com.hemanth.wallet.db.entity.WalletUser;
import com.hemanth.wallet.db.repository.UserRepo;
import com.hemanth.wallet.db.repository.WalletBalanceRepo;
import com.hemanth.wallet.service.WalletUserService;
import com.hemanth.wallet.service.assembler.UserAssembler;
import com.hemanth.wallet.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class WalletUserServiceImpl implements WalletUserService {

    private static final Logger logger = LoggerFactory.getLogger(WalletUserServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private WalletBalanceRepo walletBalanceRepo;

    @Override
    public WalletUserResponseDto getUserByEmail(String email) {
        WalletUser walletuser =  userRepo.findByEmail(email);
        WalletUserResponseDto walletUserResponseDto = userAssembler.toResponseDto(walletuser);
       return walletUserResponseDto;
    }

    @Override
    public WalletUserResponseDto getUserById(Long id) {
        Optional<WalletUser> walletuserOptional = userRepo.findById(id);
        if (!walletuserOptional.isPresent()) {
            throw new WalletException("User Not Found with the given Id ");
        }
        WalletUserResponseDto walletUserResponseDto = userAssembler.toResponseDto(walletuserOptional.get());
        return walletUserResponseDto;
    }

    @Override
    public WalletUserResponseDto createUser(WalletUserRequestDto userRequestDto) {

        logger.debug("Converting WalletUserRequestDto to WalletUser entity");
        WalletUser walletUser =  userAssembler.toEntity(userRequestDto);
        walletUser.setComments("Registration Requested");
        walletUser.setStatus(WalletUserStatus.IN_ACTIVE);
        walletUser.setRole(WalletUserRole.EXTERNAL);
        logger.debug("Saving WalletUser to the database");
        WalletUser savedWalletUser = userRepo.save(walletUser);

        WalletBalance walletBalance = new WalletBalance();
        walletBalance.setWalletUser(walletUser);
        walletBalance.setOutStandingBalance(BigDecimal.ZERO);
        walletBalance.setUnclearedAmount(BigDecimal.ZERO);
        walletBalance.setComments("wallet created");
        savedWalletUser.setWalletBalance(walletBalance);
        walletBalanceRepo.save(walletBalance);

        logger.debug("Converting saved WalletUser entity to response DTO");
        WalletUserResponseDto walletUserResponseDto = userAssembler.toResponseDto(savedWalletUser);
        logger.info("User successfully created ");
        return walletUserResponseDto;
    }

    @Override
    public List<WalletUserResponseDto> getUsers() {
        List<WalletUser> userList = userRepo.findAll();
        List<WalletUserResponseDto> dtoList = userAssembler.toResponseDtos(userList);
        return dtoList;
    }


    @Override
    public void activateUser (Long id){

        userRepo.updateUserstatusById(id , WalletUserStatus.ACTIVE);
    }

    @Override
    public WalletUserResponseDto findByPhoneNumber(String phoneNumber) {
        WalletUser walletuser =  userRepo.findByPhoneNumber(phoneNumber);
        WalletUserResponseDto walletUserResponseDto = userAssembler.toResponseDto(walletuser);
        return walletUserResponseDto;
    }




}
