package com.pragmafood.talentpool.square.infrastructure.output.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.EmployeeRestaurantEntity;

public interface EmployeeRestaurantRepository extends JpaRepository<EmployeeRestaurantEntity, Long> {

    Optional<EmployeeRestaurantEntity> findByEmployeeId(Long employeeId);
}
