package com.marhasoft.stock_control_api.security.repositories;

import com.marhasoft.stock_control_api.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
