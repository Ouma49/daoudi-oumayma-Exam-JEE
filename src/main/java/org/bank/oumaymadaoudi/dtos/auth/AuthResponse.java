package org.bank.oumaymadaoudi.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.oumaymadaoudi.enums.Role;

/**
 * DTO for authentication response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String username;
    private Role role;
    private Long customerId;
}