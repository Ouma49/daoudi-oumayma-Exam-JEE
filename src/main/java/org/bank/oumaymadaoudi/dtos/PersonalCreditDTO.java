package org.bank.oumaymadaoudi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * DTO for PersonalCredit entity.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PersonalCreditDTO extends CreditDTO {
    private String purpose; // e.g., car purchase, education, home improvement
}