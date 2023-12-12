package com.example.warehousespring.repository;

import com.example.warehousespring.data.store_action.StoreAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreActionRepository extends JpaRepository<StoreAction, Long> {
}
