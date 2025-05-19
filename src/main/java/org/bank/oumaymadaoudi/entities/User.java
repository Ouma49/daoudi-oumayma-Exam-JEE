package org.bank.oumaymadaoudi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.oumaymadaoudi.enums.Role;

/**
 * Entity representing a user in the system.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users") // 'user' is a reserved keyword in some databases
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer; // Only for users with ROLE_CLIENT
}