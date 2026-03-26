package com.pragmafood.talentpool.square.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pragmafood.talentpool.square.application.dtos.requests.EmployeeRestaurantRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.EmployeeRestaurantResponse;
import com.pragmafood.talentpool.square.domain.models.EmployeeRestaurant;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmployeeRestaurantRequestMapper {

    EmployeeRestaurant toDomain(EmployeeRestaurantRequest request);

    EmployeeRestaurantResponse toResponse(EmployeeRestaurant employeeRestaurant);
}
