package org.bank.oumaymadaoudi.controllers;

import org.bank.oumaymadaoudi.dtos.BusinessCreditDTO;
import org.bank.oumaymadaoudi.dtos.CreditDTO;
import org.bank.oumaymadaoudi.dtos.MortgageCreditDTO;
import org.bank.oumaymadaoudi.dtos.PersonalCreditDTO;
import org.bank.oumaymadaoudi.enums.CreditStatus;
import org.bank.oumaymadaoudi.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for credit management.
 */
@RestController
@RequestMapping("/api/credits")
public class CreditRestController {

    private final CreditService creditService;

    @Autowired
    public CreditRestController(CreditService creditService) {
        this.creditService = creditService;
    }

    /**
     * Get all credits.
     *
     * @return list of all credits
     */
    @GetMapping
    public ResponseEntity<List<CreditDTO>> getAllCredits() {
        return ResponseEntity.ok(creditService.getAllCredits());
    }

    /**
     * Get credit by ID.
     *
     * @param id the credit ID
     * @return the credit DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<CreditDTO> getCreditById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(creditService.getCreditById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create a new personal credit.
     *
     * @param creditDTO the personal credit DTO
     * @return the created credit DTO
     */
    @PostMapping("/personal")
    public ResponseEntity<PersonalCreditDTO> createPersonalCredit(@RequestBody PersonalCreditDTO creditDTO) {
        return new ResponseEntity<>(creditService.createPersonalCredit(creditDTO), HttpStatus.CREATED);
    }

    /**
     * Create a new mortgage credit.
     *
     * @param creditDTO the mortgage credit DTO
     * @return the created credit DTO
     */
    @PostMapping("/mortgage")
    public ResponseEntity<MortgageCreditDTO> createMortgageCredit(@RequestBody MortgageCreditDTO creditDTO) {
        return new ResponseEntity<>(creditService.createMortgageCredit(creditDTO), HttpStatus.CREATED);
    }

    /**
     * Create a new business credit.
     *
     * @param creditDTO the business credit DTO
     * @return the created credit DTO
     */
    @PostMapping("/business")
    public ResponseEntity<BusinessCreditDTO> createBusinessCredit(@RequestBody BusinessCreditDTO creditDTO) {
        return new ResponseEntity<>(creditService.createBusinessCredit(creditDTO), HttpStatus.CREATED);
    }

    /**
     * Update credit status.
     *
     * @param id the credit ID
     * @param status the new status
     * @return the updated credit DTO
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<CreditDTO> updateCreditStatus(@PathVariable String id, @RequestParam CreditStatus status) {
        try {
            return ResponseEntity.ok(creditService.updateCreditStatus(id, status));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a credit.
     *
     * @param id the credit ID
     * @return no content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable String id) {
        try {
            creditService.deleteCredit(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get credits by customer ID.
     *
     * @param customerId the customer ID
     * @return list of credits for the given customer
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CreditDTO>> getCreditsByCustomerId(@PathVariable Long customerId) {
        try {
            return ResponseEntity.ok(creditService.getCreditsByCustomerId(customerId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get credits by status.
     *
     * @param status the credit status
     * @return list of credits with the given status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<CreditDTO>> getCreditsByStatus(@PathVariable CreditStatus status) {
        return ResponseEntity.ok(creditService.getCreditsByStatus(status));
    }

    /**
     * Get credits by customer ID and status.
     *
     * @param customerId the customer ID
     * @param status the credit status
     * @return list of credits for the given customer with the given status
     */
    @GetMapping("/customer/{customerId}/status/{status}")
    public ResponseEntity<List<CreditDTO>> getCreditsByCustomerIdAndStatus(
            @PathVariable Long customerId, 
            @PathVariable CreditStatus status) {
        try {
            return ResponseEntity.ok(creditService.getCreditsByCustomerIdAndStatus(customerId, status));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}