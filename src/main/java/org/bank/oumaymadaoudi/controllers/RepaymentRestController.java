package org.bank.oumaymadaoudi.controllers;

import org.bank.oumaymadaoudi.dtos.RepaymentDTO;
import org.bank.oumaymadaoudi.services.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for repayment management.
 */
@RestController
@RequestMapping("/api/repayments")
public class RepaymentRestController {

    private final RepaymentService repaymentService;

    @Autowired
    public RepaymentRestController(RepaymentService repaymentService) {
        this.repaymentService = repaymentService;
    }

    /**
     * Get all repayments.
     *
     * @return list of all repayments
     */
    @GetMapping
    public ResponseEntity<List<RepaymentDTO>> getAllRepayments() {
        return ResponseEntity.ok(repaymentService.getAllRepayments());
    }

    /**
     * Get repayment by ID.
     *
     * @param id the repayment ID
     * @return the repayment DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<RepaymentDTO> getRepaymentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(repaymentService.getRepaymentById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create a new repayment.
     *
     * @param repaymentDTO the repayment DTO
     * @return the created repayment DTO
     */
    @PostMapping
    public ResponseEntity<RepaymentDTO> createRepayment(@RequestBody RepaymentDTO repaymentDTO) {
        return new ResponseEntity<>(repaymentService.createRepayment(repaymentDTO), HttpStatus.CREATED);
    }

    /**
     * Update an existing repayment.
     *
     * @param id the repayment ID
     * @param repaymentDTO the repayment DTO
     * @return the updated repayment DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<RepaymentDTO> updateRepayment(@PathVariable Long id, @RequestBody RepaymentDTO repaymentDTO) {
        try {
            return ResponseEntity.ok(repaymentService.updateRepayment(id, repaymentDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a repayment.
     *
     * @param id the repayment ID
     * @return no content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepayment(@PathVariable Long id) {
        try {
            repaymentService.deleteRepayment(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get repayments by credit ID.
     *
     * @param creditId the credit ID
     * @return list of repayments for the given credit
     */
    @GetMapping("/credit/{creditId}")
    public ResponseEntity<List<RepaymentDTO>> getRepaymentsByCreditId(@PathVariable String creditId) {
        try {
            return ResponseEntity.ok(repaymentService.getRepaymentsByCreditId(creditId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get repayments by credit ID ordered by date (ascending).
     *
     * @param creditId the credit ID
     * @return list of repayments for the given credit ordered by date
     */
    @GetMapping("/credit/{creditId}/ordered")
    public ResponseEntity<List<RepaymentDTO>> getRepaymentsByCreditIdOrderByDateAsc(@PathVariable String creditId) {
        try {
            return ResponseEntity.ok(repaymentService.getRepaymentsByCreditIdOrderByDateAsc(creditId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}