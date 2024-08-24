package com.ruwaytech.service;

import com.ruwaytech.shared.dto.CustomerDto;
import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAll();
    CustomerDto getById(Integer idCustomer);
    CustomerDto create(CustomerDto customerDto);
    CustomerDto update(Integer idCustomer, CustomerDto customerDto);
    CustomerDto delete(Integer idCustomer);
}
