package com.example.mobilelele.service;

import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.entity.UserRole;
import com.example.mobilelele.model.userdetails.MobileleUserDetails;
import com.example.mobilelele.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MobileleUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MobileleUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found!"));
    }

    private MobileleUserDetails map(User user) {
        return new MobileleUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getRoles()
                        .stream()
                        .map(this::map)
                        .toList()
        );
    }

    private GrantedAuthority map(UserRole userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                                          userRole.
                                                  getRole().name());
    }
}
