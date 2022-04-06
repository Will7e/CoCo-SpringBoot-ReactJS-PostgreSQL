package com.example.coco.dao;


import com.example.coco.repository.ConfirmationTokenRepository;
import com.example.coco.token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ConfirmationTokenDAO {
    ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationToken saveToken(ConfirmationToken confirmationToken){
       return confirmationTokenRepository.save(confirmationToken);
    }

    public Optional<ConfirmationToken> findTokenByUserId(Long id){
        return confirmationTokenRepository.findConfirmationTokenByUserUserId(id);
    }

    public Optional<ConfirmationToken> findByToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }



    public int updateConfirmedToken(String token, LocalDateTime localDateTime){
        return confirmationTokenRepository.updateConfirmedToken(token,localDateTime);
    }

}
