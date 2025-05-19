package org.bank.oumaymadaoudi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.oumaymadaoudi.enums.RepaymentType;

import java.util.Date;

/**
 * Entity representing a credit repayment.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repayment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;
    
    @Column(nullable = false)
    private double amount;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RepaymentType type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_id", nullable = false)
    private Credit credit;
}