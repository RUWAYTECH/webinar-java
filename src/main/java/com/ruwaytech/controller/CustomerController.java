package com.ruwaytech.controller;

import com.ruwaytech.service.CustomerService;
import com.ruwaytech.shared.dto.CustomerDto;
import com.ruwaytech.shared.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<ResponseDto> getAll() {
        List<CustomerDto> listCustomers = this.customerService.getAll();
        ResponseDto response = ResponseDto.ok(listCustomers, "Lista de clientes.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable Integer id) {
        CustomerDto customer = this.customerService.getById(id);
        ResponseDto response = ResponseDto.ok(customer, "Datos del cliente.");
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody CustomerDto customerDto) {
        CustomerDto customer = this.customerService.create(customerDto);
        ResponseDto response = ResponseDto.ok(customer, "Cliente creado existosamente.");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable(name = "id") Integer idCustomer, @RequestBody CustomerDto customerDto) {
        CustomerDto customer = this.customerService.update(idCustomer, customerDto);
        ResponseDto response = ResponseDto.ok(customer, "Cliente actualizado existosamente.");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable(name = "id") Integer idCustomer) {
        CustomerDto customer = this.customerService.delete(idCustomer);
        ResponseDto response = ResponseDto.ok(customer, "Cliente eliminado existosamente.");
        return ResponseEntity.ok(response);
    }

}