package com.hemanth.wallet.web.controller;

import com.hemanth.wallet.service.dto.LoginRequest;
import com.hemanth.wallet.service.dto.WalletUserResponseDto;
import com.hemanth.wallet.service.impl.WalletLoginServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private WalletLoginServiceImpl walletloginService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/authorise")
        public ResponseEntity<WalletUserResponseDto> loginByEmail(@RequestBody LoginRequest request , HttpServletResponse response) {
        logger.info("Login initiated : {}", request.getEmail());
            WalletUserResponseDto user = walletloginService.loginByEmail(request.getEmail(), request.getPassword());

                logger.info("Login successful: {}", request.getEmail());
                return ResponseEntity.ok(user);
        }
    }

