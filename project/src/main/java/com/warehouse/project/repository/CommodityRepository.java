package com.warehouse.project.repository;

import com.warehouse.project.data.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommodityRepository extends JpaRepository<Commodity, Long> {

}
