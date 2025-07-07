package dev.saurabh.parkinglot.repository;

import dev.saurabh.parkinglot.entity.Gate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GateRepository extends JpaRepository<Gate, Long> {
}
