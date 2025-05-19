package org.bank.oumaymadaoudi.services;

import org.bank.oumaymadaoudi.dtos.CustomerDTO;

import java.util.List;


public interface CustomerService {
    
    /**
     * Get all customers.
     *
     * @return list of all customers
     */
    List<CustomerDTO> getAllCustomers();
    
    /**
     * Get customer by ID.
     *
     * @param id the customer ID
     * @return the customer DTO
     */
    CustomerDTO getCustomerById(Long id);
    
    /**
     * Create a new customer.
     *
     * @param customerDTO the customer DTO
     * @return the created customer DTO
     */
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    
    /**
     * Update an existing customer.
     *
     * @param id the customer ID
     * @param customerDTO the customer DTO
     * @return the updated customer DTO
     */
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
    
    /**
     * Delete a customer.
     *
     * @param id the customer ID
     */
    void deleteCustomer(Long id);
    
    /**
     * Search customers by name.
     *
     * @param name the name to search for
     * @return list of customers with names containing the given string
     */
    List<CustomerDTO> searchCustomersByName(String name);
}