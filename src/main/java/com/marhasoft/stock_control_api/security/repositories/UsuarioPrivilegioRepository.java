package com.marhasoft.stock_control_api.security.repositories;

import com.marhasoft.stock_control_api.security.models.UsuarioPrivilegio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioPrivilegioRepository extends JpaRepository<UsuarioPrivilegio, Long> {
    void deleteByUsuarioId(Long usuarioId);

    List<UsuarioPrivilegio> findByUsuarioId(Long usuarioId);

    void deleteByUsuarioIdAndPrivilegioId(Long usuarioId, Long privilegioId);
}
