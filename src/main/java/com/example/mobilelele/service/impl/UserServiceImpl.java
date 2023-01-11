package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.dto.user.UserLoginBindingModel;
import com.example.mobilelele.model.dto.user.UserRegisterBindingModel;
import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.entity.UserRole;
import com.example.mobilelele.model.enums.RoleType;
import com.example.mobilelele.repository.UserRepository;
import com.example.mobilelele.repository.UserRoleRepository;
import com.example.mobilelele.service.EmailService;
import com.example.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(EmailService emailService,
                           UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           UserDetailsService userDetailsService,
                           PasswordEncoder passwordEncoder) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.mapper = new ModelMapper();
    }

    //    AUTHENTICATIONS
    @Override
    public void registerAndLogin(UserRegisterBindingModel userRegisterDTO,
                                 Locale preferredLocale) {

        User newUser = mapper.map(userRegisterDTO, User.class);
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.save(newUser);
        login(newUser.getEmail());

        String fullName = newUser.getFirstName() + " " + newUser.getLastName();
        emailService.sendRegistrationEmail(
                newUser.getEmail(),
                fullName,
                preferredLocale);
    }

    @Override
    public void login(String email) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(email);

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

    //    CHECKERS FOR VALIDATION
    @Override
    public boolean isEmailFree(String email) {
        Optional<User> optUser = userRepository.findByEmail(email);
        return optUser.isEmpty();
    }

    @Override
    public boolean passwordsCheck(UserLoginBindingModel userLoginDTO) {
        Optional<User> optUser =
                userRepository.findByEmail(userLoginDTO.getEmail());

        if (optUser.isEmpty()) return false;
        User user = optUser.get();

        return passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword());
    }

    @Override
    public void createUserIfNotExist(String userEmail) {
        var optUser = userRepository.findByEmail(userEmail);

        if (optUser.isEmpty()) {

            User newUser = new User()
                    .setEmail(userEmail)
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("New")
                    .setLastName("User")
                    .setRoles(Set.of());

            userRepository.save(newUser);
        }

    }


    //    INITIALIZATIONS
    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRole adminRole = userRoleRepository.findByRole(RoleType.ADMIN);
            UserRole userRole = userRoleRepository.findByRole(RoleType.USER);

            User admin = new User()
                    .setEmail("admin@example.com")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setActive(true)
                    .setRoles(Set.of(adminRole, userRole));

            User user = new User()
                    .setEmail("user@example.com")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("User")
                    .setLastName("Userov")
                    .setActive(true)
                    .setRoles(Set.of(userRole));

            userRepository.saveAll(List.of(admin, user));
        }
    }

    private void initializeRoles() {

        if (userRoleRepository.count() == 0) {
            UserRole adminRole = new UserRole();
            adminRole.setRole(RoleType.ADMIN);

            UserRole userRole = new UserRole();
            userRole.setRole(RoleType.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

}
