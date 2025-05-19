package org.bank.oumaymadaoudi.services;

import org.bank.oumaymadaoudi.dtos.RepaymentDTO;

import java.util.List;

/**
 * Service interface for managing repayments.
 */
public interface RepaymentService {
    

    List<RepaymentDTO> getAllRepayments();

    RepaymentDTO getRepaymentById(Long id);

    RepaymentDTO createRepayment(RepaymentDTO repaymentDTO);

    RepaymentDTO updateRepayment(Long id, RepaymentDTO repaymentDTO);

    void deleteRepayment(Long id);
    List<RepaymentDTO> getRepaymentsByCreditId(String creditId);

    List<RepaymentDTO> getRepaymentsByCreditIdOrderByDateAsc(String creditId);
}