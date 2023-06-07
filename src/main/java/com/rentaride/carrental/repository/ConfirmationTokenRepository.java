package com.rentaride.carrental.repository;

import com.rentaride.carrental.model.token.ConfirmationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {
    @Query("SELECT c FROM ConfirmationToken c WHERE c.token = :token")
    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c SET c.confirmedAt = ?2 WHERE c.token = ?1")
    void updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
