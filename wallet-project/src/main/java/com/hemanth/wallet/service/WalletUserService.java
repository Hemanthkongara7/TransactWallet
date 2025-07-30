package com.hemanth.wallet.service;

import com.hemanth.wallet.db.entity.WalletUser;
import com.hemanth.wallet.db.repository.UserRepo;
import com.hemanth.wallet.service.dto.WalletTransactionResponseDto;
import com.hemanth.wallet.service.dto.WalletUserRequestDto;
import com.hemanth.wallet.service.dto.WalletUserResponseDto;
import com.hemanth.wallet.web.dto.request.WalletUserRequestResource;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface WalletUserService {

    WalletUserResponseDto getUserByEmail(String email);

    WalletUserResponseDto getUserById(Long id);

    WalletUserResponseDto createUser(WalletUserRequestDto userRequestDto) ;

    List<WalletUserResponseDto> getUsers();

    void activateUser(Long Id);

    WalletUserResponseDto findByPhoneNumber(String phoneNumber);


}
