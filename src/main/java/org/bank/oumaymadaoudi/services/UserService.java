package org.bank.oumaymadaoudi.services;

import org.bank.oumaymadaoudi.dtos.UserDTO;
import org.bank.oumaymadaoudi.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Service interface for managing users.
 */
public interface UserService extends UserDetailsService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO getUserByUsername(String username);

    UserDTO createUser(UserDTO userDTO, String password);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);
    UserDTO getUserByCustomerId(Long customerId);

    boolean isUsernameAvailable(String username);
}