package org.bank.oumaymadaoudi.repositories;

import org.bank.oumaymadaoudi.entities.Credit;
import org.bank.oumaymadaoudi.entities.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Repayment entity.
 */
@Repository
public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
    
    /**
     * Find repayments by credit.
     *
     * @param credit the credit
     * @return list of repayments for the given credit
     */
    List<Repayment> findByCredit(Credit credit);
    
    /**
     * Find repayments by credit ordered by date (ascending).
     *
     * @param credit the credit
     * @return list of repayments for the given credit ordered by date
     */
    List<Repayment> findByCreditOrderByDateAsc(Credit credit);
}