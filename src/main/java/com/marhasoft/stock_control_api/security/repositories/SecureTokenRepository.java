package com.marhasoft.stock_control_api.security.repositories;

import com.marhasoft.stock_control_api.security.models.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecureTokenRepository extends JpaRepository<SecureToken, Long > {
    SecureToken findByToken(final String token);
    void removeByToken(SecureToken token);
}
