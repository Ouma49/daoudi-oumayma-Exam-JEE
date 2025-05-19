package org.bank.oumaymadaoudi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.oumaymadaoudi.enums.CreditStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Abstract entity representing a bank credit.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Credit {

    @Id
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date requestDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CreditStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date acceptanceDate;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private int duration; // in months

    @Column(nullable = false)
    private double interestRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Repayment> repayments = new ArrayList<>();

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
