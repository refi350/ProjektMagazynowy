package com.warehouse.project.repository;

import com.warehouse.project.data.Commodity;
import com.warehouse.project.data.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
}
