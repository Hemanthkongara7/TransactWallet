package com.hemanth.wallet.web.controller;

import com.hemanth.wallet.service.WalletUserService;
import com.hemanth.wallet.service.assembler.UserAssembler;
import com.hemanth.wallet.service.dto.WalletUserResponseDto;
import com.hemanth.wallet.web.Security.Authorized;
import com.hemanth.wallet.web.dto.request.WalletUserRequestResource;
import com.hemanth.wallet.web.dto.response.WalletUserResponseResource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping( "/user")
public class UserController {
    @Autowired
    private WalletUserService walletUserService;

    @Autowired
    private UserAssembler userAssembler;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("")
    @Authorized
    public WalletUserResponseResource getUserById(HttpServletRequest request) {
            Long userId = (Long) request.getAttribute("id");
            WalletUserResponseDto response = walletUserService.getUserById(userId);
            WalletUserResponseResource walletUserResponse = userAssembler.getUserRequestResource(response);

            return walletUserResponse;
    }

    @GetMapping("/email/{email}")
    @Authorized
    public WalletUserResponseResource getUserByEmail(@PathVariable String email) {
        WalletUserResponseDto response = walletUserService.getUserByEmail(email);
        WalletUserResponseResource walletUserResponse = userAssembler.getUserRequestResource(response);
        return walletUserResponse;
    }

    @PostMapping("/register")
    public ResponseEntity<WalletUserResponseResource> createUser(@RequestBody WalletUserRequestResource userRequestResource) {

        LOGGER.debug("Converting WalletUserRequestResource to WalletUserRequestDto");
        WalletUserResponseDto ResponseDto = walletUserService.createUser(userAssembler.toRequestDto(userRequestResource));
        LOGGER.debug("Converting WalletUserResponseDto to WalletUserResponseResource");
        WalletUserResponseResource response = userAssembler.toResponseDto(ResponseDto);
        LOGGER.info("User registration successful for email: {}", userRequestResource.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
