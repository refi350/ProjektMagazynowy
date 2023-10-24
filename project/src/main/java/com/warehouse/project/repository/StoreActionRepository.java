package com.warehouse.project.repository;

import com.warehouse.project.data.Commodity;
import com.warehouse.project.data.StoreAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreActionRepository extends JpaRepository<StoreAction, Long> {
}
