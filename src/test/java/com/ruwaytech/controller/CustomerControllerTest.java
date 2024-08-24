package com.ruwaytech.controller;

import com.ruwaytech.service.CustomerService;
import com.ruwaytech.shared.dto.CustomerDto;
import com.ruwaytech.shared.dto.ResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        List<CustomerDto> customers = Arrays.asList(new CustomerDto(), new CustomerDto());
        when(customerService.getAll()).thenReturn(customers);

        ResponseEntity<ResponseDto> response = customerController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody().getData());
        assertEquals("Lista de clientes.", response.getBody().getMessage());
        verify(customerService).getAll();
    }

    @Test
    void testGetById() {
        Integer id = 1;
        CustomerDto customer = new CustomerDto();
        when(customerService.getById(id)).thenReturn(customer);

        ResponseEntity<ResponseDto> response = customerController.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody().getData());
        assertEquals("Datos del cliente.", response.getBody().getMessage());
        verify(customerService).getById(id);
    }

    @Test
    void testCreate() {
        CustomerDto customerToCreate = new CustomerDto();
        CustomerDto createdCustomer = new CustomerDto();
        when(customerService.create(customerToCreate)).thenReturn(createdCustomer);

        ResponseEntity<ResponseDto> response = customerController.create(customerToCreate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdCustomer, response.getBody().getData());
        assertEquals("Cliente creado existosamente.", response.getBody().getMessage());
        verify(customerService).create(customerToCreate);
    }

    @Test
    void testUpdate() {
        Integer id = 1;
        CustomerDto customerToUpdate = new CustomerDto();
        CustomerDto updatedCustomer = new CustomerDto();
        when(customerService.update(id, customerToUpdate)).thenReturn(updatedCustomer);

        ResponseEntity<ResponseDto> response = customerController.update(id, customerToUpdate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCustomer, response.getBody().getData());
        assertEquals("Cliente actualizado existosamente.", response.getBody().getMessage());
        verify(customerService).update(id, customerToUpdate);
    }

    @Test
    void testDelete() {
        Integer id = 1;
        CustomerDto deletedCustomer = new CustomerDto();
        when(customerService.delete(id)).thenReturn(deletedCustomer);

        ResponseEntity<ResponseDto> response = customerController.delete(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deletedCustomer, response.getBody().getData());
        assertEquals("Cliente eliminado existosamente.", response.getBody().getMessage());
        verify(customerService).delete(id);
    }
}