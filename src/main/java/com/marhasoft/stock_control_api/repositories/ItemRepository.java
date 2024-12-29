package com.marhasoft.stock_control_api.repositories;

import com.marhasoft.stock_control_api.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
