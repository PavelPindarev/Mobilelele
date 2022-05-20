package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.dto.service.UserLoginServiceModel;
import com.example.mobilelele.model.dto.service.UserRegisterServiceModel;
import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.entity.UserRole;
import com.example.mobilelele.model.entity.enums.RoleType;
import com.example.mobilelele.repository.UserRepository;
import com.example.mobilelele.repository.UserRoleRepository;
import com.example.mobilelele.service.UserService;
import com.example.mobilelele.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final CurrentUser currentUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

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

    @Override
    public boolean login(UserLoginServiceModel userLoginServiceModel) {
        Optional<User> optUser =
                userRepository.findByUsername(userLoginServiceModel.getUsername());

        if (optUser.isEmpty()) {
            logout();
            return false;
        } else {
            User user = optUser.get();
            boolean matchesSuccessful = passwordEncoder.matches(
                    userLoginServiceModel.getPassword(),
                    user.getPassword());

            if (matchesSuccessful) {
                login(user);
                user.getRoles().forEach(r -> currentUser.addRole(r.getRole()));
            }

            return matchesSuccessful;
        }
    }


    @Override
    public void register(UserRegisterServiceModel serviceModel) {

        UserRole userRole = userRoleRepository.findByRole(RoleType.USER);

        User newUser = new User()
                .setFirstName(serviceModel.getFirstName())
                .setLastName(serviceModel.getLastName())
                .setUsername(serviceModel.getUsername())
                .setPassword(passwordEncoder.encode(serviceModel.getPassword()))
                .setActive(true)
                .setRoles(Set.of(userRole));

        newUser = userRepository.save(newUser);

        login(newUser);
    }

    @Override
    public boolean isUsernameFree(String username) {
        Optional<User> optUser = userRepository.findByUsername(username);
        return optUser.isEmpty();
    }


    @Override
    public void logout() {
        currentUser.clear();
    }

    private void login(User user) {
        currentUser.setUserName(user.getUsername())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setLoggedIn(true);
    }

}
