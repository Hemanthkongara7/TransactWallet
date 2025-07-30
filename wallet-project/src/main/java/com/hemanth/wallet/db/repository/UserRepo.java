package com.hemanth.wallet.db.repository;
import com.hemanth.wallet.db.entity.WalletUser;
import com.hemanth.wallet.service.dto.WalletUserStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserRepo extends JpaRepository<WalletUser, Long> {
    WalletUser findByEmail(String email);

    WalletUser findByPhoneNumber(String phoneNumber);

    @Modifying
    @Query("UPDATE WalletUser u SET u.status = :status WHERE u.id = :id")
    int updateUserstatusById(@Param("id") Long id, @Param("status") WalletUserStatus status);



}
