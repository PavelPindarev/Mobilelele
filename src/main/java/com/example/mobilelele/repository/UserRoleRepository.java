package com.example.mobilelele.repository;

import com.example.mobilelele.model.entity.UserRole;
import com.example.mobilelele.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRole(RoleType role);
}
