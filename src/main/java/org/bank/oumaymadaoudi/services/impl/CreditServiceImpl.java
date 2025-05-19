package org.bank.oumaymadaoudi.services.impl;

import org.bank.oumaymadaoudi.dtos.BusinessCreditDTO;
import org.bank.oumaymadaoudi.dtos.CreditDTO;
import org.bank.oumaymadaoudi.dtos.MortgageCreditDTO;
import org.bank.oumaymadaoudi.dtos.PersonalCreditDTO;
import org.bank.oumaymadaoudi.entities.*;
import org.bank.oumaymadaoudi.enums.CreditStatus;
import org.bank.oumaymadaoudi.repositories.CreditRepository;
import org.bank.oumaymadaoudi.repositories.CustomerRepository;
import org.bank.oumaymadaoudi.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of CreditService.
 */
@Service
@Transactional
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository, CustomerRepository customerRepository) {
        this.creditRepository = creditRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CreditDTO> getAllCredits() {
        return creditRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO getCreditById(String id) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + id));
        return mapToDTO(credit);
    }

    @Override
    public PersonalCreditDTO createPersonalCredit(PersonalCreditDTO creditDTO) {
        Customer customer = customerRepository.findById(creditDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + creditDTO.getCustomerId()));

        PersonalCredit credit = new PersonalCredit();
        credit.setRequestDate(new Date());
        credit.setStatus(CreditStatus.IN_REVIEW);
        credit.setAmount(creditDTO.getAmount());
        credit.setDuration(creditDTO.getDuration());
        credit.setInterestRate(creditDTO.getInterestRate());
        credit.setCustomer(customer);
        credit.setPurpose(creditDTO.getPurpose());

        PersonalCredit savedCredit = (PersonalCredit) creditRepository.save(credit);
        return (PersonalCreditDTO) mapToDTO(savedCredit);
    }

    @Override
    public MortgageCreditDTO createMortgageCredit(MortgageCreditDTO creditDTO) {
        Customer customer = customerRepository.findById(creditDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + creditDTO.getCustomerId()));

        MortgageCredit credit = new MortgageCredit();
        credit.setRequestDate(new Date());
        credit.setStatus(CreditStatus.IN_REVIEW);
        credit.setAmount(creditDTO.getAmount());
        credit.setDuration(creditDTO.getDuration());
        credit.setInterestRate(creditDTO.getInterestRate());
        credit.setCustomer(customer);
        credit.setPropertyType(creditDTO.getPropertyType());

        MortgageCredit savedCredit = (MortgageCredit) creditRepository.save(credit);
        return (MortgageCreditDTO) mapToDTO(savedCredit);
    }

    @Override
    public BusinessCreditDTO createBusinessCredit(BusinessCreditDTO creditDTO) {
        Customer customer = customerRepository.findById(creditDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + creditDTO.getCustomerId()));

        BusinessCredit credit = new BusinessCredit();
        credit.setRequestDate(new Date());
        credit.setStatus(CreditStatus.IN_REVIEW);
        credit.setAmount(creditDTO.getAmount());
        credit.setDuration(creditDTO.getDuration());
        credit.setInterestRate(creditDTO.getInterestRate());
        credit.setCustomer(customer);
        credit.setPurpose(creditDTO.getPurpose());
        credit.setCompanyName(creditDTO.getCompanyName());

        BusinessCredit savedCredit = (BusinessCredit) creditRepository.save(credit);
        return (BusinessCreditDTO) mapToDTO(savedCredit);
    }

    @Override
    public CreditDTO updateCreditStatus(String id, CreditStatus status) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + id));

        credit.setStatus(status);

        // If the status is ACCEPTED, set the acceptance date
        if (status == CreditStatus.ACCEPTED) {
            credit.setAcceptanceDate(new Date());
        }

        Credit updatedCredit = creditRepository.save(credit);
        return mapToDTO(updatedCredit);
    }

    @Override
    public void deleteCredit(String id) {
        if (!creditRepository.existsById(id)) {
            throw new RuntimeException("Credit not found with id: " + id);
        }
        creditRepository.deleteById(id);
    }

    @Override
    public List<CreditDTO> getCreditsByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        return creditRepository.findByCustomer(customer).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsByStatus(CreditStatus status) {
        return creditRepository.findByStatus(status).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsByCustomerIdAndStatus(Long customerId, CreditStatus status) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        return creditRepository.findByCustomerAndStatus(customer, status).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Map Credit entity to CreditDTO.
     *
     * @param credit the credit entity
     * @return the credit DTO
     */
    private CreditDTO mapToDTO(Credit credit) {
        CreditDTO dto;

        if (credit instanceof PersonalCredit) {
            PersonalCreditDTO personalDTO = new PersonalCreditDTO();
            personalDTO.setPurpose(((PersonalCredit) credit).getPurpose());
            dto = personalDTO;
        } else if (credit instanceof MortgageCredit) {
            MortgageCreditDTO mortgageDTO = new MortgageCreditDTO();
            mortgageDTO.setPropertyType(((MortgageCredit) credit).getPropertyType());
            dto = mortgageDTO;
        } else if (credit instanceof BusinessCredit) {
            BusinessCreditDTO businessDTO = new BusinessCreditDTO();
            businessDTO.setPurpose(((BusinessCredit) credit).getPurpose());
            businessDTO.setCompanyName(((BusinessCredit) credit).getCompanyName());
            dto = businessDTO;
        } else {
            throw new IllegalArgumentException("Unknown credit type");
        }

        dto.setId(credit.getId());
        dto.setRequestDate(credit.getRequestDate());
        dto.setStatus(credit.getStatus());
        dto.setAcceptanceDate(credit.getAcceptanceDate());
        dto.setAmount(credit.getAmount());
        dto.setDuration(credit.getDuration());
        dto.setInterestRate(credit.getInterestRate());
        dto.setCustomerId(credit.getCustomer().getId());

        // Map repayment IDs
        if (credit.getRepayments() != null) {
            dto.setRepaymentIds(credit.getRepayments().stream()
                    .map(repayment -> repayment.getId())
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}