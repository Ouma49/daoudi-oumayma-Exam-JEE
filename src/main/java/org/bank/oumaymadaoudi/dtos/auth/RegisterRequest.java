package org.bank.oumaymadaoudi.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.oumaymadaoudi.enums.Role;

/**
 * DTO for user registration request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
    private Long customerId; // Optional, only for CLIENT role
}