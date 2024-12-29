package com.marhasoft.stock_control_api.repositories;

import com.marhasoft.stock_control_api.models.OrdemItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemItemRepository extends JpaRepository<OrdemItem, Long> {
}
