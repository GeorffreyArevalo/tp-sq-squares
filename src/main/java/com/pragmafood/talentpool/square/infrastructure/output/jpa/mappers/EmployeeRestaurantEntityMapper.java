package com.pragmafood.talentpool.square.infrastructure.output.jpa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.pragmafood.talentpool.square.domain.models.EmployeeRestaurant;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.EmployeeRestaurantEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmployeeRestaurantEntityMapper {

    @Mapping(target = "restaurant.id", source = "restaurantId")
    EmployeeRestaurantEntity toEntity(EmployeeRestaurant employeeRestaurant);

    @Mapping(target = "restaurantId", source = "restaurant.id")
    EmployeeRestaurant toDomain(EmployeeRestaurantEntity entity);
}
