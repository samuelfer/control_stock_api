package com.marhasoft.stock_control_api.security.repositories;

import com.marhasoft.stock_control_api.security.models.Privilegio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivilegioRepository extends JpaRepository<Privilegio, Long> {
    public List<Privilegio> findByRoleId(Long roleId);
}
