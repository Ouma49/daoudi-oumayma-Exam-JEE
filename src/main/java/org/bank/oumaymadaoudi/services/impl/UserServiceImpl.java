package org.bank.oumaymadaoudi.services.impl;

import org.bank.oumaymadaoudi.dtos.UserDTO;
import org.bank.oumaymadaoudi.entities.Customer;
import org.bank.oumaymadaoudi.entities.User;
import org.bank.oumaymadaoudi.repositories.CustomerRepository;
import org.bank.oumaymadaoudi.repositories.UserRepository;
import org.bank.oumaymadaoudi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of UserService.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return mapToDTO(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return mapToDTO(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO, String password) {
        // Check if username is available
        if (!isUsernameAvailable(userDTO.getUsername())) {
            throw new RuntimeException("Username is already taken: " + userDTO.getUsername());
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(userDTO.getRole());

        // Set customer if customerId is provided
        if (userDTO.getCustomerId() != null) {
            Customer customer = customerRepository.findById(userDTO.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + userDTO.getCustomerId()));
            user.setCustomer(customer);
        }

        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Check if username is changed and if it's available
        if (!existingUser.getUsername().equals(userDTO.getUsername()) && !isUsernameAvailable(userDTO.getUsername())) {
            throw new RuntimeException("Username is already taken: " + userDTO.getUsername());
        }

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setRole(userDTO.getRole());

        // Update customer if customerId is changed
        if (userDTO.getCustomerId() != null) {
            if (existingUser.getCustomer() == null || !existingUser.getCustomer().getId().equals(userDTO.getCustomerId())) {
                Customer customer = customerRepository.findById(userDTO.getCustomerId())
                        .orElseThrow(() -> new RuntimeException("Customer not found with id: " + userDTO.getCustomerId()));
                existingUser.setCustomer(customer);
            }
        } else {
            existingUser.setCustomer(null);
        }

        User updatedUser = userRepository.save(existingUser);
        return mapToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getUserByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        User user = userRepository.findByCustomer(customer)
                .orElseThrow(() -> new RuntimeException("User not found for customer with id: " + customerId));

        return mapToDTO(user);
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    /**
     * Map User entity to UserDTO.
     *
     * @param user the user entity
     * @return the user DTO
     */
    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        
        if (user.getCustomer() != null) {
            dto.setCustomerId(user.getCustomer().getId());
        }
        
        return dto;
    }
}