package com.library.repository.interfaces;

import com.library.model.User;
import com.library.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    Optional<UserRole> findUserRoleByUser (User user);
}
