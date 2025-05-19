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
    

    List<Credit> findByCustomer(Customer customer);

    List<Credit> findByStatus(CreditStatus status);

    List<Credit> findByCustomerAndStatus(Customer customer, CreditStatus status);
}