package org.bank.oumaymadaoudi.repositories;

import org.bank.oumaymadaoudi.entities.Credit;
import org.bank.oumaymadaoudi.entities.Customer;
import org.bank.oumaymadaoudi.enums.CreditStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Credit entity.
 */
@Repository
public interface CreditRepository extends JpaRepository<Credit, String> {
    
    /**
     * Find credits by customer.
     *
     * @param customer the customer
     * @return list of credits for the given customer
     */
    List<Credit> findByCustomer(Customer customer);
    
    /**
     * Find credits by status.
     *
     * @param status the credit status
     * @return list of credits with the given status
     */
    List<Credit> findByStatus(CreditStatus status);
    
    /**
     * Find credits by customer and status.
     *
     * @param customer the customer
     * @param status the credit status
     * @return list of credits for the given customer with the given status
     */
    List<Credit> findByCustomerAndStatus(Customer customer, CreditStatus status);
}