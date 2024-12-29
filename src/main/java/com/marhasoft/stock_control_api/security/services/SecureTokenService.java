package com.marhasoft.stock_control_api.security.services;

import com.marhasoft.stock_control_api.security.models.SecureToken;


public interface SecureTokenService {

    SecureToken createSecureToken();

    void saveSecureToken(SecureToken secureToken);

    SecureToken findByToken(String token);

    void removeToken(SecureToken token);

}
