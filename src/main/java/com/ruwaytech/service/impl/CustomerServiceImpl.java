package com.ruwaytech.service.impl;

import com.ruwaytech.entity.CustomerEntity;
import com.ruwaytech.repository.CustomerRepository;
import com.ruwaytech.service.CustomerService;
import com.ruwaytech.shared.dto.CustomerDto;
import com.ruwaytech.shared.exception.RestaurantException;
import com.ruwaytech.shared.mapper.CustomerMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> getAll() {
        List<CustomerEntity> listEntities = this.customerRepository.findAll();
        Collection<CustomerDto> listCustomers = this.customerMapper.toListDtos(listEntities);
        return List.copyOf(listCustomers);
    }

    @Override
    public CustomerDto getById(Integer idCustomer) {
        Optional<CustomerEntity> optional = this.customerRepository.findById(idCustomer);

        if (optional.isPresent()) {
            CustomerEntity CustomerEntity = optional.get();
            return this.customerMapper.toDto(CustomerEntity);
        } else {
            throw new RestaurantException("No existe el cliente con id: " + idCustomer);
        }
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        CustomerEntity customerEntity = this.customerMapper.toEntityIgnoredId(customerDto);
        CustomerEntity customerCreated = this.customerRepository.save(customerEntity);
        return this.customerMapper.toDto(customerCreated);
    }

    @Override
    public CustomerDto update(Integer idCustomer, CustomerDto customerDto) {
        Optional<CustomerEntity> optional = this.customerRepository.findById(idCustomer);

        if (optional.isPresent()) {
            CustomerEntity customerEntity = optional.get();
            this.customerMapper.updateEntityFromDtoIgnoredId(customerDto, customerEntity);
            CustomerEntity customerUpdated = this.customerRepository.save(customerEntity);
            return this.customerMapper.toDto(customerUpdated);
        } else {
            throw new RestaurantException("No existe el cliente con id: " + idCustomer);
        }
    }

    @Override
    public CustomerDto delete(Integer idCustomer) {
        Optional<CustomerEntity> optional = this.customerRepository.findById(idCustomer);
        if (optional.isPresent()) {
            CustomerEntity customerEntity = optional.get();
            this.customerRepository.delete(customerEntity);
            return this.customerMapper.toDto(customerEntity);
        } else {
            throw new RestaurantException("No existe el cliente con id: " + idCustomer);
        }
    }

}
