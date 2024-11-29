package com.notes.thinknotesbackend.repository;

import com.notes.thinknotesbackend.entity.Role;
import com.notes.thinknotesbackend.util.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole role);
}
