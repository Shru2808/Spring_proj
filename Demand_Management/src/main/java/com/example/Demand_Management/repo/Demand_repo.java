package com.example.Demand_Management.repo;

import com.example.Demand_Management.Models.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Demand_repo extends JpaRepository<Demand, Long> {
}
