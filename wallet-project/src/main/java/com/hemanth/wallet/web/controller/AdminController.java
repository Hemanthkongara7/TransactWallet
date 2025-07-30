package com.hemanth.wallet.web.controller;

import com.hemanth.wallet.db.entity.WalletUser;
import com.hemanth.wallet.service.WalletUserService;
import com.hemanth.wallet.service.assembler.UserAssembler;
import com.hemanth.wallet.service.dto.WalletTransactionResponseDto;
import com.hemanth.wallet.service.dto.WalletUserResponseDto;
import com.hemanth.wallet.web.Security.Authorized;
import com.hemanth.wallet.web.dto.response.WalletTransactionResponseResource;
import com.hemanth.wallet.web.dto.response.WalletUserResponseResource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hemanth.wallet.service.dto.WalletUserRole.ADMIN;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private WalletUserService walletUserService;

    @Autowired
    private UserAssembler userAssembler;

    @GetMapping("/users")
    public List<WalletUserResponseResource> getUsers() {
        List<WalletUserResponseDto> users = walletUserService.getUsers();
        List<WalletUserResponseResource> walletUserResponse = userAssembler.getUserRequestResource(users);
        return walletUserResponse;
    }


    @PostMapping("/user/{userId}/activate")
    @Authorized(roles=ADMIN)
    public void activateUser (@PathVariable("userId") Long userId , HttpServletRequest httpRequest){
        Long loggedInUserId = (Long) httpRequest.getAttribute("id");
        walletUserService.activateUser(userId);
    }



}


