package org.bank.oumaymadaoudi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.oumaymadaoudi.enums.RepaymentType;

import java.util.Date;

/**
 * DTO for Repayment entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentDTO {
    private Long id;
    private Date date;
    private double amount;
    private RepaymentType type;
    private String creditId; // ID of the credit associated with this repayment
}