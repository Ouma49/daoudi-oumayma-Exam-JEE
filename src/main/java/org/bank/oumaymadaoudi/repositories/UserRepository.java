package org.bank.oumaymadaoudi.repositories;

import org.bank.oumaymadaoudi.entities.Customer;
import org.bank.oumaymadaoudi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find user by username.
     *
     * @param username the username
     * @return optional containing the user if found
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Check if a user with the given username exists.
     *
     * @param username the username
     * @return true if a user with the given username exists, false otherwise
     */
    boolean existsByUsername(String username);
    
    /**
     * Find user by customer.
     *
     * @param customer the customer
     * @return optional containing the user if found
     */
    Optional<User> findByCustomer(Customer customer);
}