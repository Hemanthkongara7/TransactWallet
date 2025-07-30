package com.hemanth.wallet.service.assembler;


import com.hemanth.wallet.db.entity.WalletUser;
import com.hemanth.wallet.service.dto.WalletTransactionResponseDto;
import com.hemanth.wallet.service.dto.WalletUserRequestDto;
import com.hemanth.wallet.service.dto.WalletUserResponseDto;
import com.hemanth.wallet.service.dto.WalletUserStatus;
import com.hemanth.wallet.web.dto.request.WalletUserRequestResource;
import com.hemanth.wallet.web.dto.response.WalletTransactionResponseResource;
import com.hemanth.wallet.web.dto.response.WalletUserResponseResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAssembler {

    public WalletUserResponseResource getUserRequestResource(WalletUserResponseDto userResponseDto) {
        WalletUserResponseResource userResponse = new WalletUserResponseResource();
        userResponse.setId(userResponseDto.getId());
        userResponse.setName(userResponseDto.getName());
        userResponse.setEmail(userResponseDto.getEmail());
        userResponse.setPhoneNumber(userResponseDto.getPhoneNumber());
        userResponse.setAge(userResponseDto.getAge());
        userResponse.setStatus(userResponseDto.getStatus());
        userResponse.setTransactions(userResponseDto.getTransactions());
        userResponse.setPnCountryCode(userResponseDto.getPnCountryCode());

        return userResponse;

    }

    public WalletUserRequestDto toRequestDto(WalletUserRequestResource resource) {

        WalletUserRequestDto requestdto = new WalletUserRequestDto();

        requestdto.setName(resource.getName());
        requestdto.setAge(resource.getAge());
        requestdto.setPnCountryCode(resource.getPnCountryCode());
        requestdto.setPhoneNumber(resource.getPhoneNumber());
        requestdto.setEmail(resource.getEmail());
        requestdto.setPassword(resource.getPassword());

        return requestdto;
    }

    public WalletUserResponseResource toResponseDto(WalletUserResponseDto resource) {
        WalletUserResponseResource response = new WalletUserResponseResource();
        response.setId(resource.getId());
        response.setName(resource.getName());
        response.setPnCountryCode(resource.getPnCountryCode());
        response.setPhoneNumber(resource.getPhoneNumber());
        response.setEmail(resource.getEmail());
        response.setTransactions(resource.getTransactions());
        response.setStatus(resource.getStatus());
        return response;
    }

    public WalletUser toEntity(WalletUserRequestDto resource) {
        WalletUser walletUser = new WalletUser();
        walletUser.setName(resource.getName());
        walletUser.setAge(resource.getAge());
        walletUser.setPnCountryCode(resource.getPnCountryCode());
        walletUser.setPhoneNumber(resource.getPhoneNumber());
        walletUser.setEmail(resource.getEmail());
        walletUser.setPassword(resource.getPassword());
        return walletUser;
    }

    public WalletUserResponseDto toResponseDto(WalletUser savedWalletUser) {
        WalletUserResponseDto walletUserResponseDto = new WalletUserResponseDto();
        walletUserResponseDto.setId(savedWalletUser.getId());
        walletUserResponseDto.setName(savedWalletUser.getName());
        walletUserResponseDto.setAge(savedWalletUser.getAge());
        walletUserResponseDto.setPnCountryCode(savedWalletUser.getPnCountryCode());
        walletUserResponseDto.setPhoneNumber(savedWalletUser.getPhoneNumber());
        walletUserResponseDto.setEmail(savedWalletUser.getEmail());
        walletUserResponseDto.setStatus(savedWalletUser.getStatus());
        walletUserResponseDto.setRoles(savedWalletUser.getRole());

        walletUserResponseDto.setOutStandingBalance(walletUserResponseDto.getOutStandingBalance());

        return walletUserResponseDto;

    }
    public List<WalletUserResponseDto> toResponseDtos(List<WalletUser> users) {
        return users.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<WalletUserResponseResource> getUserRequestResource(List<WalletUserResponseDto> users) {
        return users.stream()
                .map(this::getUserRequestResource)
                .collect(Collectors.toList());
    }
}




