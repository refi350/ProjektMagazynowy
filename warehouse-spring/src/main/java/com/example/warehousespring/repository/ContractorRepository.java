package com.example.warehousespring.repository;

import com.example.warehousespring.data.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
}
