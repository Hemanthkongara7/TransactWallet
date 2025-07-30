package com.hemanth.wallet.service;



import com.hemanth.wallet.service.dto.WalletUserResponseDto;

public interface WalletLoginService {
    WalletUserResponseDto loginByEmail(String email, String password);
}
