package com.pragmafood.talentpool.square.infrastructure.output.jpa.adapters;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.pragmafood.talentpool.square.domain.models.EmployeeRestaurant;
import com.pragmafood.talentpool.square.domain.spi.EmployeeRestaurantPersistencePort;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.EmployeeRestaurantEntity;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.mappers.EmployeeRestaurantEntityMapper;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.repositories.EmployeeRestaurantRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeeRestaurantJpaAdapter implements EmployeeRestaurantPersistencePort {

    private final EmployeeRestaurantRepository employeeRestaurantRepository;
    private final EmployeeRestaurantEntityMapper employeeRestaurantEntityMapper;

    @Override
    public EmployeeRestaurant saveEmployeeRestaurant(EmployeeRestaurant employeeRestaurant) {
        EmployeeRestaurantEntity entity = employeeRestaurantEntityMapper.toEntity(employeeRestaurant);
        EmployeeRestaurantEntity savedEntity = employeeRestaurantRepository.save(entity);
        return employeeRestaurantEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<EmployeeRestaurant> findByEmployeeId(Long employeeId) {
        return employeeRestaurantRepository.findByEmployeeId(employeeId)
                .map(employeeRestaurantEntityMapper::toDomain);
    }
}
