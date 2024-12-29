package com.marhasoft.stock_control_api.repositories;

import com.marhasoft.stock_control_api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,  Long> {
    Usuario findByLogin(String login);
    Usuario findByEmail(String email);
}
