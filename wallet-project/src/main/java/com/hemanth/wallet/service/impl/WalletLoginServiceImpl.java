package com.hemanth.wallet.service.impl;
import com.hemanth.wallet.Exception.WalletExceptionMessage;
import com.hemanth.wallet.Exception.WalletException;
import com.hemanth.wallet.db.entity.WalletUser;
import com.hemanth.wallet.db.repository.UserRepo;
import com.hemanth.wallet.service.WalletLoginService;
import com.hemanth.wallet.service.assembler.LoginAssembler;
import com.hemanth.wallet.service.dto.WalletUserResponseDto;
import com.hemanth.wallet.service.dto.WalletUserStatus;
import com.hemanth.wallet.web.Security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WalletLoginServiceImpl implements WalletLoginService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LoginAssembler loginAssembler;

    @Autowired
    private JwtService jwtService;

    private static final Logger LOGGER = LoggerFactory.getLogger(WalletLoginServiceImpl.class);


    @Override
    public WalletUserResponseDto loginByEmail(String email, String PastPassword) {

        WalletUser user = userRepo.findByEmail(email);
        if (user != null) {
            if(user.getStatus() == WalletUserStatus.IN_ACTIVE){
                throw new WalletException(WalletExceptionMessage.INVALID_USER_STATUS);
            }
            LOGGER.debug("User found having the email - {}", email);
            if (user.getPassword().equals(PastPassword)) {
                LOGGER.info("Login successful - {}", email);
                return loginAssembler.toDto(user);
            }
        }
        LOGGER.debug("session completed");
        throw new WalletException(WalletExceptionMessage.UN_AUTHORIZED);

    }
}

