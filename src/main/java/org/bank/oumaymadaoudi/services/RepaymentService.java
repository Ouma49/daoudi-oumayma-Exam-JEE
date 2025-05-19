package org.bank.oumaymadaoudi.services;

import org.bank.oumaymadaoudi.dtos.RepaymentDTO;

import java.util.List;

/**
 * Service interface for managing repayments.
 */
public interface RepaymentService {
    
    /**
     * Get all repayments.
     *
     * @return list of all repayments
     */
    List<RepaymentDTO> getAllRepayments();
    
    /**
     * Get repayment by ID.
     *
     * @param id the repayment ID
     * @return the repayment DTO
     */
    RepaymentDTO getRepaymentById(Long id);
    
    /**
     * Create a new repayment.
     *
     * @param repaymentDTO the repayment DTO
     * @return the created repayment DTO
     */
    RepaymentDTO createRepayment(RepaymentDTO repaymentDTO);
    
    /**
     * Update an existing repayment.
     *
     * @param id the repayment ID
     * @param repaymentDTO the repayment DTO
     * @return the updated repayment DTO
     */
    RepaymentDTO updateRepayment(Long id, RepaymentDTO repaymentDTO);
    
    /**
     * Delete a repayment.
     *
     * @param id the repayment ID
     */
    void deleteRepayment(Long id);
    
    /**
     * Get repayments by credit ID.
     *
     * @param creditId the credit ID
     * @return list of repayments for the given credit
     */
    List<RepaymentDTO> getRepaymentsByCreditId(String creditId);
    
    /**
     * Get repayments by credit ID ordered by date (ascending).
     *
     * @param creditId the credit ID
     * @return list of repayments for the given credit ordered by date
     */
    List<RepaymentDTO> getRepaymentsByCreditIdOrderByDateAsc(String creditId);
}