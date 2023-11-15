package com.example.warehousespring.repository;

import com.example.warehousespring.data.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommodityRepository extends JpaRepository<Commodity, Long> {

}
