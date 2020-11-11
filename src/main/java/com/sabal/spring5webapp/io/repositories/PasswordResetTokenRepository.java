package com.sabal.spring5webapp.io.repositories;

import com.sabal.spring5webapp.io.entity.PasswordResetTokenEntity;
import org.springframework.data.repository.CrudRepository;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetTokenEntity, Long> {

    PasswordResetTokenEntity findByToken(String token);
}
