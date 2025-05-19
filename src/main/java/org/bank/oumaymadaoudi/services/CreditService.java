package org.bank.oumaymadaoudi.services;

import org.bank.oumaymadaoudi.dtos.CreditDTO;
import org.bank.oumaymadaoudi.dtos.PersonalCreditDTO;
import org.bank.oumaymadaoudi.dtos.MortgageCreditDTO;
import org.bank.oumaymadaoudi.dtos.BusinessCreditDTO;
import org.bank.oumaymadaoudi.enums.CreditStatus;

import java.util.List;

/**
 * Service interface for managing credits.
 */
public interface CreditService {
    
    /**
     * Get all credits.
     *
     * @return list of all credits
     */
    List<CreditDTO> getAllCredits();
    
    /**
     * Get credit by ID.
     *
     * @param id the credit ID
     * @return the credit DTO
     */
    CreditDTO getCreditById(String id);
    
    /**
     * Create a new personal credit.
     *
     * @param creditDTO the personal credit DTO
     * @return the created credit DTO
     */
    PersonalCreditDTO createPersonalCredit(PersonalCreditDTO creditDTO);
    
    /**
     * Create a new mortgage credit.
     *
     * @param creditDTO the mortgage credit DTO
     * @return the created credit DTO
     */
    MortgageCreditDTO createMortgageCredit(MortgageCreditDTO creditDTO);
    
    /**
     * Create a new business credit.
     *
     * @param creditDTO the business credit DTO
     * @return the created credit DTO
     */
    BusinessCreditDTO createBusinessCredit(BusinessCreditDTO creditDTO);
    
    /**
     * Update credit status.
     *
     * @param id the credit ID
     * @param status the new status
     * @return the updated credit DTO
     */
    CreditDTO updateCreditStatus(String id, CreditStatus status);
    
    /**
     * Delete a credit.
     *
     * @param id the credit ID
     */
    void deleteCredit(String id);
    
    /**
     * Get credits by customer ID.
     *
     * @param customerId the customer ID
     * @return list of credits for the given customer
     */
    List<CreditDTO> getCreditsByCustomerId(Long customerId);
    
    /**
     * Get credits by status.
     *
     * @param status the credit status
     * @return list of credits with the given status
     */
    List<CreditDTO> getCreditsByStatus(CreditStatus status);
    
    /**
     * Get credits by customer ID and status.
     *
     * @param customerId the customer ID
     * @param status the credit status
     * @return list of credits for the given customer with the given status
     */
    List<CreditDTO> getCreditsByCustomerIdAndStatus(Long customerId, CreditStatus status);
}