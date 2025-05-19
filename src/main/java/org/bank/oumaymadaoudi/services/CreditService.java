package org.bank.oumaymadaoudi.services;

import org.bank.oumaymadaoudi.dtos.CreditDTO;
import org.bank.oumaymadaoudi.dtos.PersonalCreditDTO;
import org.bank.oumaymadaoudi.dtos.MortgageCreditDTO;
import org.bank.oumaymadaoudi.dtos.BusinessCreditDTO;
import org.bank.oumaymadaoudi.enums.CreditStatus;

import java.util.List;


public interface CreditService {
    

    List<CreditDTO> getAllCredits();

    CreditDTO getCreditById(String id);
    

    PersonalCreditDTO createPersonalCredit(PersonalCreditDTO creditDTO);

    MortgageCreditDTO createMortgageCredit(MortgageCreditDTO creditDTO);

    BusinessCreditDTO createBusinessCredit(BusinessCreditDTO creditDTO);

    CreditDTO updateCreditStatus(String id, CreditStatus status);

    void deleteCredit(String id);

    List<CreditDTO> getCreditsByCustomerId(Long customerId);

    List<CreditDTO> getCreditsByStatus(CreditStatus status);

    List<CreditDTO> getCreditsByCustomerIdAndStatus(Long customerId, CreditStatus status);
}