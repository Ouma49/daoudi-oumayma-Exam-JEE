package org.bank.oumaymadaoudi.services;

import org.bank.oumaymadaoudi.dtos.CustomerDTO;

import java.util.List;


public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);
    

    List<CustomerDTO> searchCustomersByName(String name);
}