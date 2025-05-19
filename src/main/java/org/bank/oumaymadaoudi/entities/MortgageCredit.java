package org.bank.oumaymadaoudi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bank.oumaymadaoudi.enums.PropertyType;

/**
 * Entity representing a mortgage credit.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MortgageCredit extends Credit {
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyType propertyType; // APARTMENT, HOUSE, COMMERCIAL
}