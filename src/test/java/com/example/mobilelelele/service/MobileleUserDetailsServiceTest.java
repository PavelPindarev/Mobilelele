package com.example.mobilelelele.service;

import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.entity.UserRole;
import com.example.mobilelele.model.enums.RoleType;
import com.example.mobilelele.model.userdetails.MobileleUserDetails;
import com.example.mobilelele.repository.UserRepository;
import com.example.mobilelele.service.MobileleUserDetailsService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MobileleUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private MobileleUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new MobileleUserDetailsService(
                mockUserRepo
        );
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        //arrange
        User testUserEntity =
                new User().
                        setEmail("pesho@example.com").
                        setPassword("topsecret").
                        setFirstName("Pesho").
                        setLastName("Petrov").
                        setActive(true).
                        setImageUrl("http://image.com/image").
                        setRoles(
                                Set.of(
                                        new UserRole().setRole(RoleType.ADMIN),
                                        new UserRole().setRole(RoleType.USER)
                                )
                        );

        when(mockUserRepo.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));

        //act
        MobileleUserDetails userDetails = (MobileleUserDetails)
                toTest.loadUserByUsername(testUserEntity.getEmail());

        //assert
        Assertions.assertEquals(testUserEntity.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUserEntity.getFirstName() + " " + testUserEntity.getLastName(),
                userDetails.getFullName());

        var authorities = userDetails.getAuthorities();

        Assertions.assertEquals(2, authorities.size());

        var authoritiesIter = authorities.iterator();

        Assertions.assertEquals("ROLE_" + RoleType.ADMIN.name(),
                authoritiesIter.next().getAuthority());
        Assertions.assertEquals("ROLE_" + RoleType.USER.name(),
                authoritiesIter.next().getAuthority());
    }
}
