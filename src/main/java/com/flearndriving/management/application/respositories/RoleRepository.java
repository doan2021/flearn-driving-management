package com.flearndriving.management.application.respositories;

import com.flearndriving.management.application.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
