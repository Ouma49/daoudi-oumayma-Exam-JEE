package org.bank.oumaymadaoudi.repositories;

import org.bank.oumaymadaoudi.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Customer entity.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    /**
     * Find customers by name containing the given string (case-insensitive).
     *
     * @param name the name to search for
     * @return list of customers with names containing the given string
     */
    List<Customer> findByNameContainingIgnoreCase(String name);
}