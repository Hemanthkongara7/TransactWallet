package com.hemanth.wallet.service.assembler;

import com.hemanth.wallet.db.entity.WalletUser;
import com.hemanth.wallet.service.dto.WalletUserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class LoginAssembler {

    public WalletUserResponseDto toDto(WalletUser user) {
        WalletUserResponseDto dto = new WalletUserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setStatus(user.getStatus());
        dto.setComments(user.getComments());
        dto.setAgent(user.getAgent());
//      dto.setPassword(user.getPassword());
        return dto;
    }
}
