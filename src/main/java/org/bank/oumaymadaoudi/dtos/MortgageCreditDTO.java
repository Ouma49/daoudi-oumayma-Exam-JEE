package org.bank.oumaymadaoudi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bank.oumaymadaoudi.enums.PropertyType;

/**
 * DTO for MortgageCredit entity.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MortgageCreditDTO extends CreditDTO {
    private PropertyType propertyType; // APARTMENT, HOUSE, COMMERCIAL
}