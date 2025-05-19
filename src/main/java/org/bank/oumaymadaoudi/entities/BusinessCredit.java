package org.bank.oumaymadaoudi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity representing a business credit.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BusinessCredit extends Credit {
    
    @Column(nullable = false)
    private String purpose;
    
    @Column(nullable = false)
    private String companyName;
}