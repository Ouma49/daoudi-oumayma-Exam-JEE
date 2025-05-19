package org.bank.oumaymadaoudi.controllers;

import org.bank.oumaymadaoudi.dtos.CustomerDTO;
import org.bank.oumaymadaoudi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for customer management.
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Get all customers.
     *
     * @return list of all customers
     */
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    /**
     * Get customer by ID.
     *
     * @param id the customer ID
     * @return the customer DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(customerService.getCustomerById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create a new customer.
     *
     * @param customerDTO the customer DTO
     * @return the created customer DTO
     */
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createCustomer(customerDTO), HttpStatus.CREATED);
    }

    /**
     * Update an existing customer.
     *
     * @param id the customer ID
     * @param customerDTO the customer DTO
     * @return the updated customer DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        try {
            return ResponseEntity.ok(customerService.updateCustomer(id, customerDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a customer.
     *
     * @param id the customer ID
     * @return no content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Search customers by name.
     *
     * @param name the name to search for
     * @return list of customers with names containing the given string
     */
    @GetMapping("/search")
    public ResponseEntity<List<CustomerDTO>> searchCustomersByName(@RequestParam String name) {
        return ResponseEntity.ok(customerService.searchCustomersByName(name));
    }
}