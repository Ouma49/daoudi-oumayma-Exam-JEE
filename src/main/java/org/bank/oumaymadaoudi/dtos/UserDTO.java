package org.bank.oumaymadaoudi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.oumaymadaoudi.enums.Role;

/**
 * DTO for User entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private Role role;
    private Long customerId; // ID of the customer associated with this user (only for ROLE_CLIENT)
}