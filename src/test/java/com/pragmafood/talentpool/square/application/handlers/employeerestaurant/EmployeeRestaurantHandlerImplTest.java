package com.pragmafood.talentpool.square.application.handlers.employeerestaurant;

import com.pragmafood.talentpool.square.application.dtos.requests.EmployeeRestaurantRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.EmployeeRestaurantResponse;
import com.pragmafood.talentpool.square.application.mappers.EmployeeRestaurantRequestMapper;
import com.pragmafood.talentpool.square.domain.api.EmployeeRestaurantServicePort;
import com.pragmafood.talentpool.square.domain.models.EmployeeRestaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeRestaurantHandlerImplTest {
    @Mock EmployeeRestaurantServicePort employeeRestaurantServicePort;
    @Mock EmployeeRestaurantRequestMapper employeeRestaurantRequestMapper;
    @InjectMocks EmployeeRestaurantHandlerImpl employeeRestaurantHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeRestaurantHandler = new EmployeeRestaurantHandlerImpl(employeeRestaurantServicePort, employeeRestaurantRequestMapper);
    }

    @Test
    void assignEmployeeToRestaurant_validRequest_returnsResponse() {
        EmployeeRestaurantRequest req = mock(EmployeeRestaurantRequest.class);
        EmployeeRestaurant er = new EmployeeRestaurant();
        EmployeeRestaurantResponse resp = new EmployeeRestaurantResponse(1L, 2L, 3L);
        when(employeeRestaurantRequestMapper.toDomain(req)).thenReturn(er);
        when(employeeRestaurantServicePort.assignEmployeeToRestaurant(er, 1L)).thenReturn(er);
        when(employeeRestaurantRequestMapper.toResponse(er)).thenReturn(resp);
        EmployeeRestaurantResponse result = employeeRestaurantHandler.assignEmployeeToRestaurant(req, 1L);
        assertNotNull(result);
        assertEquals(2L, result.employeeId());
        verify(employeeRestaurantServicePort).assignEmployeeToRestaurant(er, 1L);
    }
    // ...más pruebas unitarias para otros métodos...
}
