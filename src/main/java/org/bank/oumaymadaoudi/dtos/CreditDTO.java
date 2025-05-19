package org.bank.oumaymadaoudi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.oumaymadaoudi.enums.CreditStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Abstract DTO for Credit entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CreditDTO {
    private String id;
    private Date requestDate;
    private CreditStatus status;
    private Date acceptanceDate;
    private double amount;
    private int duration; // in months
    private double interestRate;
    private Long customerId; // ID of the customer associated with this credit
    private List<Long> repaymentIds = new ArrayList<>(); // List of repayment IDs associated with this credit
}