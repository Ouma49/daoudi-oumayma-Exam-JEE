package org.bank.oumaymadaoudi.services.impl;

import org.bank.oumaymadaoudi.dtos.RepaymentDTO;
import org.bank.oumaymadaoudi.entities.Credit;
import org.bank.oumaymadaoudi.entities.Repayment;
import org.bank.oumaymadaoudi.repositories.CreditRepository;
import org.bank.oumaymadaoudi.repositories.RepaymentRepository;
import org.bank.oumaymadaoudi.services.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of RepaymentService.
 */
@Service
@Transactional
public class RepaymentServiceImpl implements RepaymentService {

    private final RepaymentRepository repaymentRepository;
    private final CreditRepository creditRepository;

    @Autowired
    public RepaymentServiceImpl(RepaymentRepository repaymentRepository, CreditRepository creditRepository) {
        this.repaymentRepository = repaymentRepository;
        this.creditRepository = creditRepository;
    }

    @Override
    public List<RepaymentDTO> getAllRepayments() {
        return repaymentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RepaymentDTO getRepaymentById(Long id) {
        Repayment repayment = repaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repayment not found with id: " + id));
        return mapToDTO(repayment);
    }

    @Override
    public RepaymentDTO createRepayment(RepaymentDTO repaymentDTO) {
        Credit credit = creditRepository.findById(repaymentDTO.getCreditId())
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + repaymentDTO.getCreditId()));
        
        Repayment repayment = new Repayment();
        repayment.setDate(repaymentDTO.getDate());
        repayment.setAmount(repaymentDTO.getAmount());
        repayment.setType(repaymentDTO.getType());
        repayment.setCredit(credit);
        
        Repayment savedRepayment = repaymentRepository.save(repayment);
        return mapToDTO(savedRepayment);
    }

    @Override
    public RepaymentDTO updateRepayment(Long id, RepaymentDTO repaymentDTO) {
        Repayment existingRepayment = repaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repayment not found with id: " + id));
        
        // Update only the fields that can be changed
        existingRepayment.setDate(repaymentDTO.getDate());
        existingRepayment.setAmount(repaymentDTO.getAmount());
        existingRepayment.setType(repaymentDTO.getType());
        
        // If credit ID has changed, update the credit reference
        if (!existingRepayment.getCredit().getId().equals(repaymentDTO.getCreditId())) {
            Credit credit = creditRepository.findById(repaymentDTO.getCreditId())
                    .orElseThrow(() -> new RuntimeException("Credit not found with id: " + repaymentDTO.getCreditId()));
            existingRepayment.setCredit(credit);
        }
        
        Repayment updatedRepayment = repaymentRepository.save(existingRepayment);
        return mapToDTO(updatedRepayment);
    }

    @Override
    public void deleteRepayment(Long id) {
        if (!repaymentRepository.existsById(id)) {
            throw new RuntimeException("Repayment not found with id: " + id);
        }
        repaymentRepository.deleteById(id);
    }

    @Override
    public List<RepaymentDTO> getRepaymentsByCreditId(String creditId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + creditId));
        
        return repaymentRepository.findByCredit(credit).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepaymentDTO> getRepaymentsByCreditIdOrderByDateAsc(String creditId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + creditId));
        
        return repaymentRepository.findByCreditOrderByDateAsc(credit).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Map Repayment entity to RepaymentDTO.
     *
     * @param repayment the repayment entity
     * @return the repayment DTO
     */
    private RepaymentDTO mapToDTO(Repayment repayment) {
        RepaymentDTO dto = new RepaymentDTO();
        dto.setId(repayment.getId());
        dto.setDate(repayment.getDate());
        dto.setAmount(repayment.getAmount());
        dto.setType(repayment.getType());
        dto.setCreditId(repayment.getCredit().getId());
        return dto;
    }
}