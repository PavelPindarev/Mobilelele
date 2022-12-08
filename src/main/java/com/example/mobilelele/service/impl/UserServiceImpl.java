package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.dto.user.UserLoginBindingModel;
import com.example.mobilelele.model.dto.user.UserRegisterBindingModel;
import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.entity.UserRole;
import com.example.mobilelele.model.entity.enums.RoleType;
import com.example.mobilelele.repository.UserRepository;
import com.example.mobilelele.repository.UserRoleRepository;
import com.example.mobilelele.service.MobileleUserDetailsService;
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
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder,
                           UserDetailsService userDetailsService,
                           ModelMapper mapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = new MobileleUserDetailsService(userRepository);
        this.mapper = mapper;
    }

//    AUTHENTICATIONS
    @Override
    public void registerAndLogin(UserRegisterBindingModel userRegisterDTO) {
        User newUser = mapper.map(userRegisterDTO, User.class);
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.save(newUser);
        login(newUser.getUsername());
    }

    @Override
    public void login(String userName) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userName);

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

//    CHECKERS
    @Override
    public boolean isUsernameFree(String username) {
        Optional<User> optUser = userRepository.findByUsername(username);
        return optUser.isEmpty();
    }

    @Override
    public boolean passwordsCheck(UserLoginBindingModel loginServiceModel) {
        Optional<User> optUser =
                userRepository.findByUsername(loginServiceModel.getUsername());

        if (optUser.isEmpty()) return false;
        User user = optUser.get();

        return passwordEncoder.matches(loginServiceModel.getPassword(), user.getPassword());
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
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setActive(true)
                    .setRoles(Set.of(adminRole, userRole));

            User user = new User()
                    .setUsername("user")
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
